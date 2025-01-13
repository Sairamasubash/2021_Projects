package muppalanenilinepackage;   // Stating that this Line class is in the package called muppalanenilinepackage.

/*****************************************************************
This program demonstrates a simple "Line" class. Here, a Line class
 is defined with its properties and interface (i.e., its methods).
A main method (in TestLine) then creates instances of this Line
 class and calls on the methods to demonstrate its behavior.
*****************************************************************/
import java.io.*;

public class Line
{
   private int x1, y1, x2, y2; //coordinates of the line

   //Constructor
   //Receives 4 integers which are the Line's start and end points.
   public Line(int xOne, int yOne, int xTwo, int yTwo) throws Exception   // Throwing the first generic exception.
   {
      // each of these validates its argument - see below.
      setXOne(xOne);
      setYOne(yOne);
      setXTwo(xTwo);
      setYTwo(yTwo);
   } // end constructor

   // Adding another Line constructor that will accept two TwoDPoint objects instead of four integers like the first Line constructor.
   public Line(TwoDPoint twoDObject1, TwoDPoint twoDObject2) throws Exception   // Throwing the second generic exception.
   {
      this(twoDObject1.x, twoDObject1.y, twoDObject2.x, twoDObject2.y);   // Using the keyword this to call the first Line constructor in the second Line constructor.
   }

   //method draw() calls another method called drawLine(),
   //which is assumed to be a graphics primitive on the
   //system. However, since this program will be
   //run in console mode, a text description of the Line
   //will be displayed. //
   public void draw()
   {
      drawLine(x1, y1, x2, y2);
   }

   //method drawLine() simulates drawing of a line for console mode.
   //It should describe all the important attributes of the line.
   //In a graphics mode program, we would delete this and use the
   //system's Graphics library drawLine(). //
   private void drawLine(int x1, int y1, int x2, int y2)
   {
      System.out.println("Draw a line from x of " + x1 + " and y of " + y1);
      System.out.println("to x of " + x2 + " and y of " + y2 + " (DRAW LINE SUCCEEDED)" + "\n");
   }

   //Method setLine() allows user to change the points of the
   //already existing Line.
   public void setLine(int xOne, int yOne, int xTwo, int yTwo) throws Exception   // Throwing the Third generic exception.
   {
      setXOne(xOne);
      setYOne(yOne);
      setXTwo(xTwo);
      setYTwo(yTwo);
   }

   // -- the individual setXXXX methods that prevent
   //  any line's coordinate from being offscreen.
   //  In the event of an invalid (offscreen) value,
   //  that value is (silently) set to 0.
   public void setXOne(int xOne) throws Exception
   {
      if (xOne < 0 || xOne > 639)
      {
         throw new Exception("Value " + xOne + " Was out of Bounds");   // Throwing a new generic exception when the xOne value is invalid.
      }
      else
      {
         x1 = xOne;
      }
   }

   public void setYOne(int yOne) throws Exception
   {
      if (yOne < 0 || yOne > 479)
      {
         throw new Exception("Value " + yOne + " Was out of Bounds");   // Throwing a new generic exception when the yOne value is invalid.
      }
      else
      {
         y1 = yOne;
      }
   }

   public void setXTwo(int xTwo) throws Exception
   {
      if (xTwo > 639 || xTwo < 0)
      {
         throw new Exception("Value " + xTwo + " Was out of Bounds");   // Throwing a new generic exception when the xTwo value is invalid.
      }
      else
      {
         x2 = xTwo;
      }
   }

   public void setYTwo(int yTwo) throws Exception
   {
      if (yTwo > 479 || yTwo < 0)
      {
         throw new Exception("Value " + yTwo + " Was out of Bounds");   // Throwing a new generic exception when the yTwoe value is invalid.
      }
      else
      {
         y2 = yTwo;
      }
   }

   //Now for some "get" Access methods to get individual values
   public int getXOne()
   {
      return x1;
   }

   public int getYOne()
   {
      return y1;
   }

   public int getXTwo()
   {
      return x2;
   }

   public int getYTwo()
   {
      return y2;
   }

   // Ceating a get accessor method to get the length of any given line.
   public double getLength()
   {
      return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));   // This is the formula to get the length of any given line.
   }

   // Ceating a get accessor method to get the angle of any given line.
   public double getAngle()
   {
      return Math.asin((y2 - y1) / getLength());   // This is the formula to get the angle of any given line.
   }

} // end class Line

