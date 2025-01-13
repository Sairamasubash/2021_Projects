#
# PROGRAM:  PROGRAMMING ASSIGNMENT #5
# AUTHOR:   Sairamasubash Muppalaneni
# Z-ID:     z1844762
# DATE DUE: 4/10/21, 11:59 PM
#

# Compiler variables
CCFLAGS = -Wall -std=c++11

# Rule to link object code files to create executable file
z1844762_project5: z1844762_project5.o PageTable.o PhysicalMemory.o
	g++ $(CCFLAGS) -o z1844762_project5 z1844762_project5.o PageTable.o PhysicalMemory.o

# Rule to compile source code file to object code (z1844762_project5)
z1844762_project5.o: z1844762_project5.cpp
	g++ $(CCFLAGS) -c z1844762_project5.cpp

# Rule to compile source code file to object code (PageTable.cpp)
PageTable.o: PageTable.cpp
	g++ $(CCFLAGS) -c -o PageTable.o PageTable.cpp

# Rule to compile source code file to object code (PhysicalMemory.cpp)
PhysicalMemory.o: PhysicalMemory.cpp
	g++ $(CCFLAGS) -c -o PhysicalMemory.o PhysicalMemory.cpp

# Pseudo-target to remove object code and executable files
clean:
	-rm *.o z1844762_project5
