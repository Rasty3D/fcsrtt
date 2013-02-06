# Project configuration
cmake_minimum_required(VERSION 2.8)
project(fcsrtt)
set(CMAKE_MODULE_PATH ${ROOT_DIR}/cmake)
set(EXECUTABLE_OUTPUT_PATH ../../bin)
set(LIBRARY_OUTPUT_PATH ../../bin)


# Compiler options
set(CMAKE_CXX_FLAGS "-Wall -pedantic -O3")
set(CMAKE_EXE_LINKER_FLAGS "-Wall -pedantic -O3")


# Set Doxygen
set(DOXYFILE_OUTPUT_DIR "doc")
set(DOXYFILE_LATEX "OFF")
include(UseDoxygen OPTIONAL)

if (DOXYGEN_FOUND)
  message("DOXYGEN found")
else (DOXYGEN_FOUND)
  message("DOXYGEN not found")
endif (DOXYGEN_FOUND)


# Enable testing
enable_testing()


# Set subdirectories
add_subdirectory(lib)
add_subdirectory(exe)
