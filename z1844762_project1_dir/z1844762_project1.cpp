/********************************************************************
CSCI 480 - Assignment 1 - Spring 2021

Progammer: Sairamasubash Muppalaneni

Z-ID:      z1844762

Section:   1

TA:        Satya Keerthi Challa

Date Due:  1/29/21, 11:59 PM

Purpose:   The purpose of this Programming Assignment is to open and
           read a lot of virtual files located in the proc directory.
           After we read these files, we will be using the information
           in these files to answer five different sets of questions.
           To complete this entire process we are using member functions
           from the C++ string class.

*********************************************************************/

// Here are all the libraries that are required by this program.
#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>

// Here are all the using statements that are required by this program.
using std::cout;
using std::string;
using std::ifstream;
using std::endl;
using std::getline;
using std::stod;
using std::stoi;
using std::setprecision;
using std::fixed;

/***************************************************************
Function:  main

Use:       We are using this main function to open and read files
           called ostype, hostnname, osrelease, version, cpuinfo,
           uptime, stat, and swaps. All of these file are located
           in the proc directory (plus these are virtual files).

Arguments: none

Returns:   return 0 - whenever we get the return value of 0 in
           the output, we can then confirm that our program was
           successful.

Notes:     Most of the code is located in this main function
           because there are no other functions in this program.
***************************************************************/

