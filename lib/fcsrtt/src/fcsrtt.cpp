/*
 * INCLUDES
 */

#include "fcsrtt.h"


/*
 * CLASS
 */

Fcsrtt::Fcsrtt()
{
	experiments.clear();
}

Fcsrtt::~Fcsrtt()
{
	experiments.clear();
}

bool Fcsrtt::parseFolder(const char *path)
{
	// Variables
	DIR *dir = opendir(path);
	struct dirent *ent;
	std::string filename;
	std::list<std::string> filenames;

	// Get list of files
	if (dir)
	{
		while ((ent = readdir(dir)) != NULL)
		{
			if (ent->d_type == DT_REG)
				filenames.push_back(ent->d_name);
		}

		closedir(dir);
	}
	else
	{
		std::cout << "Error reading the engines folder" << std::endl;
	}

	// Sort filenames
	filenames.sort();

	// Parse files
	for (std::list<std::string>::iterator it = filenames.begin();
		 it != filenames.end(); it++)
	{
		filename = path;
		if (path[strlen(path) - 1] != '/')
			filename += "/";
		filename += (*it);

		std::cout << "Processing [" << filename << "]" << std::endl;

		if (!parseFile(filename.c_str()))
			std::cout << "  Error. Skiping file" << std::endl;
	}

	// Return ok
	return true;
}

bool Fcsrtt::parseFile(const char *filename)
{
		/* Variables */

	std::ifstream file;
	std::string line;
	int session = -1;
	size_t posColon;
	std::string key;
	std::string value;
	char lastVector = '\0';
	int number;
	Fcsrtt_testDay testDay;
	Fcsrtt_mouse mouse = {0, 0};
	int nValues;
	float values[11];
	Fcsrtt_experiment *experiment = NULL;


		/* Open file */

	file.open(filename);

	if (!file)
	{
		std::cout << "Error opening file [" << filename << "]" << std::endl;
		return false;
	}


		/* Parse file */

	while (!file.eof())
	{
		// Get line
		std::getline(file, line);

		// Check line size
		if (line.size() < 2)
			continue;

		// Extract key
		posColon = line.find_first_of(':');

		// Colon not found
		if (posColon == std::string::npos)
			continue;

		key = line.substr(0, posColon);
		value = line.substr(posColon + 2);

		// value last \n or \r
		if (value[value.size() - 1] == '\r')
			value.resize(value.size() - 1);

		if (key.compare("Start Date") == 0)
		{
			// Move to next session
			session++;

			// Move to next experiment
			if (session == 3)
			{
				session = 0;

				if (experiment == NULL)
				{
					std::cout << "Something went wrong, the experiment shouldn't be NULL" << std::endl;
					file.close();
					return false;
				}
				else
				{
					testDay.daySeq = experiment->tests.size() + 1;
					experiment->tests.push_back(testDay);
				}
			}

			//std::cout << "Start Date: " << value << std::endl;
			testDay.dateStart = value;
			lastVector = '\0';
		}
		else if (key.compare("End Date") == 0)
		{
			//std::cout << "End Date: " << value << std::endl;
			testDay.dateEnd = value;
			lastVector = '\0';
		}
		else if (key.compare("Subject") == 0)
		{
			//std::cout << "Subject: " << value << std::endl;
			mouse.id = atoi(value.c_str());
			lastVector = '\0';

			// Look for the mouse
			experiment = this->lookForMouse(mouse.id);

			if (experiment == NULL)
			{
				//std::cout << "Mouse not found, adding " << mouse.id << std::endl;
				Fcsrtt_experiment newExperiment;
				this->experiments.push_back(newExperiment);
				experiment = &this->experiments.back();
				experiment->mouse = mouse;
				experiment->tests.clear();
			}
			else
			{
				//std::cout << "Mouse found " << mouse.id << std::endl;
			}
		}
		else if (key.compare("Start Time") == 0)
		{
			//std::cout << "Start Time: " << value << std::endl;
			testDay.session[session].timeStart = value;
			lastVector = '\0';
		}
		else if (key.compare("End Time") == 0)
		{
			//std::cout << "End Time: " << value << std::endl;
			testDay.session[session].timeEnd = value;
			lastVector = '\0';
		}
		else if (key.compare("C") == 0)
		{
			lastVector = 'C';
		}
		else if (key.compare("D") == 0)
		{
			lastVector = 'D';
		}
		else if (this->getNumber(key, number))
		{
			if (lastVector == 'C')
			{
				// Save data in the vector
				nValues = this->extractValues(value, values, 11);

				if (number + nValues > FCSRTT_SIZE_C)
				{
					std::cout << "Wrong number of values" << std::endl;
					file.close();
					return false;
				}

				memcpy(
					&testDay.session[session].paramsC[number],
					values, nValues * sizeof(float));
			}
			else if (lastVector == 'D')
			{
				// Save data in the vector
				nValues = this->extractValues(value, values, 11);

				if (number + nValues > FCSRTT_SIZE_D)
				{
					std::cout << "Wrong number of values" << std::endl;
					file.close();
					return false;
				}

				memcpy(
					&testDay.session[session].paramsD[number],
					values, nValues * sizeof(float));
			}
		}
		else
		{
			// Discard line
			lastVector = '\0';
		}
	}


		/* Close and exit */

	file.close();
	return true;
}

