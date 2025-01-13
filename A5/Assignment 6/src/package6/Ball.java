/********************************************************************************
 CSCI 470     Assignment 6 - Ball class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 4/23/2021

 Purpose: The purpose of this Ball class is to create the Ball constructor,
          the move, and draw methods that are required by this class. Also, 
          in this Ball class, we are creating six variables that represent 
          the Color, radius, coordinates, and movement of the balls. The 
          components of this Ball class will mostly be used/called in the
          AnimationPanel class. 
 *******************************************************************************/
package package6;   // Here we are stating that this Ball.java file is located in package6.

// Importing the java abstract window toolkit library into this Ball class.
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics;

public class Ball 
{
	// Here we are creating the Color, radius, coordinates, and movement variables.
	Color color;   
	int radius;
	int x, y;
	int dx, dy;
	
	// Creating the Ball constructor, and this constructor will be used later on in this Assignment.
	Ball(Color col, int rad, int x, int y, int dx, int dy) 
	{
		// Here we are initializing all the data members that were created above. 
		this.color = col;
		this.radius = rad;
		this.x = x;
		this.y = y; 
		this.dx = dx; 
		this.dy = dy;
	}
	
	// Creating the move method that will move the balls whenever they hit the edge of the panel.
	public void move(Dimension dPanel) 
	{
		// Checking to see if the ball has hit the edge of the panel on the X-axis.
		if(x <= radius || x >= (dPanel.width - (2 * radius))) 
			dx = -dx;   // Switching the direction.
		
		// Checking to see if the ball has hit the edge of the panel on the Y-axis.
		if(y <= radius || y >= (dPanel.height - (2 * radius))) 
			dy = -dy;   // Switching the direction again.
		
		// If not, continue moving the ball in the direction and modify the coordinates to reflect the move.
		x += dx;
		y += dy; 
	}
	
	// Creating the draw method which draws the ball in its new location, while changing x and y each time.
	public void draw(Graphics g)  
	{
		g.setColor(color);   // Setting the color to whatever the balls color was.
		
		g.fillOval(x, y, 2 * radius, 2 * radius);   // Drawing the ball in the new location.
	}
}
