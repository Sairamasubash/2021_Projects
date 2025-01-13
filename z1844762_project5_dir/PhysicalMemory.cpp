/********************************************************************
 CSCI 480 - Assignment 5: PhysicalMemory.cpp - Spring 2021

 Progammer: Sairamasubash Muppalaneni

 Z-ID:      Z1844762

 Date Due:  4/10/21, 11:59 PM

 Purpose:   The purpose of this PhysicalMemory.cpp  file is to
            initialize the PhysicalMemory, access, printMemory, swapIn,
            getNextAvailableFrame, and isFull functions that are declared
            in the PhysicalMemory.h header file.

 *********************************************************************/

// Here is the additional file that is required by this PhysicalMemory.cpp file.
#include "PhysicalMemory.h"

/***************************************************************
 Function:  PhysicalMemory

 Use:       We are using this PhysicalMemory function to initialize
            all the data and data structure in the class.

 Arguments: memorySize - This is an integer variable.
            algorithmIn - This is an integer variable.

 Returns:   none

 Notes:     This is the first function that is initialized in this
            PhysicalMemory.cpp file.
 ***************************************************************/
PhysicalMemory::PhysicalMemory(int memorySize, int algorithmIn)
{
  // Here we initializing all the data members/structures.
  memoryList = vector <string> (memorySize);
  timeList.resize(memorySize);
  currentTimeIndex = 0;
}

/***************************************************************
 Function:  access

 Use:       We are using this access function to access the frame,
            update the time stamp, and update the time list.

 Arguments: frameID - This is an integer variable.

 Returns:   none

 Notes:     This is the second function that is initialized in this
            PhysicalMemory.cpp file.
 ***************************************************************/
void PhysicalMemory::access(int frameID)
{
  cout << "Physical: Accessed frameID: " << frameID << " contains: " << memoryList[frameID] << endl;   // Here we are printing out the frameID and what the frameID contains.

  currentTimeIndex++;   // Here we are incrementing the currentTimeIndex variable.
}

/***************************************************************
 Function:  printMemory

 Use:       We are using this printMemory function to print out
            the physical memory layout.

 Arguments: none

 Returns:   none

 Notes:     This is the third function that is initialized in this
            PhysicalMemory.cpp file.
 ***************************************************************/
void PhysicalMemory::printMemory() const
{
  size_t i = 0;   // Creating a size_t variable to use it in a while loop.

  // While i is less then the size of the memoryList.
  while(i < memoryList.size())
  {
     // Print out the memory frame and what that memory frame contains.
     cout << "Physical: Memory Frame:: " << i << " contains: " << memoryList[i] << endl;

     i++;   // Here we are incrementing the i variable.
  }
}

/***************************************************************
 Function:  swapIn

 Use:       We are using this swapIn function to return the frameID
            of the item just swapped in.

 Arguments: item - This is a string variable

 Returns:   frameID - Returning the frameID of the item just swapped in.

 Notes:     This is the fourth function that is initialized in this
            PhysicalMemory.cpp file.
 ***************************************************************/
int PhysicalMemory::swapIn(string item)
{
  int frameID = getNextAvailableFrame();   // Here we are calling the getNextAvailableFrame function.

  memoryList[frameID] = item;   // Setting the memoryList[frameID] equal to the item variable.
  currentTimeIndex++;   // Here we are incrementing the currentTimeIndex variable.
  timeList[frameID] = currentTimeIndex;   // Setting the timeList[frameID] equal to the currentTimeIndex variable.

  return frameID;   // Returing the frameID of the item just swapped in.
}

/***************************************************************
 Function:  getNextAvailableFrame

 Use:       We are using this getNextAvailableFrame function to
            get a frame, either available or via replacement.

 Arguments: none

 Returns:   nextAvailableFrame - Return value when memory is full.
            nextAvailableFrame2 - Return value when memory is not full.

 Notes:     This is the fifth function that is initialized in this
            PhysicalMemory.cpp file.
 ***************************************************************/
int PhysicalMemory::getNextAvailableFrame()
{
  // Creating four integer variables called nextAvailableFrame, nextAvailableFrame2, i, and j.
  int nextAvailableFrame;
  int nextAvailableFrame2;
  size_t i;
  size_t j;

  // Checking to see if memory is full.
  if(isFull())
  {
     nextAvailableFrame = 0;   // Creating an integer variable called nextAvailableFrame and setting it equal to 0.

     i = 1;   // Creating a size_t variable to use it in a while loop.

     // While i is less than the size of the timeList.
     while(i < timeList.size())
     {
        // Checking to see if timeList[i] is less than timeList[nextAvailableFrame].
        if(timeList[i] < timeList[nextAvailableFrame])
        {
           nextAvailableFrame = i;   // Setting nextAvailableFrame equal to i.
        }

        i++;   // Here we are incrementing the i variable.
     }

     return nextAvailableFrame;   // Retruning the nextAvailableFrame variable.
  }
  else   // If memory is not full.
  {
     nextAvailableFrame2 = 0;   // Creating an integer variable called nextAvailableFrame2 and setting it equal to 0.

     j = 0;   // Creating another size_t variable to use it in a while loop.

     // While j is less than the size of the timeList.
     while(j < timeList.size())
     {
        // Checking to see if timeList[j] is equal to 0;
        if(timeList[j] == 0)
        {
           nextAvailableFrame2 = j;   // Setting nextAvailableFrame2 equal to i.

           break;   // Using the break keyword to exit the while loop.
        }

        j++;   // Here we are incrementing the j variable.
     }

     return nextAvailableFrame2;   // Retruning the nextAvailableFrame2 variable.
  }
}

/***************************************************************
 Function:  isFull

 Use:       We are using this isFull function to check if the
            memory is full.

 Arguments: none

 Returns:   memoryFull - Returning the memoryFull booleen variable.

 Notes:     This is the sixth function that is initialized in this
            PhysicalMemory.cpp file.
 ***************************************************************/
bool PhysicalMemory::isFull()
{
  bool memoryFull = true;    // Creating the memoryFull booleen variable.

  // If the memoryList capacity is greater than the memoryList size.
  if(memoryList.capacity() > memoryList.size())
  {
     memoryFull = false;   // Setting the memoryFull variable equal to false.
  }

  return memoryFull;   // Returning the memoryFull booleen variable.
}