void Fcsrtt::print()
{
	for (std::list<Fcsrtt_experiment>::iterator it = this->experiments.begin();
		 it != this->experiments.end(); it++)
	{
		// Mouse
		std::cout << "Mouse: " << (*it).mouse.id << " [";
		std::cout << (*it).mouse.genotype << "]" << std::endl;

		std::cout << "Testing days:" << std::endl;

		for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
			 itDay != (*it).tests.end(); itDay++)
		{
			// Date
			std::cout << "  From " << (*itDay).dateStart;
			std::cout << " to " << (*itDay).dateEnd << std::endl;

			// Day sequence
			std::cout << "  Day sequence: " << (*itDay).daySeq << std::endl;

			// Sessions
			for (int s = 0; s < 3; s++)
			{
				// Session
				std::cout << "  Session " << (s + 1) << ":" << std::endl;

				// Time
				std::cout << "    Time from " << (*itDay).session[s].timeStart;
				std::cout << " to " << (*itDay).session[s].timeEnd << std::endl;

				// Parameters
				std::cout << "    Params C: ";
				for (int p = 0; p < FCSRTT_SIZE_C - 1; p++)
					std::cout << (*itDay).session[s].paramsC[p] << " ";
				std::cout << (*itDay).session[s].paramsC[FCSRTT_SIZE_C - 1] << std::endl;

				std::cout << "    Params D: ";
				for (int p = 0; p < FCSRTT_SIZE_D - 1; p++)
					std::cout << (*itDay).session[s].paramsD[p] << " ";
				std::cout << (*itDay).session[s].paramsD[FCSRTT_SIZE_D - 1] << std::endl;
			}
		}
	}
}

bool Fcsrtt::saveByParam(const char *filename, bool verbose)
{
		/* Open file */

	std::ofstream file;
	file.open(filename);

	if (!file)
	{
		std::cout << "Error writing file" << std::endl;
		return false;
	}


		/* Get max day sequence */

	unsigned int maxDaySequence = 0;

	for (std::list<Fcsrtt_experiment>::iterator it = this->experiments.begin();
		 it != this->experiments.end(); it++)
	{
		if ((*it).tests.size() > maxDaySequence)
			maxDaySequence = (*it).tests.size();
	}


		/* Print parameters */

	for (int i = 0; i < FCSRTT_SIZE_C; i++)
	{
		// Print parameter name
		file << "Parameter C" << i << std::endl;

		// Print header
		file << "Mouse,";

		for (unsigned int day = 0; day < maxDaySequence - 1; day++)
			file << "s1,s2,s3,";
		file << "s1,s2,s3" << std::endl;

		// Print parameter
		this->saveByParam(FCSRTT_PARAMS_GROUP_C, i, file, verbose);
	}

	for (int i = 0; i < FCSRTT_SIZE_D; i++)
	{
		// Print parameter name
		file << "Parameter D" << i << std::endl;

		// Print header
		file << "Mouse,";

		for (unsigned int day = 0; day < maxDaySequence - 1; day++)
			file << "s1,s2,s3,";
		file << "s1,s2,s3" << std::endl;

		// Print parameter
		this->saveByParam(FCSRTT_PARAMS_GROUP_D, i, file, verbose);
	}


		/* Close file */

	file.close();


		/* Exit */

	return true;
}

