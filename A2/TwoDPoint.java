/********************************************************************************
 CSCI 470     Assignment 2 - TwoDPoint class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 2/7/2021

 Purpose: The purpose of this TwoDPoint class is to create two public instance
 variables, and a counstructor that initializes the two instance variables. The
 components of this TwoDPoint class will mostly be used/called in the main DriverTest
 function, and also the Line class that we created (basically in the second
 constructor of the Line class).
 *******************************************************************************/

package muppalanenilinepackage;   // Stating that this TwoDPoint class is in the package called muppalanenilinepackage.

public class TwoDPoint   // Here we are creating the TwoDPoint class.
{
   // Declaring the two public instance variables.
   public int x;
   public int y;

   //  Building a TwoDPoint constructor that initializes the two public instance variables.
   public TwoDPoint(int xValue, int yValue)
   {
      this.x = xValue;
      this.y = yValue;
   }
}
