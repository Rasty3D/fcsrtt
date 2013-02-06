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
	// TODO
	return false;
}

bool Fcsrtt::parseFile(const char *filename)
{
		/* Variables */

	std::ifstream file;
	std::string line;
	int session = 0;
	size_t posColon;
	std::string key;
	std::string value;
	char lastVector = '\0';
	int number;


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
		value = line.substr(posColon + 2, line.size());

		if (key.compare("Start Date") == 0)
		{
			std::cout << "Start Date: " << value << std::endl;
			lastVector = '\0';
		}
		else if (key.compare("End Date") == 0)
		{
			std::cout << "End Date: " << value << std::endl;
			lastVector = '\0';
		}
		else if (key.compare("Subject") == 0)
		{
			std::cout << "Subject: " << value << std::endl;
			lastVector = '\0';
		}
		else if (key.compare("Start Time") == 0)
		{
			std::cout << "Start Time: " << value << std::endl;
			lastVector = '\0';
		}
		else if (key.compare("End Time") == 0)
		{
			std::cout << "End Time: " << value << std::endl;
			lastVector = '\0';
		}
		else if (key.compare("C") == 0)
		{
			std::cout << "C: " << value << std::endl;
			lastVector = 'C';
		}
		else if (this->getNumber(key, number))
		{
			if (lastVector != '\0')
				std::cout << value << std::endl;
		}
		else
		{
			// Discard line
			lastVector = '\0';
		}
	}

	// TODO

/*
File: C:\MED-PC IV\DATA\!2013-01-22_16h36m.Subject 2733



Start Date: 01/22/13
End Date: 01/22/13
Subject: 2733
Experiment: 1
Group: 0
Box: 1
Start Time: 16:36:58
End Time: 20:30:25
MSN: 5CSRT IIT Stage 1, 3ses night
I:1056.0
J:  2.00
L:  0.00
U:  0.00
V:  0.00
Y:  0.00
A:
     0: 100.00  30.00   5.00   1.00   2.00  30.00
B:
     0:  49.00 3000.0 500.00   0.00 200.00 1814.0  30.00   4.00 2000.0 200.00
C:
     0:   0.00   0.00  49.00   0.00   0.00   0.00  51.00   0.00   0.00   0.00   0.00
    11:   0.00 100.00   0.00  49.00
*/

		/* Close and exit */

	file.close();
	return true;
}

bool Fcsrtt::getNumber(std::string &line, int &number)
{
	for (int i = 0; i < line.size(); i++)
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
