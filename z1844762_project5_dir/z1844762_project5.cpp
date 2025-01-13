/********************************************************************
 CSCI 480 - Assignment 5 - Spring 2021

 Progammer: Sairamasubash Muppalaneni

 Z-ID:      Z1844762

 Section:   1

 TA:        Satya Keerthi Challa

 Date Due:  4/10/21, 11:59 PM

 Purpose:   The purpose of this Programming Assignment is to write a
            C++ program that simulates a paging-based virtual memory
            system. Also, this file includes three additional files
            that are called PageTableEntry.h, PhysicalMemory.h, and
            PageTable.h. This file includes two global variables
            called virtualMemoryList, and runList.

 *********************************************************************/

// Here are all the libraries that are required by this program.
#include <fstream>
#include <iostream>
#include <stdlib.h>
#include <string>
#include <vector>

// Here are all the additional files that are required by this program.
#include "PageTableEntry.h"
#include "PhysicalMemory.h"
#include "PageTable.h"

// Here is the using statement that is required by this header file.
using namespace std;

// Here are the global variables that are required by this program.
vector <string> virtualMemoryList;
vector <string> runList;

/***************************************************************
 Function:  loadVirtualMemory

 Use:       We are using this loadVirtualMemory function to load
            the virtual memory with each of the items while using
            the push_back function on the virtualMemoryList string
            vector.

 Arguments: none

 Returns:   none

 Notes:     This is the loadVirtualMemory function, there is another
            function called loadRunList which is similer
            to this function.
 ***************************************************************/
void loadVirtualMemory()
{
  // Here we are pushing back many items into the virtualMemoryList string vector.
  virtualMemoryList.push_back("main");
  virtualMemoryList.push_back("load");
  virtualMemoryList.push_back("tax");
  virtualMemoryList.push_back("update");
  virtualMemoryList.push_back("print");
  virtualMemoryList.push_back("holding");
  virtualMemoryList.push_back("activity");
  virtualMemoryList.push_back("net");
}

/***************************************************************
 Function:  loadRunList

 Use:       We are using this loadRunList function to load each
            of the items to be referenced while using the push_back
            function on the runList string vector.

 Arguments: none

 Returns:   none

 Notes:     This is the loadRunList function, there is another
            function called loadVirtualMemory which is similer
            to this function.
 ***************************************************************/
void loadRunList()
{
  // Here we are pushing back many items into the runList string vector.
  runList.push_back("main");
  runList.push_back("load");
  runList.push_back("holding");
  runList.push_back("main");
  runList.push_back("activity");
  runList.push_back("net");
  runList.push_back("main");
  runList.push_back("update");
  runList.push_back("holding");
  runList.push_back("net");
  runList.push_back("activity");
  runList.push_back("main");
  runList.push_back("tax");
  runList.push_back("net");
  runList.push_back("main");
  runList.push_back("print");
  runList.push_back("net");
}

/***************************************************************
 Function:  itemToPageID

 Use:       We are using this itemToPageID function to accept a
            string item name, and return the item's matching
            virtual page index.

 Arguments: itemIn - This parameter is a string variable.

 Returns:   return i - Returning the current virtual memory list index.
            return -1 - Returning this when no match is found.

 Notes:     Other then the loadVirtualMemory, loadRunList, and
            main functions, this is the only other function in
            this file.
 ***************************************************************/
int itemToPageID(string itemIn)
{
  // For each item in the virtual memory list.
  for(size_t i = 0; i < virtualMemoryList.size(); i++)
  {
     // Get the current virutal memory list item.
     string cItem = virtualMemoryList.at(i);

     // Compare the requested run list item with the current virtual memory list item.
     int sComp = cItem.compare(itemIn);

     // If the two items match.
     if(sComp == 0)
     {
        // Return the current virtual memory list index.
        return i;
     }
  }

  // If we get to here, no match was found.
  return -1;
}

/***************************************************************
 Function:  main

 Use:       We are using this main function to get command line
            arguments, and with these command line arguments, we
            are implementing the FIFO (First In First Out)
            page replacement algorithm.

 Arguments: argc - This is the parameter that will hold the current
            number of command line arguments passed in (counter).
            argv[] - This is the parameter that actually holds the
            command line arguments.

 Returns:   return 0 - whenever we get the return value of 0 in
            the output, we can then confirm that our program ran
            successfully.

 Notes:     If there is not an exact number of command line
            arguments that we want, we will set some default
            values in the places of those command line arguments.
 ***************************************************************/
int main(int argc, char *argv[])
{
  int physicalMemorySize = 4;   // Creating an integer variable called physicalMemorySize and setting this variable equal to 4.

  // If there are more then 2 command line arguments.
  if(argc > 1)
  {
     physicalMemorySize = atoi(argv[1]);   // Setting argument physicalMemorySize equal to argument 2.
  }

  cout << "Welcome!" << endl;

  // Calling the loadVirtualMemory, and the loadRunList functions.
  loadVirtualMemory();
  loadRunList();

  // 1 for FIFO.  LRU can be 0 or other numbers.
  PhysicalMemory physicalMem(physicalMemorySize, 1);
  PageTable pTable(&physicalMem, virtualMemoryList.size());

  cout << "FIFO with " << physicalMemorySize << " physical frames" << endl;

  // For each item in the run list
  for(size_t i = 0; i < runList.size(); i++)
  {
     // Fetch the virtual index for the string item.
     int virtualIdx = itemToPageID(runList.at(i));

     // If the virtual frame index was found.
     if(virtualIdx != -1)
     {
        // Reference the item at the specified virtual index.
        pTable.reference(virtualIdx, virtualMemoryList.at(virtualIdx));
     }
     else   // Otherwise the item to virtual frame index failed.
     {
        // Print an error message and break.
        cout << "Failed to convert reference to virtual memory: " << runList.at(i) << endl;
        break;
     }
  }

  cout << "Main: now print" << endl << endl;

  // Print physical memory and page table.
  physicalMem.printMemory();
  pTable.printTable();

  return 0;   // Returning 0 to see if the program ran successfully.
}
