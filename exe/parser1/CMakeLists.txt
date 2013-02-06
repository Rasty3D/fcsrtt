# Project configuration
project(parser1)

# Include directories
#include_directories(${ROOT_DIR}/lib/)

# Add executable
add_executable(parser1
  src/main.cpp)
#target_link_libraries(parser1
#  othello dl rt)
install(TARGETS parser1
  RUNTIME DESTINATION ${ROOT_DIR}/bin)
  