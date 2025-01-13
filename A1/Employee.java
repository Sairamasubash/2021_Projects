/********************************************************************************
 CSCI 470     Assignment 1 - Employee class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 1/24/2021

 Purpose: The purpose of this Employee class is to create three instance variables,
 a counstructor that initializes the three instance variables, and the set
 and get accessor methods for the three instance variables. The components of this
 Employee class will mostly be used/called in the main DriverTest function (just
 like the Date class that we created).
 *******************************************************************************/
class Employee
{
  // Here we are creating the three private string/double instance variables (called firstName, lastName, and monthlySalary).
  private String firstName;
  private String lastName;
  private double monthlySalary;

  // Building a constructor that initializes the three instance variables (assuming all the value are correctly provided.)
  Employee(String inFname, String inLname, Double inMsalary)
  {
    this.firstName = inFname;
    this.lastName = inLname;
    this.monthlySalary = inMsalary;
  }

  // Creating a set accessor method for the instance variable called firstName.
  void setFname(String inFname)
  {
    this.firstName = inFname;
  }

  // Creating a set accessor method for the instance variable called lastName.
  void setLname(String inLname)
  {
    this.lastName = inLname;
  }

  // Creating a set accessor method for the instance variable called monthlySalary.
  void setMsalary(double inMsalary)
  {
    // Making sure that the monthlySalary variable is a positive number.
    if(monthlySalary >= 0.00)
    {
      this.monthlySalary = inMsalary;
    }
  }

  // Creating a get accessor method for the instance variable called firstName.
  String getFname()
  {
    return firstName;
  }

  // Creating a get accessor method for the instance variable called lastName.
  String getLname()
  {
    return lastName;
  }

  // Creating a get accessor method for the instance variable called monthlySalary.
  double getMsalary()
  {
    return monthlySalary;
  }
}
