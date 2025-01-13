/********************************************************************
 CSCI 480 - Assignment 4 - Spring 2021

 Progammer: Sairamasubash Muppalaneni

 Z-ID:      z1844762

 Section:   1

 TA:        Satya Keerthi Challa

 Date Due:  3/20/21, 11:59 PM

 Purpose:   The purpose of this Programming Assignment is to create
            several reader and writer threads. With these threads, we
            will loop for several iterations for both reading and writing.
            Also, the numbers of both the reader/writer threads, and the
            number of iterations have to be passed in as command line
            arguments (so there are three command line arguments in total).

 *********************************************************************/

// Here are all the libraries that are required by this program.
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <iostream>
#include <unistd.h>
#include <string>

// Here are all the using statements that are required by this program.
using std::cout;
using std::endl;
using std::string;

// Here is the global string variable that we will be working with in both the reader and writer functions.
string writerString1 = "All work and no play makes Jack a dull boy.";

// Here are the two integer global variables that will be used in this program.
int readCounter = 0;
int NUM_ITERATION = 0;

// Here are the two global Semaphores that will be used in this program.
sem_t rw_sem;
sem_t cs_sym;

// Creating the two function prototypes for this program.
void *reader(void *param);
void *writer(void *param);


/***************************************************************
 Function:  main

 Use:       We are using this main function to get the command line
            arguments, initialize the semaphores, create reader and
            writer threads, wait for the reader and writer threads
            to complete, and cleanup and exit at the end.

 Arguments: argc - This is the parameter that will hold the current
            number of command line arguments passed in (counter).
            argv[] - This is the parameter that actually holds the
            command line arguments.

 Returns:   none

 Notes:     If there is not an exact number of command line
            arguments that we want, we will set some default
            values in the places of those command line arguments.
 ***************************************************************/
int main(int argc, char *argv[])
{
  // Creating the two integer variables that will hold the values of arguments one and two.
  int argumentOne;
  int argumentTwo;

  // If the argument counter is equal to three, we are storing those arguments in different variables.
  if(argc == 4)
  {
     argumentOne = atoi(argv[1]);
     argumentTwo = atoi(argv[2]);
     NUM_ITERATION = atoi(argv[3]);
  }
  else   // If the argument counter is not equal to three, we are setting a default value for those spots.
  {
     argumentOne = 1;
     argumentTwo = 1;
     NUM_ITERATION = 43;
  }

  // Initializing the rw_sem global semaphore that we created above (also while error checking).
  if(sem_init(&rw_sem, 0, 1) != 0)
  {
     printf("Error: when we are Initializing the rw_sem semaphore.\n");
  }

  // Initializing the cs_sym global semaphore that we created above (also while error checking).
  if(sem_init(&cs_sym, 0, 1) != 0)
  {
     printf("Error: when we are Initializing the cs_sym semaphore.\n");
  }

  // Initializing both the readers and writers threads.
  pthread_t readers[argumentOne];
  pthread_t writers[argumentTwo];

  // Creating two local variables that will be used in loops, and that will also be used to do error checking.
  int rc;
  long t;

  printf("*** Reader-Writer Problem Simulation ***\n");   // Printing out the header for the entire program.
  printf("Number of reader threads: %d\n", argumentOne);   // Printing out the number of reader threads.
  printf("Number of writer threads: %d\n", argumentTwo);   // Printing out the number of writer threads.
  printf("Number of iterations per thread: %d\n", NUM_ITERATION);   // Printing out the number of iterations per thread.

  // Here we are creating the reader threads (also while error checking).
  for(t = 0; t < argumentOne; t++)
  {
     rc = pthread_create(&readers[t], NULL, reader, (void *)t);

     // We are using an if statement to do the error checking.
     if(rc)
     {
        printf("ERROR: the return code from pthread_create() for the readers thread is %d.\n", rc);
        exit(-1);
     }
  }

  // Here we are creating the writer threads (also while error checking).
  for(t = 0; t < argumentTwo; t++)
  {
     rc = pthread_create(&writers[t], NULL, writer, (void *)t);

     // We are using an if statement to do the error checking.
     if(rc)
     {
        printf("ERROR: the return code from pthread_create() for the writers thread is %d.\n", rc);
        exit(-1);
     }
  }

  // Here we are waiting for the reader threads to complete (also while error checking).
  for(int i = 0; i < argumentOne; i++)
  {
     if(pthread_join(readers[i], NULL) != 0)
     {
        printf("Error: when calling pthread_join for one of the readers threads.\n");
     }
  }

  // Here we are waiting for the writer threads to complete (also while error checking).
  for(int i = 0; i < argumentTwo; i++)
  {
     if(pthread_join(writers[i], NULL) != 0)
     {
        printf("Error: when calling pthread_join for one of the writers threads.\n");
     }
  }

  // Here we are destroying the rw_sem semaphore (also while error checking).
  if(sem_destroy(&rw_sem) != 0)
  {
     printf("Error: when destroying the rw_sem semaphore.\n");
  }

  // Here we are destroying the cs_sym semaphore (also while error checking).
  if(sem_destroy(&cs_sym) != 0)
  {
     printf("Error: when destroying the cs_sym semaphore.\n");
  }

  printf("All threads are done.\nResources cleaned up.\n");   // Printing out the endinng statements for the entire program.

  pthread_exit(NULL);   // Here we are exiting all of the threads in the main function.
}