int main()
{
  // Creating the ifstream and input variables for Question A (the first set of questions).
  ifstream inFileA;
  string inputDataA;

  // Opening the /proc/sys/kernel/ostype file.
  inFileA.open("/proc/sys/kernel/ostype");

  // Checking to see if the /proc/sys/kernel/ostype file opened successfully.
  if(inFileA.fail())
  {
     cout << "The Following File Could Not Open: /proc/sys/kernel/ostype" << endl;
     exit(-1);
  }

  inFileA >> inputDataA;     // Reading the first item in the /proc/sys/kernel/ostype file.

  cout << "A: Questions about turing’s OS:" << endl;     // The beginning of question A (the first set of questions).
  cout << "1. The current ostype is " << inputDataA << "." << endl;     // Answering question one in the first set of questions.

  inFileA.close();    // Closing the /proc/sys/kernel/ostype file.

  // Opening the /proc/sys/kernel/hostname file.
  inFileA.open("/proc/sys/kernel/hostname");

  // Checking to see if the /proc/sys/kernel/hostname file opened successfully.
  if(inFileA.fail())
  {
     cout << "The Following File Could Not Open: /proc/sys/kernel/hostname" << endl;
     exit(-1);
  }

  inFileA >> inputDataA;     // Reading the first item in the /proc/sys/kernel/hostname file.

  cout << "2. The current hostname is " << inputDataA << "." << endl;     // Answering question two in the first set of questions.

  inFileA.close();     // Closing the /proc/sys/kernel/ostype file.

  // Opening the /proc/sys/kernel/osrelease file.
  inFileA.open("/proc/sys/kernel/osrelease");

  // Checking to see if the /proc/sys/kernel/osrelease file opened successfully.
  if(inFileA.fail())
  {
     cout << "The Following File Could Not Open: /proc/sys/kernel/osrelease" << endl;
     exit(-1);
  }

  inFileA >> inputDataA;     // Reading the first item in the /proc/sys/kernel/osrelease file.

  cout << "3. The current osrelease is " << inputDataA << "." << endl;     // Answering question three in the first set of questions.

  inFileA.close();     // Closing the /proc/sys/kernel/osrelease file.

  // Opening the /proc/sys/kernel/version file.
  inFileA.open("/proc/sys/kernel/version");

  // Checking to see if the /proc/sys/kernel/version file opened successfully.
  if(inFileA.fail())
  {
     cout << "The Following File Could Not Open: /proc/sys/kernel/version" << endl;
     exit(-1);
  }

  getline(inFileA, inputDataA);     // Reading the first line in the /proc/sys/kernel/version file.

  cout << "4. The current version is " << inputDataA << "." << endl;     // Answering question four in the first set of questions.

  inFileA.close();     // Closing the /proc/sys/kernel/version file.

  // Creating the ifstream and input variables for Question B (the second set of questions).
  ifstream inFileB;
  string inputDataB;

  // Opening the /proc/cpuinfo file.
  inFileB.open("/proc/cpuinfo");

  // Checking to see if the /proc/cpuinfo file opened successfully.
  if(inFileB.fail())
  {
     cout << "The Following File Could Not Open: /proc/cpuinfo" << endl;
     exit(-1);
  }

  // Creating the multiCoreChips, processorCounter, findProcessor, and findPhysicalID variables that we need to find/keep track of the processor, and the physical id.
  int multiCoreChips = 0;
  int processorCounter = 0;
  size_t findProcessor;
  size_t findPhysicalID;

  // Creating a while loop to read through the cpuinfo file (line by line).
  while(getline(inFileB, inputDataB))
  {
     findProcessor = inputDataB.find("processor");     // Finding the keyword "processor" in the cpuinfo file.

     // Incrementing the processorCounter each time the keyword "processor" is found in the cpuinfo file.
     if(findProcessor != string::npos)
     {
        processorCounter++;
     }

     findPhysicalID = inputDataB.find("physical id");     // Finding the keyword "physical id" in the cpuinfo file.

     // Checking to see if the keyword "physical id" is found in the cpuinfo file.
     if(findPhysicalID != string::npos)
     {
        // Using an if statement to find out the total number of physical multi-core chips that are being used by turing.
        if(stoi(inputDataB.substr(14, 1)) > multiCoreChips)
        {
           multiCoreChips = stoi(inputDataB.substr(14, 1));
        }
     }
  }

  cout << endl;     // Printing out an end line statement.
  cout << "B: Questions about turing's processors:" << endl;     // The beginning of question B (the second set of questions).
  cout << "1. Turing has " << processorCounter << " processors." << endl;     // Answering question one in the second set of questions.

  // Using and if statement to check if there is only one physical multi-core chip, or multiple physical multi-core chips.
  if((multiCoreChips + 1) == 1)
  {
     cout << "2. Turing has " << multiCoreChips + 1 << " physical multi-core chip." << endl;     // Answering question two in the second set of questions (if one physical multi-core chip).
  }
  else
  {
     cout << "2. Turing has " << multiCoreChips + 1 << " physical multi-core chips." << endl;     // Answering question two in the second set of questions (if multiple physical multi-core chip).
  }

  inFileB.close();     // Closing the /proc/cpuinfo file.

  // Opening the /proc/uptime file.
  inFileB.open("/proc/uptime");

  // Checking to see if the /proc/uptime file opened successfully.
  if(inFileB.fail())
  {
     cout << "The Following File Could Not Open: /proc/uptime" << endl;
     exit(-1);
  }

  inFileB >> inputDataB;     // Reading the first item in the /proc/uptime file.

  cout << "3. Turing has been up for " << fixed << setprecision(2) << inputDataB << " seconds." << endl;     // Answering question three in the second set of questions.

  // Creating the uptime, numOfDays, secondsInDay, numOfHours, secondsInHour, numOfMinutes, and secondsInMinute variables that are needed to get the number of days, hours, minutes, and seconds.
  double uptime = stod(inputDataB);
  int numOfDays = 0;
  int secondsInDay = 86400;
  int numOfHours = 0;
  int secondsInHour = 3600;
  int numOfMinutes = 0;
  int secondsInMinute = 60;

  // Using an if statement to figure out the number of days based on the uptime.
  if(uptime >= secondsInDay)
  {
     numOfDays = uptime / secondsInDay;
     uptime = uptime - (numOfDays * secondsInDay);
  }

  // Using an if statement to figure out the number of hours based on the uptime.
  if(uptime >= secondsInHour)
  {
     numOfHours = uptime / secondsInHour;
     uptime = uptime - (numOfHours * secondsInHour);
  }

  // Using an if statement to figure out the number of minutes based on the uptime.
  if(uptime >= secondsInMinute)
  {
     numOfMinutes = uptime / secondsInMinute;
     uptime = uptime - (numOfMinutes * secondsInMinute);
  }

  cout << "4. When expressed regularly, turing has been up for ";     // Answering question four in the second set of questions.

  // Using an if statement to print out the number of days for question four in the second set of questions.
  if(numOfDays == 1)
  {
     cout << numOfDays << " day, ";
  }
  else
  {
     cout << numOfDays << " days, ";
  }

  // Using an if statement to print out the number of hours for question four in the second set of questions.
  if(numOfHours == 1)
  {
     cout << numOfHours << " hour, ";
  }
  else
  {
     cout << numOfHours << " hours, ";
  }

  // Using an if statement to print out the number of minutes for question four in the second set of questions.
  if(numOfMinutes == 1)
  {
     cout << numOfMinutes << " minute, and ";
  }
  else
  {
     cout << numOfMinutes << " minutes, and ";
  }

  // Using an if statement to print out the number of seconds for question four in the second set of questions.
  if(uptime == 1)
  {
     cout << fixed << setprecision(2) << uptime << " second." << endl;
  }
  else
  {
     cout << fixed << setprecision(2) << uptime << " seconds." << endl;
  }

  inFileB.close();     // Closing the /proc/uptime file.

  // Creating the ifstream and input variables for Question C (the third set of questions).
  ifstream inFileC;
  string inputDataC;

  // Opening the /proc/cpuinfo file.
  inFileC.open("/proc/cpuinfo");

  // Checking to see if the /proc/cpuinfo file opened successfully.
  if(inFileC.fail())
  {
     cout << "The Following File Could Not Open: /proc/cpuinfo" << endl;
     exit(-1);
  }

  // Creating the second processor counter variable.
  int processorCounterTwo = 0;

  // Creating the findProcessorZero, findVendor, findModelName, and findAdressSize variables that are needed to use the find function.
  size_t findProcessorZero;
  size_t findVendor;
  size_t findModelName;
  size_t findAdressSize;

  // Creating the nameOfVendor, nameOfModel, nameOfPSize, and nameOfASize variables that are needed to store processor 0's information.
  string nameOfVendor;
  string nameOfModel;
  string nameOfPSize;
  string nameOfASize;

  // Creating a while loop to read through the cpuinfo file (line by line) for the second time.
  while(getline(inFileC, inputDataC))
  {
     findProcessorZero = inputDataC.find("processor");     // Finding the keyword "processor" in the cpuinfo file for the second time.

     // Incrementing the processorCounterTwo each time the keyword "processor" is found in the cpuinfo file for the second time.
     if(findProcessorZero != string::npos)
     {
        processorCounterTwo++;
     }

     findVendor = inputDataC.find("vendor_id");     // Finding the keyword "vendor_id" in the cpuinfo file.

     // Using an if statement to get processor 0's vendor.
     if((processorCounterTwo - 7) == 0 && findVendor != string::npos)
     {
        nameOfVendor = (inputDataC.substr(12, 12));
     }

     findModelName = inputDataC.find("model name");     // Finding the keyword "model name" in the cpuinfo file.

     // Using an if statement to get processor 0's model name.
     if((processorCounterTwo - 7) == 0 && findModelName != string::npos)
     {
        nameOfModel = (inputDataC.substr(13, 41));
     }

     findAdressSize = inputDataC.find("address sizes");     // Finding the keyword "address sizes" in the cpuinfo file.

     // Using an if statement to get processor 0's physical, and virtual address sizes.
     if((processorCounterTwo - 7) == 0 && findAdressSize != string::npos)
     {
        nameOfPSize = (inputDataC.substr(16, 7));
        nameOfASize = (inputDataC.substr(34, 7));
     }
  }

  cout << endl;     // Printing out an end line statement.
  cout << "C: Questions about turing's processor 0:" << endl;     // The beginning of question C (the third set of questions).
  cout << "1. Processor 0's vendor is " << nameOfVendor << "." << endl;     // Answering question one in the third set of questions.
  cout << "2. Processor 0's model name is " << nameOfModel << "." << endl;     // Answering question two in the third set of questions.
  cout << "3. Processor 0's physical address size is " << nameOfPSize << "." << endl;     // Answering question three in the third set of questions.
  cout << "4. Processor 0's virtual address size is " << nameOfASize << "." << endl;     // Answering question four in the third set of questions.

  inFileC.close();     // Closing the /proc/cpuinfo file.

  // Creating the ifstream and input variables for Question D (the fourth set of questions).
  ifstream inFileD;
  string inputDataD;

  // Opening the /proc/stat file.
  inFileD.open("/proc/stat");

  // Checking to see if the /proc/stat file opened successfully.
  if(inFileD.fail())
  {
     cout << "The Following File Could Not Open: /proc/stat" << endl;
     exit(-1);
  }

  // Creting the timeInUserMode, timeInSystemMode, and timeInIdle variables to hold the three times in a string.
  string timeInUserMode;
  string timeInSystemMode;
  string timeInIdle;

  // Creating the inUserSeconds, inSystemSeconds, and inIdleSeconds variables to hold the three times in a double after converting them from a string.
  double inUserSeconds;
  double inSystemSeconds;
  double inIdleSeconds;

  // Creating the numOfDays2, secondsInDay2, numOfHours2, secondsInHour2, numOfMinutes2, and secondsInMinute2 variables that are needed to get the number of days, hours, minutes, and seconds.
  int numOfDays2 = 0;
  int secondsInDay2 = 86400;
  int numOfHours2 = 0;
  int secondsInHour2 = 3600;
  int numOfMinutes2 = 0;
  int secondsInMinute2 = 60;

  // Calling the getline functionn twice to get the first two lines in the /proc/stat file.
  getline(inFileD, inputDataD);
  getline(inFileD, inputDataD);

  // Reading the next two items in the third line of the /proc/stat file.
  inFileD >> inputDataD;
  inFileD >> inputDataD;

  // Getting processor 1's time spent in user mode, and converting that value from a string to a double.
  timeInUserMode = inputDataD;
  inUserSeconds = stod(inputDataD) / 100;

  // Once again, reading the next two items in the third line of the /proc/stat file.
  inFileD >> inputDataD;
  inFileD >> inputDataD;

  // Getting processor 1's time spent in system mode, and converting that value from a string to a double.
  timeInSystemMode = inputDataD;
  inSystemSeconds = stod(inputDataD) / 100;

  // Finally, reading the next item in the third line of the /proc/stat file.
  inFileD >> inputDataD;

  // Getting processor 1's time spent in idle, and converting that value from a string to a double.
  timeInIdle = inputDataD;
  inIdleSeconds = stod(inputDataD) / 100;

  cout << endl;     // Printing out an end line statement.
  cout << "D: Questions about turing's processor 1:" << endl;     // The beginning of question D (the fourth set of questions).
  cout << "1. Processor 1 has spent " << fixed << setprecision(2) << inUserSeconds << " seconds in user mode (not low-priority user mode)." << endl; // Answering question 1 in fouth set of questions.
  cout << "2. Processor 1 has spent " << fixed << setprecision(2) << inSystemSeconds << " seconds in system  mode." << endl;     // Answering question two in the fourth set of questions.
  cout << "3. Processor 1 has been idle for " << fixed << setprecision(2) << inIdleSeconds << " seconds." << endl;     // Answering question three in the fourth set of questions.

  // Using an if statement to figure out the number of days based on the time spent in idle.
  if(inIdleSeconds >= secondsInDay2)
  {
     numOfDays2 = inIdleSeconds / secondsInDay2;
     inIdleSeconds = inIdleSeconds - (numOfDays2 * secondsInDay2);
  }

  // Using an if statement to figure out the number of hours based on the time spent in idle.
  if(inIdleSeconds >= secondsInHour2)
  {
     numOfHours2 = inIdleSeconds / secondsInHour2;
     inIdleSeconds = inIdleSeconds - (numOfHours2 * secondsInHour2);
  }

  // Using an if statement to figure out the number of minutes based on the time spent in idle.
  if(inIdleSeconds >= secondsInMinute2)
  {
     numOfMinutes2 = inIdleSeconds / secondsInMinute2;
     inIdleSeconds = inIdleSeconds - (numOfMinutes2 * secondsInMinute2);
  }

  cout << "4. When expressed regularly, processor 1 has been idle for ";     // Answering question four in the fourth set of questions.

  // Using an if statement to print out the number of days for question four in the fourth set of questions.
  if(numOfDays2 == 1)
  {
     cout << numOfDays2 << " day, ";
  }
  else
  {
     cout << numOfDays2 << " days, ";
  }

  // Using an if statement to print out the number of hours for question four in the fourth set of questions.
  if(numOfHours2 == 1)
  {
     cout << numOfHours2 << " hour, ";
  }
  else
  {
     cout << numOfHours2 << " hours, ";
  }

  // Using an if statement to print out the number of minutes for question four in the fourth set of questions.
  if(numOfMinutes2 == 1)
  {
     cout << numOfMinutes2 << " minute, and ";
  }
  else
  {
     cout << numOfMinutes2 << " minutes, and ";
  }

  // Using an if statement to print out the number of seconds for question four in the fourth set of questions.
  if(inIdleSeconds == 1)
  {
     cout << fixed << setprecision(2) << inIdleSeconds << " second." << endl;
  }
  else
  {
     cout << fixed << setprecision(2) << inIdleSeconds << " seconds." << endl;
  }

  inFileD.close();     // Closing the /proc/stat file.

  // Creating the ifstream and input variables for Question E (the fifth set of questions).
  ifstream inFileE;
  string inputDataE;

  // Opening the /proc/swaps file.
  inFileE.open("/proc/swaps");

  // Checking to see if the /proc/swaps file opened successfully.
  if(inFileE.fail())
  {
     cout << "The Following File Could Not Open: /proc/swaps" << endl;
     exit(-1);
  }

  getline(inFileE, inputDataE);     // Calling the getline function to read the first line in the /proc/swaps file.

  // Reading the next three items in the second line of the /proc/swaps file.
  inFileE >> inputDataE;
  inFileE >> inputDataE;
  inFileE >> inputDataE;

  double turingSwapSizeMB = stod(inputDataE) / 1000;     // Converting the turing swap device size from kilobytes to megabytes.

  cout << endl;     // Printing out an end line statement.
  cout << "E: Questions about the size of turing’s swap device:" << endl;     // The beginning of question E (the fifth set of questions).
  cout << "1. The size of turing's swap device in megabytes is " << fixed << setprecision(3) << turingSwapSizeMB << " MB." << endl;     // Answering question one in the fifth set of questions.

  inFileE.close();     // Closing the /proc/swaps file.

  return 0;     // Returning the value 0 to see if the program ran successfully.
}
