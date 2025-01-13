/********************************************************************************
 CSCI 470     Assignment 6 - BallAnimation class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 4/23/2021

 Purpose: The purpose of this BallAnimation class is to create the BallAnimation 
          constructor, and the actionPerformed method that is required by this  
          class. Also, in this BallAnimation class, we are creating three variables 
          that represent the start/stop buttons, and the AnimationPanel. The 
          components of this actionPerformed class will mostly be used/called in 
          the GraphicsFrame class. 
 *******************************************************************************/
package package6;   // Here we are stating that this BallAnimation.java file is located in package6.

// Importing the java abstract window toolkit library into this BallAnimation class.
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Importing the java swing library into this BallAnimation class.
import javax.swing.JButton;
import javax.swing.JPanel;

public class BallAnimation extends JPanel implements ActionListener
{
	// Here we are creating the AnimationPanel, and the start/stop animation variables.
	JButton startBtn;
	JButton stopBtn;
	AnimationPanel ballAnimationPanel;
	
	// Creating the BallAnimation constructor, and this constructor will be used later on in this Assignment.
	public BallAnimation()
	{
		this.setLayout(new BorderLayout());   // Here we are setting the layout of the frame as BorderLayout.

		// Here we are initializing both of the JButton variables (startBtn, and stopBtn).
		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");		

		// Creating a JPanel variable called southPanel, and setting it as a FlowLayout.
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
			
		// Here we are adding the JButton variables to the southPanel, and adding the southPanel to the south part of the BallAnimation frame.
		southPanel.add(startBtn);
		southPanel.add(stopBtn);
		this.add(southPanel, BorderLayout.SOUTH);
		
		// Hooking up listeners to the buttons startBtn, and stopBtn.
		startBtn.addActionListener(this);
		stopBtn.addActionListener(this);
		
		// Here we are initializing the ballAnimationPanel, and adding the ballAnimationPanel to the north part of the BallAnimation frame.
		ballAnimationPanel = new AnimationPanel();
		this.add(ballAnimationPanel, BorderLayout.NORTH);
	}
	
	// Creating the actionPerformed method which will use an else if statement to work with the two JButton variables.
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == startBtn)   // Checking to see if the startBtn button is clicked.
		{
			// If the startBtn button is clicked, enabling the stopBtn button, disabling the startBtn button, and calling the start method. 
			stopBtn.setEnabled(true);
			startBtn.setEnabled(false);
			ballAnimationPanel.start();
			
		}
		else if(e.getSource() == stopBtn)   // Checking to see if the stopBtn button is clicked.
		{
			// If the stopBtn button is clicked, enabling the startBtn button, disabling the stopBtn button, and calling the stop method. 
			startBtn.setEnabled(true);
			stopBtn.setEnabled(false);
			ballAnimationPanel.stop();	
		}
	}
}