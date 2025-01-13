/********************************************************************
 CSCI 480 - Assignment 5: PageTable.cpp - Spring 2021

 Progammer: Sairamasubash Muppalaneni

 Z-ID:      Z1844762

 Date Due:  4/10/21, 11:59 PM

 Purpose:   The purpose of this PageTable.cpp file is to initialize
            the PageTable, reference, getFaults, printTable, and
            pageFault functions that are declared in the PageTable.h
            header file.

 *********************************************************************/

// Here is the additional file that is required by this PageTable.cpp file.
#include "PageTable.h"

/***************************************************************
 Function:  PageTable

 Use:       We are using this PageTable function to initialize
            all entries to invalid.

 Arguments: pmIn - This is an object of the PhysicalMemory class.
            tableSize - This is an integer variable.

 Returns:   none

 Notes:     This is the first function that is initialized in this
            PageTable.cpp file.
 ***************************************************************/
PageTable::PageTable(PhysicalMemory* pmIn, int tableSize)
{
  numFaults = 0;   // Setting the numFaults variable equal to 0.

  mainPhysicalMemory = pmIn;   // Setting the mainPhysicalMemory variable equal to the pmIn variable.

  size_t i = 0;   // Creating a size_t variable to use it in a while loop.

  entryList.resize(tableSize);  // Resizing the entryList to the value located in the tableSize .

  // While i is less than the size of the entryList.
  while(i < entryList.size())
  {
     entryList[i].valid = false;   // Setting entryList[i].valid equal to false.

     entryList[i].physicalMemoryIndex = -1;   // Setting entryList[i].physicalMemoryIndex equal to -1.

     i++;   // Here we are incrementing the i variable.
  }
}

/***************************************************************
 Function:  reference

 Use:       We are using this reference function to reference a
            logical page, if not in memory, calling the pageFault
            function.

 Arguments: pageID - This is an integer variable.
            item - This is a string variable.

 Returns:   none

 Notes:     This is the second function that is initialized in this
            PageTable.cpp file.
 ***************************************************************/
void PageTable::reference(int pageID, string item)
{
  // If the entryList[pageID] is valid.
  if(entryList[pageID].valid)
  {
     mainPhysicalMemory->access(entryList[pageID].physicalMemoryIndex);   // Here we are calling the access function.
  }
  else   // If the entryList[pageID] is not valid.
  {
     pageFault(pageID, item);   // Here we are calling the pageFault function.
  }
}

/***************************************************************
 Function:  getFaults

 Use:       We are using this getFaults function to return number
            of faults.

 Arguments: none

 Returns:   numFaults - Returning the number of faults.

 Notes:     This is the third function that is initialized in this
            PageTable.cpp file.
 ***************************************************************/
int PageTable::getFaults() const
{
  return numFaults;   // Returning the numFaults variable.
}

/***************************************************************
 Function:  printTable

 Use:       We are using this printTable function to print the
            layout of page table.

 Arguments: none

 Returns:   none

 Notes:     This is the fourth function that is initialized in this
            PageTable.cpp file.
 ***************************************************************/
void PageTable::printTable() const
{
  size_t i = 0;   // Creating a size_t variable to use it in a while loop.

  // While i is less than the size of the entryList.
  while(i < entryList.size())
  {
     // Printing out the layout of the page table.
     cout << "PageTable: Index: " << i << " : Physical Index: " << entryList[i].physicalMemoryIndex << " : In Use: " << boolalpha << entryList[i].valid << endl;

     i++;   // Here we are incrementing the i variable.
  }

  // Printing out the current number of page faults.
  cout << "PageTable: Current number of page faults: " << getFaults() << endl;
}

/***************************************************************
 Function:  pageFault

 Use:       We are using this pageFault function to increment numFaults.
            We need to swap in the item into physical memory by
            calling the swapIn function of the PhysicalMemory class.

 Arguments: pageID - This is an integer variable.
            item - This is a string variable.

 Notes:     This is the fifth function that is initialized in this
            PageTable.cpp file.
 ***************************************************************/
void PageTable::pageFault(int pageID, string item)
{
  numFaults++;   // Here we are incrementing the numFaults variable.

  int newFrameSwapped = mainPhysicalMemory->swapIn(item);   // Here we are calling the swapIn function.

  size_t i = 0;   // Creating a size_t variable to use it in a while loop.

  // While i is less than the size of the entryList.
  while(i < entryList.size())
  {
     // If entryList[i].physicalMemoryIndex equals newFrameSwapped.
     if(entryList[i].physicalMemoryIndex == newFrameSwapped)
     {
        entryList[i].physicalMemoryIndex = -1;   // Setting entryList[i].physicalMemoryIndex equal to -1.

        entryList[i].valid = false;   // Setting entryList[i].valid equal to false.

        break;   // Using the break keyword to exit the while loop.
     }

     i++;   // Here we are incrementing the i variable.
  }

  entryList[pageID].valid = true;   // Setting entryList[pageID].valid equal to true.

  entryList[pageID].physicalMemoryIndex = newFrameSwapped;   // Setting entryList[pageID].physicalMemoryIndex equal to newFrameSwapped.

  // Printing out that a page fault occurred, and what item is stored.
  cout << "PageTable: page fault occurred" << endl;
  cout << "Physical: Stored: " << item << endl;
}
