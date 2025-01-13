/********************************************************************************
 CSCI 470     Assignment 1 - main function     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 1/24/2021

 Purpose: The purpose of this main function located in this DriverTest class is
          to basically bring togeather both the Employee class and the Date class.
          This is being done by calling all the set and get accessor methods from both
          the Employee class and the Date class. Plus, we are also calling the
          displayDate method, which is located in the Date class. Some of these
          methods are being called in this main function with data passed into them.
 *******************************************************************************/
class DriverTest
{
  // This is the start of the main function where we will be using all the contents of both the Employee and Date classes.
  public static void main(String[] args)
  {
    // Creating the first Employee class object called emp1.
    Employee emp1 = new Employee("Not Given", "Not Given", 0.00);

    // Calling the three set accessor methods for the Employee class object called emp1.
    emp1.setFname("Sam");
    emp1.setLname("Fisher");
    emp1.setMsalary(5000.00);

    // Here we are calculating employee one's (first employee object's) monthly salary, yearly salary, and yearly salary after a 10% raise.
    double employeeOneMSalary = emp1.getMsalary();
    double employeeOneYSalary = employeeOneMSalary * 12;
    double raisedEmpOneYSalary = (employeeOneYSalary * 0.10) + employeeOneYSalary;

    // Calling the three get accessor methods for the Employee class object called emp1.
    System.out.println("Here is employee one's first name: " + emp1.getFname());
    System.out.println("Here is employee one's last name: " + emp1.getLname());
    System.out.println("Here is employee one's monthly salary: $" + emp1.getMsalary());

    // Here we are calculating employee two's (second employee object's) monthly salary, yearly salary, and yearly salary after a 10% raise.
    double employeeTwoMSalary = 10000.00;
    double employeeTwoYSalary = employeeTwoMSalary * 12;
    double raisedEmpTwoYSalary = (employeeTwoYSalary * 0.10) + employeeTwoYSalary;

    // Creating the first Employee class object called emp2.
    Employee emp2 = new Employee("Tom", "Brady", employeeTwoMSalary);

    // Calling the three get accessor methods for the Employee class object called emp2.
    System.out.println("\nHere is employee two's first name: " + emp2.getFname());
    System.out.println("Here is employee two's last name: " + emp2.getLname());
    System.out.println("Here is employee two's monthly salary: $" + emp2.getMsalary());

    // Here we are displaying each employee's (each object's) yearly salary.
    System.out.println("\nHere is employee one's yearly salary: $" + employeeOneYSalary);
    System.out.println("Here is employee two's yearly salary: $" + employeeTwoYSalary);

    // Here we are displaying each employee's (each object's) yearly salary after the 10% raise.
    System.out.println("\nHere is employee one's yearly salary after a 10% raise: $" + raisedEmpOneYSalary);
    System.out.println("Here is employee two's yearly salary after a 10% raise: $" + raisedEmpTwoYSalary);

    // Creating the first Date class object called date1.
    Date date1 = new Date(0,0,0);

    // Calling the three set accessor methods for the Date class object called date1.
    date1.setMonth(10);
    date1.setDay(11);
    date1.setYear(2000);

    // Calling the three get accessor methods for the Date class object called date1.
    System.out.println("\nThis is the month that I was born in: " + date1.getMonth());
    System.out.println("This is the day that I was born in: " + date1.getDay());
    System.out.println("This is the year that I was born in: " + date1.getYear());

    // Creating the second Date class object called date2.
    Date date2 = new Date(1,22,2021);

    // Printing out the current date in a formatted fashion by calling the displayDate() method.
    System.out.println("\nThis is the current date: ");
    date2.displayDate();
  }
}

