/********************************************************************************
 CSCI 470     Assignment 6 - AnimationPanel class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 4/23/2021

 Purpose: The purpose of this AnimationPanel class is to create the AnimationPanel 
          constructor, the start, stop, run, and paintComponent methods that are 
          required by this class. Also, in this AnimationPanel class, we are creating 
          three variables that represent the ArrayList of Ball objects, the Dimension 
          object, and the Thread object. The components of this AnimationPanel class 
          will mostly be used/called in the BallAnimation class. 
 *******************************************************************************/
package package6;   // Here we are stating that this AnimationPanel.java file is located in package6.

// Importing the java abstract window toolkit library into this AnimationPanel class.
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// Importing the java utilities library into this AnimationPanel class.
import java.util.ArrayList;

// Importing the java swing library into this AnimationPanel class.
import javax.swing.JPanel;

public class AnimationPanel extends JPanel implements Runnable
{
	ArrayList<Ball> ballObjectPtr = new ArrayList<Ball>();   // An ArrayList of Ball objects.
	
	Dimension dimensionObjectPtr = null;   // A reference to a Dimension object that is initially set to null.
	
	Thread threadObjPtr = null;   // A reference to a Thread object that is initially set to null.
	
	// Creating the AnimationPanel constructor, and this constructor will be used later on in this Assignment.
	public AnimationPanel()
	{
		Dimension dim = new Dimension(350, 350);   // Creating a new Dimension variable and setting the size as 350 X 350.
		
		this.setPreferredSize(dim);   // Setting the size of this frame as the dim Dimension variable.
		
		this.setBackground(Color.WHITE);   // Setting the background color of this frame as white.
	}
	
	// Creating the start method to receive the pointer to a thread in the threadObjPtr variable.
	public void start()
	{
		// Checking to see if the threadObjPtr variable is equal to null.
		if(threadObjPtr == null)
		{
			threadObjPtr = new Thread(this);   // Creating a new Thread object.
			
			threadObjPtr.start();   // Calling the start method.
		}
	}
	
	// Creating the stop method which will cause the loop in the run method to exit.
	public void stop()
	{
		threadObjPtr = null;   // Here we are setting the threadObjPtr variable equal to null.
	}
	
	// Creating the run method which is basically like the main method of this AnimationPanel class.
	public void run()
	{
		// While the threadObjPtr variable is not equal to null.
		while(threadObjPtr != null)
		{
			try   // Here we are starting the try/catch block.
			{
				Thread.sleep(100);   // Putting the thread to sleep for 100 milliseconds.
			}
			catch(InterruptedException exc)   // Here we are catching the InterruptedException.
			{
				System.out.println("Error in the run method (basically the main method).");   // Here we are printing out an error message.
			}

			this.repaint();   // Calling the repaint method.
		}
	}
	
	// Creating the Overridden paintComponent method which will create the balls that we are going to need.  
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);   // Here we are calling the constructor of the AnimationPanel class.
	
		// If the dimensionObjectPtr variable is equal to null.
		if(dimensionObjectPtr == null)
		{
			dimensionObjectPtr = this.getSize();   // Calling the getSize method while setting it equal to the dimensionObjectPtr variable.
			
			// Here we are creating all of the balls that will be added to the ballObjectPtr ArrayList.
			Ball ball1 = new Ball(Color.RED, 3, dimensionObjectPtr.width - 50, dimensionObjectPtr.height - 50, -3, -5);
			Ball ball2 = new Ball(Color.ORANGE, 5, dimensionObjectPtr.width - 100, dimensionObjectPtr.height - 100, -2, -6);
			Ball ball3 = new Ball(Color.YELLOW, 6, dimensionObjectPtr.width - 150, dimensionObjectPtr.height - 150, -1, -7);
			Ball ball4 = new Ball(Color.GREEN, 10, dimensionObjectPtr.width - 200, dimensionObjectPtr.height - 200, -5, -4);
			Ball ball5 = new Ball(Color.MAGENTA, 15, dimensionObjectPtr.width - 250, dimensionObjectPtr.height - 250, -2, -3);
			Ball ball6 = new Ball(Color.BLUE, 20, dimensionObjectPtr.width - 300, dimensionObjectPtr.height - 300, -7, -6);
			Ball ball7 = new Ball(Color.BLACK, 25, dimensionObjectPtr.width - 325, dimensionObjectPtr.height - 325, -8, -1);
			
			// Here we are adding all of the balls to the ballObjectPtr ArrayList.
			ballObjectPtr.add(ball1);
			ballObjectPtr.add(ball2);
			ballObjectPtr.add(ball3);
			ballObjectPtr.add(ball4);
			ballObjectPtr.add(ball5);
			ballObjectPtr.add(ball6);
			ballObjectPtr.add(ball7);
		}
		
		// Using a for each loop to go through the ballObjectPtr ArrayList while calling the move and draw methods.
		for(Ball b:ballObjectPtr)
		{
			b.move(dimensionObjectPtr);   // Calling the move method.
			 
			b.draw(g);   // Calling the draw method.
		}
	}
}
