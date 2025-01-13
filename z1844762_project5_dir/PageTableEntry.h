/****************************************************************
 FILE:      PageTableEntry.h

 AUTHOR:    Sairamasubash Muppalaneni

 Z-ID:      Z1844762

 DUE DATE:  April 10, 2021

 PURPOSE:   The purpose of the PageTableEntry.h file is to create a
            structure called PageTableEntry which will be used in
            the PageTable class. This PageTableEntry structure has
            a total of two variables created inside of it.
 ****************************************************************/

// Here are the opening header guards for the PageTableEntry.h file.
#ifndef PageTableEntry_H
#define PageTableEntry_H

// Creating a structure called PageTableEntry, and this structure will be used in the PageTable class.
struct PageTableEntry
{
  // Here we are creating a boolean and an integer variable that will be used in the PageTable class.
  bool valid;
  int physicalMemoryIndex;
};

#endif   // Here is the closing header guard for the PageTableEntry.h file.
