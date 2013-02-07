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
		std::cout << "Introduce the file to parse" << std::endl;
		return 0;
	}

	// Parse one single file
	if (!fcsrtt.parseFolder(argv[1]))
	{
		std::cout << "Error parsing file" << std::endl;
		return 0;
	}

	// Print results
	fcsrtt.print();

	// Return ok
	return 0;
}
