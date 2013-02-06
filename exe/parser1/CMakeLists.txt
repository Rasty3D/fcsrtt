# Project configuration
project(parser1)

# Include directories
include_directories(${ROOT_DIR}/lib/fcsrtt/include)

# Add executable
add_executable(parser1
  src/main.cpp)
target_link_libraries(parser1
  fcsrtt)
install(TARGETS parser1
  RUNTIME DESTINATION ${ROOT_DIR}/bin)
  