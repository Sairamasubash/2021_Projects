import muppalanenilinepackage.*;   // Here we are importing the package called muppalanenilinepackage.

/***************************************************************
Now we will define a driver program below called TestLine with
main() where execution will begin. It is this class, and this code,
 that will create instances of the Line and call its methods. As a
test module, this code would be improved with additional
System.out.println() statements that explain what is being attempted
 and what the results should be, for example: "About to change l1 to
 an invalid value and then redraw it. Line position
should not change: "*/
//*********************************************************
class TestLine
{
   public static void main(String args[])
   {
      //declare 4 instances of Line class
      Line l1 = null, l2 = null, l3 = null, l4 = null;

      System.out.println("DOWN BELOW ARE THE OPERATIONS FROM LINE l1:\n");   // Starting the operations for Line l1.

      try   // We are going to try the code where we create a first Line object called l1.
      {
         //create 1 Line object
         l1 = new Line (10, 10, 100, 100);
      }
      catch(Exception e)   // If there are invald values passed into the Line object called l1, we are catching that error and leaving the program with a return code of 88.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception while calling the Line constructor to create the l1 Line object (leaving program with return code of 88).\n");
         System.out.println(e.toString() + "\n");
         System.out.println("Java Result: 88");
         System.exit(88);
      }

      //draw it
      l1.draw();

      try   // We are going to try the code where we change the values of the Line object called l1.
      {
         //change start point with valid values
         l1.setLine(5, 5, l1.getXTwo(), l1.getYTwo());
      }
      catch(Exception e)   // If the changed values of l1 are invalid, we are catching that error and printing an error message to the output.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception while calling the setLine method to change the values of Line l1.\n");
         System.out.println(e.toString() + "\n");
      }

      //draw it again with new start point
      l1.draw();

      try   // We are going to try the code where we change the xOne value of the Line object called l1.
      {
         //try to change xOne (x1) to an illegal value
         l1.setXOne(3000);
      }
      catch(Exception e)   // If the changed xOne value of l1 is invalid, we are catching that error and printing an error message to the output.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception in one of the set methods for a bad value of xOne in Line l1.\n");
         System.out.println(e.toString() + "\n");
      }

      //draw the line...x1 should now be zero
      l1.draw();

      System.out.println("DOWN BELOW ARE THE OPERATIONS FROM LINE l2:\n");   // Starting the operations for Line l2.

      try   // We are going to try the code where we create a second Line object called l2.
      {
         //create a second Line instance, or object
         l2 = new Line(100, 100, 400, 400);
      }
      catch(Exception e)   // If there are invald values passed into the Line object called l2, we are catching that error and leaving the program with a return code of 88.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception while calling the Line constructor to create the l2 Line object (leaving program with return code of 88).\n");
         System.out.println(e.toString() + "\n");
         System.out.println("Java Result: 88");
         System.exit(88);
      }

      //draw 2nd line
      l2.draw();

      try   // We are going to try the code where we change the yTwo value of the Line object called l2.
      {
         //set a new valid yTwo for line 2
         l2.setYTwo(479);
      }
      catch(Exception e)   // If the changed yTwo value of l2 is invalid, we are catching that error and printing an error message to the output.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception in one of the set methods for a bad value of yTwo in Line l2.\n");
         System.out.println(e.toString() + "\n");
      }

      //draw 2nd line again
      l2.draw();

      // Getting the angles values for both Line l1 and Line l2.
      System.out.println("Here is the angle for Line l1: " + l1.getAngle());
      System.out.println("Here is the angle for Line l2: " + l2.getAngle() + "\n");

      // Getting the length values for both Line l1 and Line l2.
      System.out.println("Here is the length for Line l1: " + l1.getLength());
      System.out.println("Here is the length for Line l2: " + l2.getLength() + "\n");

      TwoDPoint twoDP1 = null, twoDP2 = null;   // Creating two TwoDPoint objects called twoDP1, and twoDP2.

      // Passing in parameters to the two TwoDPoint objects called twoDP1, and twoDP2.
      twoDP1 = new TwoDPoint(10, 100);
      twoDP2 = new TwoDPoint(5, 400);

      System.out.println("DOWN BELOW ARE THE OPERATIONS FROM LINE l3:\n");   // Starting the operations for Line l3.

      try   // We are going to try the code where we create a third Line object called l3 (using the second Line constructor which takes two TwoDPoint objects as parameters).
      {
         l3 = new Line(twoDP1, twoDP2);
      }
      catch(Exception e)   // If there are invald values passed into the Line object called l3, we are catching that error and leaving the program with a return code of 88.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception while calling the second Line constructor to create the l3 Line object (leaving program with return code of 88).\n");
         System.out.println(e.toString() + "\n");
         System.out.println("Java Result: 88");
         System.exit(88);
      }

      System.out.println("Tesing the second Line constructor using Line l3: X1 value = " + l3.getXOne() + "\n");   // Getting the xOne value of Line l3.
      System.out.println("Tesing the second Line constructor using Line l3: X2 value = " + l3.getXTwo() + "\n");   // Getting the xTwo value of Line l3.
      System.out.println("Tesing the second Line constructor using Line l3: Y1 value = " + l3.getYOne() + "\n");   // Getting the yOne value of Line l3.
      System.out.println("Tesing the second Line constructor using Line l3: Y2 value = " + l3.getYTwo() + "\n");   // Getting the yTwo value of Line l3.

      System.out.println("DOWN BELOW ARE THE OPERATIONS FROM LINE l4:\n");   // Starting the operations for Line l4.

      try   // We are going to try the code where we create a fourth Line object called l4.
      {
         l4 = new Line(100, 100, 4000, 400);
      }
      catch(Exception e)   // If there are invald values passed into the Line object called l4, we are catching that error and leaving the program with a return code of 88.
      {
         System.out.println("--EXCEPTION: My try catch caught a generic exception while calling the Line constructor to create the l4 Line object (leaving program with return code of 88).\n");
         System.out.println(e.toString() + "\n");
         System.out.println("Java Result: 88");
         System.exit(88);
      }

   } // end of main

}  // end class TestLine
