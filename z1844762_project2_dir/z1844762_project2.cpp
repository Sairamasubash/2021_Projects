/********************************************************************
 CSCI 480 - Assignment 2 - Spring 2021

 Progammer: Sairamasubash Muppalaneni

 Z-ID:      z1844762

 Section:   1

 TA:        Satya Keerthi Challa

 Date Due:  2/16/21, 11:59 PM

 Purpose:   The purpose of this Programming Assignment is to practice the
            use of the fork() system call and a lot of other system calls
            to implement a microshell in C/C++. We are also practicing the
            FCFS (First Come First Serve) CPU scheduling algorithm. Plus,
            there are many other CPU schduling algorithms, but in this program,
            we are only focusing on the FCFS CPU scheduling algorithm.

 *********************************************************************/

// Here are all the libraries that are required by this program.
#include <iostream>
#include <cstring>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>
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
Function:  assignOneOutput

Use:       We are using this assignOneOutput function to open and
           read files called ostype, hostnname, osrelease, version,
           cpuinfo, uptime, stat, and swaps. All of these file are
           located in the proc directory (plus these are virtual files).

Arguments: none

Returns:   none

Notes:     This is basically the code from Assignment 1 and we are
           just placing it here because we will be printing out the
           Assignment 1 output whenever ls_sys is the given command.
***************************************************************/
void assignOneOutput()
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

  double turingSwapSizeMB = stod(inputDataE) / 1024;     // Converting the turing swap device size from kilobytes to megabytes.

  cout << endl;     // Printing out an end line statement.
  cout << "E: Questions about the size of turing’s swap device:" << endl;     // The beginning of question E (the fifth set of questions).
  cout << "1. The size of turing's swap device in megabytes is " << fixed << setprecision(3) << turingSwapSizeMB << " MB." << endl;     // Answering question one in the fifth set of questions.

  inFileE.close();     // Closing the /proc/swaps file.
}

/***************************************************************
 Function:  firstComeFirstServe

 Use:       We are using this firstComeFirstServe function to do
            the first come first serve (FCFS) CPU scheduling
            simulation/calculation. The number of processes being
            used will be given as an argument for the fcfs command.

 Arguments: numOfProcesses - An integer value that holds the number
            of processes that are being used in the first come first
            serve (FCFS) CPU scheduling algorithm.

 Returns:   none

 Notes:     If the numOfProcesses integer variable is not given
            along with the fcfs command, we are assuming that there
            are five processes being used.
 ***************************************************************/
void firstComeFirstServe(int numOfProcesses)
{
  // Creating the cpuBurst, totWaitTime, and avgWaitTime integer variables to process the first come first serve (FCFS) CPU scheduling simulation.
  int cpuBurst = 0;
  int totWaitTime = 0;
  int avgWaitTime = 0;

  srand(10);   // Creating seed 10 to generate random numbers.

  // Printing out the number of processes being used for the first come first serve (FCFS) CPU scheduling simulation.
  cout << "FCFS CPU scheduling simulation with " << numOfProcesses << " processes." << endl;

  for(int i = 1; i <= numOfProcesses; i++)   // Here is the first for loop that we are using to do the first come first serve (FCFS) CPU scheduling simulation.
  {
     cpuBurst = rand() % 100 + 1;   // Randomly generating integers between 1 and 100.

     cout << "CPU burst: " << cpuBurst << " ms" << endl;   // Printing out the CPU burst time for each process.

     if(i != numOfProcesses)   // Using an if statement to make sure that the last process is not being used.
     {
        for(int j = numOfProcesses; j > i; j--)   // Here is the second for loop that we are using to do the first come first serve (FCFS) CPU scheduling simulation.
        {
           totWaitTime += cpuBurst;   // Here we are calculating the total waiting time for the first come first serve (FCFS) CPU scheduling simulation.
        }
     }
  }

  avgWaitTime = totWaitTime / numOfProcesses;   // Here we are calculating the average waiting time for the first come first serve (FCFS) CPU scheduling simulation.

  // Here we are printing out the total waiting time for the first come first serve (FCFS) CPU scheduling simulation.
  cout << "Total waiting time in the ready queue: " << totWaitTime << " ms" << endl;

  // Here we are printing out the average waiting time for the first come first serve (FCFS) CPU scheduling simulation.
  cout << "Average waiting time in the ready queue: " << avgWaitTime << " ms" << endl;
}

