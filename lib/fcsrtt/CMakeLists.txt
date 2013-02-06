# Project configuration
project(fcsrtt)

# Include directories
include_directories(include)

# Add library
add_library(fcsrtt
  src/fcsrtt.cpp)

# Add test
#add_executable(test_fcsrtt
#  test/test_fcsrtt.cpp)
#target_link_libraries(test_fcsrtt
#  fcsrtt)
#add_test(fcsrtt
#  ${EXECUTABLE_OUTPUT_PATH}/test_fcsrtt)
