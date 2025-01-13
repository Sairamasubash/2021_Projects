/********************************************************************************
 CSCI 470     Assignment 6 - TileGridPanel class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 4/23/2021

 Purpose: The purpose of this TileGridPanel class is to create three regular 
          integer variables, one static final integer variable, one two 
          dimensional array, and a lot of different methods (including the 
          mouse click methods). The components of this TileGridPanel class 
          will mostly be used/called in the main GraphicsFrame function (just 
          like the MainPanel class).
 *******************************************************************************/
package package6;   // Here we are stating that this TileGridPanel.java file is located in package6.
 
//Importing the java abstract window toolkit library into this TileGridPanel class.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Importing the java swing library into this TileGridPanel class.
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TileGridPanel extends JPanel implements MouseListener
{
	int selectedTile = -1;   // Creating an integer variable called selectedTile (this can be a value between 0 and 4).
	
	static final int squareSide = 25;   // Creating a static final integer variable called squareSide which is equal to 25.
	
	int GridRows = 5, GridCols = 5;   // Creating two integer variables called GridRows and GridCols (both are equal to 5).
	
	Image[][] gif2dArray = new Image[GridRows][GridCols];   // Creating a two dimensional image array with 5 grids and 5 columns.
	
	// Creating the TileGridPanel constructor, and this constructor will be used in the constructor of the MainPanel class.
	public TileGridPanel()
	{
		// Here we hooking up a listener to the TileGridPanel with yellow as the background color on the frame.
		this.addMouseListener(this);
		this.setBackground(Color.yellow);
	}
	
	// Here we are creating an non-empty mouseClicked method which is required since we implemented MouseListener.
	public void mouseClicked(MouseEvent event)
	{
		// Checking to see if an image was selected before clicking on the TileGridPanel.
		if(selectedTile == -1) 
		{
			System.out.println("ERROR: You have not selected an image before clicking on the TileGridPanel.");
		}
		else 
		{
			// Here is the for loop used to go through the gif2dArray rows.
			for(int row = 0; row < GridRows; row++) 
			{
				// Here is the for loop used to go through the gif2dArray columns.
				for(int col = 0; col < GridCols; col++) 
				{
					// Here we are filling in the gif2dArray with the selected gif images.
					gif2dArray[row][col] = MainPanel.imageA[selectedTile]; 
				}
			}
			
			this.repaint();   // Here we are calling the repaint method.
		}
	}
	
	// Here we are creating an empty mouseReleased method which is required since we implemented MouseListener.
	public void mouseReleased(MouseEvent event)
	{
		
	}
	
	// Here we are creating an empty mouseExited method which is required since we implemented MouseListener.
	public void mouseExited(MouseEvent event)
	{
		
	}

	// Here we are creating an empty mouseEntered method which is required since we implemented MouseListener.
	public void mouseEntered(MouseEvent event)
	{
		
	}

	// Here we are creating an empty mousePressed method which is required since we implemented MouseListener.
	public void mousePressed(MouseEvent event)
	{
		
	}

	// Creating the ResetGridTile method that will use an inner/outer for loop to empty out a gif from the TileGridPanel.
	public void ResetGridTile()
	{
		// Here is the for loop used to go through the gif2dArray rows.
		for(int row = 0; row < GridRows; row++)
		{
			// Here is the for loop used to go through the gif2dArray columns.
			for(int col = 0; col < GridCols; col++)
			{
				// Reseting the TileGridPanel by making everything in the gif2dArray equal to null.
				gif2dArray[row][col] = null;
			}
		}
		
		this.repaint();   // Here we are calling the repaint method.
	}
	
	// Creating the paintComponent method that will paint the center of the TileGridPanel with the elements in the gif2dArray.
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);   // Here we are calling the constructor along with the paintComponent method.
		
		// Here we are creating the six integer variables that are need for this method.
		int gridWidth;
		int gridHeight;   
		int panelWidth;
		int panelHeight;
		int startX;
		int startY;
		
		// Here we are finding the center portion/area of the TileGridPanel.
		gridHeight = GridRows * squareSide;
		gridWidth = GridCols * squareSide;
		panelWidth = getWidth();
		panelHeight = getHeight();
		
		// Here we are getting the X and Y starting points to draw the grid.
		startX = (panelWidth - gridWidth) / 2;
		startY = (panelHeight - gridHeight) / 2;
				
		// Here is the for loop used to go through the gif2dArray rows.
		for(int row = 0; row < GridRows; row++)
		{
			// Here is the for loop used to go through the gif2dArray columns.
			for(int col = 0; col < GridCols; col++)
			{
				// Here we are copying the gif image array over to the drawing grid (with an inner/outer for loop).
				g.drawImage(gif2dArray[row][col], startX + (squareSide * row), startY + (squareSide * col), this);	
			}	
		}
	}
}