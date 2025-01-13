/********************************************************************************
 CSCI 470     Assignment 1 - Date class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 1/24/2021

 Purpose: The purpose of this Date class is to create three instance variables,
          a counstructor that initializes the three instance variables, the set
          and get accessor methods for the three instance variables, and a
          displayDate method that displays any given date in a formatted fashion.
          The components of this Date class will mostly be used/called in
          the main DriverTest function (just like the Employee class).
 *******************************************************************************/
class Date
{
  // Here we are creating the three private integer instance variables (called month, day, and year).
  private int month;
  private int day;
  private int year;

  // Building a constructor that initializes the three instance variables (assuming all the value are correctly provided.)
  Date(int inMonth, int inDay, int inYear)
  {
    this.month = inMonth;
    this.day = inDay;
    this.year = inYear;
  }

  // Creating a set accessor method for the instance variable called month.
  void setMonth(int inMonth)
  {
    this.month = inMonth;
  }

  // Creating a set accessor method for the instance variable called day.
  void setDay(int inDay)
  {
    this.day = inDay;
  }

  // Creating a set accessor method for the instance variable called year.
  void setYear(int inYear)
  {
    this.year = inYear;
  }

  // Creating a get accessor method for the instance variable called month.
  int getMonth()
  {
    return month;
  }

  // Creating a get accessor method for the instance variable called day.
  int getDay()
  {
    return day;
  }

  // Creating a get accessor method for the instance variable called year.
  int getYear()
  {
    return year;
  }

  // Building a method called displayDate that displays the month, day, and year instance variables seperated by forward slashes.
  void displayDate()
  {
    System.out.println(+ month + "/" + day + "/" + year);
  }
}
