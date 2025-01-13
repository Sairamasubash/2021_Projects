#
# PROGRAM:  PROGRAMMING ASSIGNMENT #1
# AUTHOR:   Sairamasubash Muppalaneni
# Z-ID:     z1844762
# DATE DUE: 1/29/21, 11:59 PM
#

# Compiler variables
CCFLAGS = -Wall -std=c++11

# Rule to link object code files to create executable file
z1844762_project1: z1844762_project1.o
	g++ $(CCFLAGS) -o z1844762_project1 z1844762_project1.o

# Rule to compile source code file to object code
z1844762_project1.o: z1844762_project1.cpp
	g++ $(CCFLAGS) -c z1844762_project1.cpp

# Pseudo-target to remove object code and executable files
clean:
	-rm *.o z1844762_project1