/***************************************************************
 Function:  main

 Use:       We are using this main function to get commands form
            the command line. This function also allows the use of
            command options/arguments through string token.
            Furthermore, there are additional custom commands that
            will be allowed (such as quit, ls_sys, and fcfs).

 Arguments: none

 Returns:   none

 Notes:     Most of the code for this program is located in the two
            functions that were created above, but there is a good
            amount of important code in this main function.
 ***************************************************************/
int main(void)
{
  // Creating the buf, pid, and status variables to get the commands from the command line.
  char buf[1024];
  pid_t pid;
  int status;

  // Creating the ptr, number, and command variables to use string token, which allows options/arguments to be given to the commands.
  char* ptr;
  int number = 0;
  char* command[128] = {};

  printf("myshell>");   // Printing out a command line prompt that says "myshell>" for the first time.

  // Using a while loop to process the buf array that holds the commands from the command line.
  while(fgets(buf, 1024, stdin) != NULL)
  {
     buf[strlen(buf) - 1] = 0;   // Here we are working with the length of the buf array that holds the commands from the command line.

     if((pid = fork()) < 0)   // If the process ID from the fork system call is less than 0, we are printing out an error message and terminating the program.
     {
        printf("ERROR: There is a problem with the fork system call.\n");
        exit(EXIT_FAILURE);
     }
     else if(pid==0)   // If the process ID from the fork system call is equal to 0, we know that there were no problems, so we are moving forward.
     {
        command[0] = strtok(buf, " ");   // Calling the strtok function to allow options/arguments for the commands.

        // Using a while loop to store both the command and the command's options/arguments in the command array.
        while((ptr=strtok(NULL, " ")) != NULL)
        {
           number++;   // Incrementing the number variable.
           command[number]=ptr;   // Storing the ptr (pointer) variable in the command array.
        }

        execvp(command[0], command);   // Using the execvp system call to work with the commands given from the command line.

        if(strcmp(command[0], "quit") == 0)   // Using an if statement to break the while loop if the keyword "quit" was entered as a command (for the first time).
        {
           break;
        }
        else if(strcmp(command[0], "ls_sys") == 0)   // If the command "ls_sys" is given, we will be calling the assignOneOutput function which prints out the Assignment 1 output.
        {
           assignOneOutput();
        }
        else if(strcmp(command[0], "") == 0)   // If nothing is given as a command, we will be printing out the "myshell>" prompt again by doing nothing in the else if statement.
        {
        }
        else if(strcmp(command[0], "fcfs") == 0)   // If the command "fcfs" is given, we will be doing the first come first serve (FCFS) CPU scheduling simulation/calculation.
        {
           if(command[1])   // If the fcfs command has an option/argument, we are calling the firstComeFirstServe function while passing in the fcfs option/argument as a parameter.
           {
              firstComeFirstServe(stoi(command[1]));
           }
           else   // If the fcfs command does not have an option/argument, we are calling the firstComeFirstServe function while passing in the integer 5 as a parameter.
           {
              firstComeFirstServe(5);
           }
        }
        else   // Here we are finally printing out some text that states that a given command could not execute properly.
        {
           cout << "couldn’t execute: ";   // Printing out text that says that a given command is invalid.

           // Creating a for loop to print out the contents of an entire array.
           for(int i = 0; command[i] != 0; i++)
           {
              cout << command[i] << " ";
           }

           cout << endl;   // Printing out an end line statement.
        }

        exit(127);   // Terminating the program with a code of 127;
     }

     // Using an if statement to break the while loop if the keyword "quit" was entered as a command (for the second time).
     if(strcmp(buf, "quit") == 0)
     {
        break;
     }

     // Calling the waitpid function in the parent process to wait for the completion of the commands.
     if((pid = waitpid(pid, &status, 0)) < 0)
     {
        printf("waitpid error");
     }

     printf("myshell>");   // Keep printing out a command line prompt that says "myshell>" until the keyword "quit" is entered.
  }

  exit(0);   // Terminating the program with a code of 0;
}