/***************************************************************
 Function:  reader

 Use:       The purpose of this reader function is to loop through
            a number of iterations. This function also prints out
            the content of the shared string, while also printing
            out the current readcount value when the value increments
            or when the value decrements.

 Arguments: param - This is the parameter that will be used to tell
            which reader is currently reader (after being converted
            into a long integer).

 Returns:   none

 Notes:     This is the reader function that is required by this
            program, the writer function that is also required is
            right below this function.
 ***************************************************************/
void *reader(void *param)
{
  // Here we are converting the reader funtions parameter into a long integer value.
  long readerNumber;
  readerNumber = (long)param;

  int intValue;   // This integer variable will be used as the second parameter for the getvalue function.

  // Using a for loop to go through all of the iterations in the reader function (the number of iterations are from the command line).
  for(int i = 0; i < NUM_ITERATION; i++)
  {
     sem_wait(&cs_sym);   // Calling the sem_wait function in the reader function, while passing in the cs_sym semaphore as a parameter.

     readCounter++; // Here we are incrementing the readCounter global integer variable.

     printf("read_count increments to: %i.\n", readCounter);

     // If the readCounter variable is equal to one, we are calling the sem_wait function in the reader function, while passing in the rw_sem semaphore as a parameter.
     if(readCounter == 1)
     {
        sem_wait(&rw_sem);
     }

     sem_post(&cs_sym);   // Calling the sem_post function in the reader function, while passing in the cs_sym semaphore as a parameter.

     printf("reader %ld is reading ... content : %s\n", readerNumber, writerString1.c_str());

     sem_wait(&cs_sym);   // Calling the sem_wait function in the reader function, while passing in the cs_sym semaphore as a parameter.

     readCounter--;   // Here we are decrementing the readCounter global integer variable.

     printf("read_count decrements to: %i.\n", readCounter);

     // Checking to see if the readCounter variable is equal to zero.
     if(readCounter == 0)
     {
        // Calling the sem_getvalue function to get the semaphore value before signaling the writer.
        sem_getvalue(&rw_sem, &intValue);
        printf("Last reader. Semaphore value before signaling writer: %d.\n", intValue);

        sem_post(&rw_sem);   // Calling the sem_post function in the reader function, while passing in the rw_sem semaphore as a parameter.

        // Calling the sem_getvalue function to get the semaphore value after signaling the writer.
        sem_getvalue(&rw_sem, &intValue);
        printf("Last reader. Semaphore value after signaling writer: %d.\n", intValue);
     }

     sem_post(&cs_sym);   // Calling the sem_post function in the reader function, while passing in the cs_sym semaphore as a parameter.

     sleep(1);   // Making the reader function sleep for one second.
  }

  pthread_exit(NULL);   // Here we are exiting all of the threads in the reader function.
}


/***************************************************************
 Function:  writer

 Use:       The purpose of this writer function is to loop through
            a number of iterations, write while chopping off the last
            character of a string, print out a message saying that
            the writer is writing, and sleep for one second.

 Arguments: param - This is the parameter that will be used to tell
            which writer is currently writing (after being converted
            into a long integer).

 Returns:   none

 Notes:     This is the writer function that is required by this
            program, the reader function that is also required is
            right above this function.
 ***************************************************************/
void *writer(void *param)
{
  // Here we are converting the writer funtions parameter into a long integer value.
  long writerNumber;
  writerNumber = (long)param;

  // Using a for loop to go through all of the iterations in the writer function (the number of iterations are from the command line).
  for(int i = 0; i < NUM_ITERATION; i++)
  {
     sem_wait(&rw_sem);   // Calling the sem_wait function in the writer function, while passing in the rw_sem semaphore as a parameter.

     writerString1.pop_back();   // Chopping off the last character of the string by calling the pop_back function.

     printf("writer %ld is writing ...\n", writerNumber);   // Here we are printing out a message that says that a writer is currently writing.

     sem_post(&rw_sem);   // Calling the sem_post function in the writer function, while passing in the rw_sem semaphore as a parameter.

     sleep(1);   // Making the writer function sleep for one second.
  }

  pthread_exit(NULL);   // Here we are exiting all of the threads in the writer function.
}
