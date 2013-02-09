#include <iostream>
#include "fcsrtt.h"

int main(int argc, char *argv[])
{
	// Welcome message
	std::cout << "Welcome to Ping's parser" << std::endl;

	// Folder/file parser
	Fcsrtt fcsrtt;

	// Check arguments
	if (argc < 2)
	{
		std::cout << "Sintax:" << std::endl;
		std::cout << "  parser <input folder> <output file> <output verbose file>" << std::endl;
		return 0;
	}

	// Parse one single file
	if (!fcsrtt.parseFolder(argv[1]))
	{
		std::cout << "Error parsing file" << std::endl;
		return 0;
	}

	// Print results
	//fcsrtt.print();

	// Check data
	if (!fcsrtt.checkData())
	{
		std::cout << "Warning: error checking data. The output file might be wrong" << std::endl;
		std::cout << "Please check the verbose output file and verify the data" << std::endl;
	}

	// Save params
	if (argc >= 3)
		fcsrtt.saveByParam(argv[2]);

	// Save verbose results
	if (argc >= 4)
		fcsrtt.saveByParam(argv[3], true);

	// Return ok
	return 0;
}
