#ifndef FCSRTT_H_
#define FCSRTT_H_

/*
 * INCLUDES
 */

#include <iostream>
#include <fstream>
#include <list>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>


/*
 * DEFINES
 */

#define FCSRTT_N_CORRECT		0	// Number of correct responses
#define FCSRTT_N_INCORRECT		1	// Number of incorrect responses
#define FCSRTT_N_OMISSIONS		2	// Number of omissions
#define FCSRTT_N_PREMATURE		3	// Number of premature responses
#define FCSRTT_N_PERSEVERANT	4	// Number of perseverant responses
#define FCSRTT_N_TIMEOUT		5	// Number of timeout responses
#define FCSRTT_N_HEADENTRIES	6	// Number of head entries
#define FCSRTT_N_TOTAL			7	// Number of total responses
#define FCSRTT_P_PREMATURE		8	// Percentage of premature responses
#define FCSRTT_P_PERSEVERANT	9	// Percentage of perseverant responses
#define FCSRTT_P_CORRECT		10	// Percentage of correct trials
#define FCSRTT_P_INCORRECT		11	// Percentage of incorrect trials
#define FCSRTT_P_OMISSIONS		12	// Percentage of omissions
#define FCSRTT_P_ACCURACY		13	// Percentage of accuracy
#define FCSRTT_TOTAL_TRIALS		14	// Total trials run
#define FCSRTT_SIZE_C			15	// Param C vector size

#define FCSRTT_SIZE_D			15	// Param D vector size

#define FCSRTT_PARAMS_GROUP_C	0
#define FCSRTT_PARAMS_GROUP_D	1


/*
 * TYPES
 */

typedef struct
{
	int id;
	int genotype;
}Fcsrtt_mouse;

typedef struct
{
	std::string timeStart;
	std::string timeEnd;
	float paramsC[FCSRTT_SIZE_C];
	float paramsD[FCSRTT_SIZE_D];
}Fcsrtt_session;

typedef struct
{
	std::string dateStart;
	std::string dateEnd;
	int daySeq;
	Fcsrtt_session session[3];
}Fcsrtt_testDay;

typedef struct
{
	Fcsrtt_mouse mouse;
	std::list<Fcsrtt_testDay> tests;
}Fcsrtt_experiment;


/*
 * CLASS
 */

class Fcsrtt
{
private:
	std::list<Fcsrtt_experiment> experiments;

public:
	Fcsrtt();
	~Fcsrtt();

	bool parseFolder(const char *path);
	bool parseFile(const char *filename);

	void print();
	bool saveByParam(const char *filename, bool verbose = false);
	void saveByParam(
		int paramGroup, int paramId,
		std::ofstream &file, bool verbose = false);

	bool checkData();

private:
	bool getNumber(std::string &line, int &number);
	int extractValues(std::string &line, float *values, int size);
	Fcsrtt_experiment *lookForMouse(int id);
};

#endif	/* FCSRTT_H_ */
