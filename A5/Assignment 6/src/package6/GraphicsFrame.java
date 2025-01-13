/********************************************************************************
 CSCI 470     Assignment 6 - GraphicsFrame class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 4/23/2021

 Purpose: The purpose of this GraphicsFrame class is to create the main function,
          and the GraphicsFrame constructor which will be used in the main function.
          This GraphicsFrame class creates a frame that has a main panel covering
          it, and this main panel comes from the MainPanel class which has a toolbar,
          and two subpanels. Also, there is another class called TileGridPanel that
          we will be using in this program (the programs goal is to paint a grid
          with different kinds of gifs). 
 *******************************************************************************/
package package6;   // Here we are stating that this GraphicsFrame.java file is located in package6.

import java.awt.BorderLayout;   // Importing the java abstract window toolkit library into this GraphicsFrame class.

import javax.swing.JFrame;   // Importing the java swing library into this GraphicsFrame class.

public class GraphicsFrame extends JFrame
{
	// Creating the main function of the GraphicsFrame class, and this main function basically creates the frame that will be using.
	public static void main(String[] args)
	{
		GraphicsFrame frame = new GraphicsFrame();   // Creating the frame by using the keyword new on the GraphicsFrame constructor.
		
		// Here we are making sure that the frame is visible while also setting the name and the size of the frame.
		frame.setName("Frame");
	    frame.setSize(550, 500);    
	    frame.setVisible(true); 
	}
	
	// Here we are creating the GraphicsFrame constructor, and we will use this constructor in the main function.
	public GraphicsFrame()
	{
		super();   // Here we are using the keyword super to call the constructor from the class. 
		
		MainPanel mainPanelPtr = new MainPanel();   // Creating a main panel which is a MainPanel object.
		
		// Creating a ball animation panel which is a BallAnimation object.
		BallAnimation ballAnimationPtr = new BallAnimation();
		
		this.add(mainPanelPtr, BorderLayout.WEST);   // Making sure that the main panel covers the west side of the frame.
		
		this.add(ballAnimationPtr, BorderLayout.EAST);   // Making sure that the ball animation panel covers the east side of the frame.
	}
}
