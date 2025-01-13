/********************************************************************************
 CSCI 470     Assignment 6 - MainPanel class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 4/23/2021

 Purpose: The purpose of this MainPanel class is to create the MainPanel constructor
          and an actionPerformed method that is required to work with all of the 
          button clicks. Also, in this MainPanel class, we are creating two arrays,
          six JButtons, a JToolBar, a JPanel, and a pointer to the TileGridPanel class.
          The components of this MainPanel class will mostly be used/called in the main
          GraphicsFrame function (just like the TileGridPanel class). 
 *******************************************************************************/
package package6;   // Here we are stating that this MainPanel.java file is located in package6.

// Importing the java abstract window toolkit library into this MainPanel class.
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Importing the java swing library into this MainPanel class.
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainPanel extends JPanel implements ActionListener 
{
	// Here is the String array that holds the names of the five gifs that will be used.
	String[] imageStringNameA = {"Gifs/pat1.gif", "Gifs/pat2.gif", "Gifs/pat3.gif", "Gifs/pat4.gif", "Gifs/pat5.gif"};
	
	final static Image[] imageA = new Image[5];   // Here is an image array that can hold five images. 
	
	// Here we are creating a JToolBar variable, and a JPanel variable.
	JToolBar MainPanelToolBar = new JToolBar();
	JPanel resetSouthPanel = new JPanel();
	
	TileGridPanel tileGridPanelPtr = new TileGridPanel();   // Here we are creating a pointer to the TileGridPanel class.
	
	// Here are the six JButton variables that are needed for this program.
	JButton patch1btn;
	JButton patch2btn;
	JButton patch3btn;
	JButton patch4btn;
	JButton patch5btn;
	JButton resetbtn;
	
	// Creating the MainPanel constructor, and this constructor will be used in the constructor of the GraphicsFrame class.
	public MainPanel()
	{		
		// Here we are using a for loop to get the gif names from the imageStringNameA array and storing them in the imageA array.
		for(int i = 0; i < imageA.length; i++)
		{
			imageA[i] = (Image) Toolkit.getDefaultToolkit().getImage(imageStringNameA[i]);
		}
		
		this.setLayout(new BorderLayout());   // Here we are setting the layout of the frame as BorderLayout.
		
		// Here we are initializing all six of the JButton variables.
		patch1btn = new JButton(new ImageIcon(imageA[0]));
		patch2btn = new JButton(new ImageIcon(imageA[1]));
		patch3btn = new JButton(new ImageIcon(imageA[2]));
		patch4btn = new JButton(new ImageIcon(imageA[3]));
		patch5btn = new JButton(new ImageIcon(imageA[4]));
		resetbtn = new JButton("Reset");
		
		// Here we are adding the JButton variables to the MainPanelToolBar.
		MainPanelToolBar.add(patch1btn);
		MainPanelToolBar.add(patch2btn);
		MainPanelToolBar.add(patch3btn);
		MainPanelToolBar.add(patch4btn);
		MainPanelToolBar.add(patch5btn);
		resetSouthPanel.add(resetbtn);
		
		// Hooking up listeners to the buttons patch1btn, patch2btn, patch3btn, patch4btn, patch5btn, and resetbtn.
		patch1btn.addActionListener(this);				
		patch2btn.addActionListener(this);
		patch3btn.addActionListener(this);
		patch4btn.addActionListener(this);
		patch5btn.addActionListener(this);
		resetbtn.addActionListener(this);

		// Here we are adding the MainPanelToolBar to the frame with orange as the background color.
		this.add(MainPanelToolBar, BorderLayout.NORTH);
		MainPanelToolBar.setBackground(Color.ORANGE);
		
		// Here we are adding the tileGridPanelPtr to the frame with yellow as the background color.
		this.add(tileGridPanelPtr, BorderLayout.CENTER);
		tileGridPanelPtr.ResetGridTile();
		
		// Here we are adding the resetSouthPanel to the frame with pink as the background color.
		this.add(resetSouthPanel, BorderLayout.SOUTH);
		resetSouthPanel.setBackground(Color.PINK);	
	}
	
	// Creating the actionPerformed method which will use an else if statement to work with the six JButton variables.
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == patch1btn)   // Checking to see if the patch1btn button is clicked.
		{
			tileGridPanelPtr.selectedTile = 0;   // Setting the selectedTile variable from the TileGridPanel class equal to 0.
		}
		else if(e.getSource() == patch2btn)   // Checking to see if the patch2btn button is clicked.
		{
			tileGridPanelPtr.selectedTile = 1;   // Setting the selectedTile variable from the TileGridPanel class equal to 1.
		}
		else if(e.getSource() == patch3btn)   // Checking to see if the patch3btn button is clicked.
		{
			tileGridPanelPtr.selectedTile = 2;   // Setting the selectedTile variable from the TileGridPanel class equal to 2.
		}
		else if(e.getSource() == patch4btn)   // Checking to see if the patch4btn button is clicked.
		{
			tileGridPanelPtr.selectedTile = 3;   // Setting the selectedTile variable from the TileGridPanel class equal to 3.
		}
		else if(e.getSource() == patch5btn)   // Checking to see if the patch5btn button is clicked.
		{
			tileGridPanelPtr.selectedTile = 4;   // Setting the selectedTile variable from the TileGridPanel class equal to 4.
		}
		else if(e.getSource() == resetbtn)   // Checking to see if the resetbtn button is clicked.
		{
			tileGridPanelPtr.ResetGridTile();   // Here we are calling the ResetGridTile method from the TileGridPanel class.
		}
	}
}