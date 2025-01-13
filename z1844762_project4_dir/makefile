#
# PROGRAM:  PROGRAMMING ASSIGNMENT #4
# AUTHOR:   Sairamasubash Muppalaneni
# Z-ID:     z1844762
# DATE DUE: 3/20/21, 11:59 PM
#

# Compiler variables
CCFLAGS = -Wall -std=c++11

# Rule to link object code files to create executable file
z1844762_project4: z1844762_project4.o
	g++ $(CCFLAGS) -o z1844762_project4 z1844762_project4.o -lpthread

# Rule to compile source code file to object code
z1844762_project4.o: z1844762_project4.cpp
	g++ $(CCFLAGS) -c z1844762_project4.cpp

# Pseudo-target to remove object code and executable files
clean:
	-rm *.o z1844762_project4

