cmake_minimum_required(VERSION 3.10)
project(JNITest VERSION 1.0 LANGUAGES C)

set(CMAKE_C_STANDARD 99)
set(CMAKE_C_STANDARD_REQUIRED ON)

find_package(Java REQUIRED)
find_package(JNI REQUIRED)

if(NOT JNI_FOUND)
    message(FATAL_ERROR "JNI not found. Please set JAVA_HOME environment variable.")
endif()

include_directories(${JNI_INCLUDE_DIRS})

set(LIBRARY_OUTPUT_PATH "${CMAKE_BINARY_DIR}")
set(LIBRARY_NAME "app")

add_library(app SHARED src/main/c/App.c)

set_target_properties(app PROPERTIES
    OUTPUT_NAME "${LIBRARY_NAME}"
    LIBRARY_OUTPUT_DIRECTORY "${LIBRARY_OUTPUT_PATH}"
    RUNTIME_OUTPUT_DIRECTORY "${LIBRARY_OUTPUT_PATH}"
)

target_link_libraries(app ${JNI_LIBRARIES})

message(STATUS "Java include dirs: ${JNI_INCLUDE_DIRS}")
message(STATUS "Library will be built to: ${LIBRARY_OUTPUT_PATH}/${LIBRARY_NAME}")