void Fcsrtt::saveByParam(
	int paramGroup, int paramId,
	std::ofstream &file, bool verbose)
{
	for (std::list<Fcsrtt_experiment>::iterator it = this->experiments.begin();
		 it != this->experiments.end(); it++)
	{
		if (verbose)
		{
			// Date start
			file << "Date start,";
			for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
				 itDay != (*it).tests.end(); itDay++)
			{
				for (int s = 0; s < 3; s++)
					file << (*itDay).dateStart << ",";
			}
			file << std::endl;

			// Date end
			file << "Date end,";
			for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
				 itDay != (*it).tests.end(); itDay++)
			{
				for (int s = 0; s < 3; s++)
					file << (*itDay).dateEnd << ",";
			}
			file << std::endl;

			// Time start
			file << "Time start,";
			for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
				 itDay != (*it).tests.end(); itDay++)
			{
				for (int s = 0; s < 3; s++)
					file << (*itDay).session[s].timeStart << ",";
			}
			file << std::endl;

			// Time end
			file << "Time end,";
			for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
				 itDay != (*it).tests.end(); itDay++)
			{
				for (int s = 0; s < 3; s++)
					file << (*itDay).session[s].timeEnd << ",";
			}
			file << std::endl;
		}

		// Params
		file << (*it).mouse.id << ",";
		for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
			 itDay != (*it).tests.end(); itDay++)
		{
			for (int s = 0; s < 3; s++)
			{
				if (paramGroup == FCSRTT_PARAMS_GROUP_C)
					file << (*itDay).session[s].paramsC[paramId] << ",";
				else if (paramGroup == FCSRTT_PARAMS_GROUP_D)
					file << (*itDay).session[s].paramsD[paramId] << ",";
			}
		}
		file << std::endl;
	}
}

bool Fcsrtt::checkData()
{
	bool dataCorrect = true;

	for (std::list<Fcsrtt_experiment>::iterator it = this->experiments.begin();
		 it != this->experiments.end(); it++)
	{
		for (std::list<Fcsrtt_testDay>::iterator itDay = (*it).tests.begin();
			 itDay != (*it).tests.end(); itDay++)
		{
			// Check the start time of the three sessions
			if ((*itDay).session[0].timeStart.compare(
					(*itDay).session[0].timeStart) != 0 ||
				(*itDay).session[0].timeStart.compare(
					(*itDay).session[1].timeStart) != 0 ||
				(*itDay).session[1].timeStart.compare(
					(*itDay).session[2].timeStart) != 0)
				dataCorrect = false;
		}
	}

	return dataCorrect;
}

bool Fcsrtt::getNumber(std::string &line, int &number)
{
	for (unsigned int i = 0; i < line.size(); i++)
	{
		if (line[i] != ' ' && line[i] != '\t' &&
			(line[i] < '0' || line[i] > '9'))
		{
			return false;
		}
	}

	number = atoi(line.c_str());
	return true;
}

int Fcsrtt::extractValues(std::string &line, float *values, int size)
{
	int nValues = 0;
	bool insideNumber = false;
	int posLastNumber = -1;

	for (unsigned int i = 0; i < line.size(); i++)
	{
		if (insideNumber)
		{
			if (line[i] == ' ')
			{
				values[nValues] = (float)atof(line.substr(posLastNumber, line.size()).c_str());
				nValues++;
				insideNumber = false;
			}
		}
		else
		{
			if (line[i] != ' ')
			{
				posLastNumber = i;
				insideNumber = true;
			}
		}
	}

	if (insideNumber)
	{
		values[nValues] = (float)atof(line.substr(posLastNumber, line.size()).c_str());
		nValues++;
	}

	return nValues;
}

Fcsrtt_experiment *Fcsrtt::lookForMouse(int id)
{
	for (std::list<Fcsrtt_experiment>::iterator it = this->experiments.begin();
		 it != this->experiments.end(); it++)
	{
		if ((*it).mouse.id == id)
			return &(*it);
	}

	return NULL;
}
