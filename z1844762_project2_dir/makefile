#
# PROGRAM:  PROGRAMMING ASSIGNMENT #2
# AUTHOR:   Sairamasubash Muppalaneni
# Z-ID:     z1844762
# DATE DUE: 2/16/21, 11:59 PM
#

# Compiler variables
CCFLAGS = -Wall -std=c++11

# Rule to link object code files to create executable file
z1844762_project2: z1844762_project2.o
	g++ $(CCFLAGS) -o z1844762_project2 z1844762_project2.o

# Rule to compile source code file to object code
z1844762_project2.o: z1844762_project2.cpp
	g++ $(CCFLAGS) -c z1844762_project2.cpp

# Pseudo-target to remove object code and executable files
clean:
	-rm *.o z1844762_project2
