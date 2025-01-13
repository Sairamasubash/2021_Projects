/****************************************************************
 FILE:      PhysicalMemory.h

 AUTHOR:    Sairamasubash Muppalaneni

 Z-ID:      Z1844762

 DUE DATE:  April 10, 2021

 PURPOSE:   The purpose of the PhysicalMemory.h file is to create a
            class called PhysicalMemory. This PhysicalMemory class
            has a total of six methods and four data members/structures
            declared inside of it.
 ****************************************************************/

// Here are the beginning header guards for the PhysicalMemory.h file.
#ifndef PhysicalMemory_H
#define PhysicalMemory_H

// Here are all the libraries that are required by this header file.
#include <iostream>
#include <string>
#include <vector>

// Here is the using statement that is required by this header file.
using namespace std;

// Creating a class called PhysicalMemory, and this class will be used in the main function.
class PhysicalMemory
{
  // Declaring the public methods for the PhysicalMemory class.
  public:
     PhysicalMemory(int memorySize, int algorithmIn);
     void access(int frameID);
     void printMemory() const;
     int swapIn(string item);

  // Declaring the private methods and data members/structures for the PhysicalMemory class.
  private:
     vector <string> memoryList;
     vector <int> timeList;
     int currentTimeIndex;
     int algorithmFlag;
     int getNextAvailableFrame();
     bool isFull();
};

#endif   // Here is the ending header guard for the PhysicalMemory.h file.

