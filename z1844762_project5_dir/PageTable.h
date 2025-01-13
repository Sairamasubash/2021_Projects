/****************************************************************
 FILE:      PageTable.h

 AUTHOR:    Sairamasubash Muppalaneni

 Z-ID:      Z1844762

 DUE DATE:  April 10, 2021

 PURPOSE:   The purpose of the PageTable.h file is to create a
            class called PageTable. This PageTable class has a
            total of seven methods and three data members/structures
            declared inside of it.
 ****************************************************************/

// Here are the beginning header guards for the PageTable.h file.
#ifndef PageTable_H
#define PageTable_H

// Here are all the libraries that are required by this header file.
#include <iostream>
#include <string>
#include <vector>
#include "PhysicalMemory.h"
#include "PageTableEntry.h"

// Here is the using statement that is required by this header file.
using namespace std;

// Creating a class called PageTable, and this class will be used in the main function.
class PageTable
{
  // Declaring the public methods for the PageTable class.
  public:
     PageTable(PhysicalMemory * pmIn, int tableSize);
     void reference(int pageID, string item);
     int getFaults() const;
     void printTable() const;
     void reset();

  // Declaring the private methods and data members/structures for the PageTable class.
  private:
     vector <PageTableEntry> entryList;
     int numFaults;
     PhysicalMemory* mainPhysicalMemory;
     void pageFault(int pageID, string item);
     void updateReplacedEntry(int replacedIndex, int pageID);
};

#endif   // Here is the ending header guard for the PageTable.h file.
