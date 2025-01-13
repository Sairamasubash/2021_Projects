/********************************************************************************
 CSCI 470     Assignment 4 - main function     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 3/21/2021

 Purpose: The purpose of this PDGameGui class is to re-implement the Prisoner's
          Dilemma game from Assignment 3 as a Java Swing GUI (Graphical User 
          Interface) application. The GameStat and PDGame classes mostly remain
          the same as they were in Assignment 3. The only parts that might be
          changed are the statements that will be printed out in the output. Also,
          the PDGameApp class name from Assignment 3 is now changed to PDGameGui
          in Assignment 4.
 *******************************************************************************/
package package4;   // Here we are stating that this PDGameGui.java file is located in package4.

// Importing the java abstract window toolkit library into this PDGameGui class.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Importing the java swing library into this PDGameGui class.
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;   // Importing the java utilities library into this PDGameGui class.

public class PDGameGui extends JFrame implements ActionListener , ListSelectionListener 
{	
	// Here are all of the private instance variables that are required by this PDGameGui class.
    private final DefaultListModel<String> listModelPtr = new DefaultListModel<String>();
    private JList<String> finishedGamesListPtr= new JList<String>(listModelPtr); 
    private JComboBox<Object> computerStrategyCB=null; 
    private final JTextArea gameResultsTA = new JTextArea(15, 35); 
    private PDGame currentPDGame = null;
    private String gameStartTimeStr = null;
    private final HashMap<String, GameStat> stats = new HashMap<>();
    private int computerStrategy = 1;  
   
    // Here are all of the swing text fields that are by this PDGameGui class.
    private final JTextField roundsTF = new JTextField(10);
    private final JTextField computerStrategyTF = new JTextField(10);
    private final JTextField playerSentenceTF = new JTextField(10);
    private final JTextField computerSentenceTF = new JTextField(10);
    private final JTextField winnerTF = new JTextField(10);
    
    // Here are all of the swing labels that are by this PDGameGui class.
    private final JLabel roundsPlayedL = new JLabel("Rounds Played");
    private final JLabel computerStrategyL1 = new JLabel("Computer Strategy");
    private final JLabel playerSentenceL = new JLabel("Player Sentence");
    private final JLabel computerSentenceL = new JLabel("Computer Sentence");
    private final JLabel winnerL = new JLabel("Winner");
    private final JLabel computerStrategyL2 = new JLabel("Computer Strategy");
    private final JLabel decisionL = new JLabel("Your decision this round?");
    
    // Here are all of the swing buttons that are by this PDGameGui class.
    private final JButton startB = new JButton("Start New Game");
    private final JButton silentB = new JButton("Remain Silent");
    private final JButton betrayB = new JButton("Testify");
   
    // Creating the main function of the PDGameGui class, and this main function only calls the createAndShowGUI method.
    public static void main(String args[])
    {    	
	    createAndShowGUI();   // Calling the createAndShowGUI method.
    }
   
    // Here we are creating the createAndShowGUI method which is called in the main function of the PDGameGui class (above).
    public static void createAndShowGUI()
    {    	
	    PDGameGui pdg1 = new PDGameGui();   // Calling the PDGameGui constructor.
 	    pdg1.addListeners();   // Calling the addListeners method.
	   
 	    // Here we are displaying the window, and we are also packing together all of the swing items (such as the panels, and the buttons).
	    pdg1.pack(); 
	    pdg1.setVisible(true);
    }
   
    // Creating the PDGameGui constructor, and this constructor is where we will build the swing interface for this program.
    public PDGameGui()
    {    	
	    super("Prisoner's Dilemma");   // Making the title of the entire window "Prisoner's Dilemma".
	    
	    currentPDGame = new PDGame();   // Here we are calling the PDGame constructor.
	   
	    setLayout(new BorderLayout());   // Setting the layout of the panels to border layout (until it is changed later).
	   
	    // Creating a panel called westPanel which will have a border layout, be on the west side of the window, and have cyan as background color.
	    JPanel westPanel = new JPanel(new BorderLayout());
	    westPanel.setBackground(Color.CYAN);
	    westPanel.setBorder(BorderFactory.createTitledBorder("List of Games"));
	    add(westPanel, BorderLayout.WEST);	   
	    
	    // Creating a panel called eastPanel which will have a border layout, be on the east side of the window, and have magenta as background color.
	    JPanel eastPanel = new JPanel(new BorderLayout());
	    eastPanel.setBackground(Color.MAGENTA);
	    add(eastPanel, BorderLayout.EAST);	   
	   	 
	    // Here we are setting up a JLIST, while putting this JLIST in a scroll pane on the north part of the westPanel.
	    finishedGamesListPtr.setFont(new Font("SansSerif", Font.BOLD, 18));
        finishedGamesListPtr.setVisibleRowCount(10);
        finishedGamesListPtr.setFixedCellWidth(350);
        finishedGamesListPtr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        westPanel.add(new JScrollPane(finishedGamesListPtr), BorderLayout.NORTH);
        
        // Here we are setting up a text area, while putting this text area in a scroll pane on the south part of the eastPanel.
        eastPanel.add(new JScrollPane(gameResultsTA), BorderLayout.SOUTH);
        
        // Creating a panel called westPanel2 which will have a grid layout, be on the south side of the westPanel, and have cyan as background color.
	    JPanel westPanel2 = new JPanel(new GridLayout(5,2));
	    westPanel2.setBackground(Color.CYAN);
	    
	    // Here we are adding the rounds played label and text field to westPanel2.
	    westPanel2.add(roundsPlayedL);
	    westPanel2.add(roundsTF);
	    
	    // Here we are adding the computer strategy label and text field to westPanel2.
	    westPanel2.add(computerStrategyL1);
	    westPanel2.add(computerStrategyTF);
	    
	    // Here we are adding the player sentence label and text field to westPanel2.
	    westPanel2.add(playerSentenceL);
	    westPanel2.add(playerSentenceTF);
	    
	    // Here we are adding the computer sentence label and text field to westPanel2.
	    westPanel2.add(computerSentenceL);
	    westPanel2.add(computerSentenceTF);
	    
	    // Here we are adding the winner label and text field to westPanel2.
	    westPanel2.add(winnerL);
	    westPanel2.add(winnerTF);
	    
	    // Adding westPanel2 to the south side of the westPanel.
	    westPanel.add(westPanel2, BorderLayout.SOUTH);
	    
	    // Creating a panel called eastPanel2 which will have a grid layout, be on the north side of the eastPanel, and have yellow as background color.
	    JPanel eastPanel2 = new JPanel(new GridLayout(2,1));
	    eastPanel2.setBackground(Color.YELLOW);	    	   
	    eastPanel.add(eastPanel2, BorderLayout.NORTH);
        
	    // Creating a panel called eastPanel3 which will have a flow layout, be on the north side of the eastPanel2, and have yellow as background color.
	    JPanel eastPanel3 = new JPanel(new FlowLayout());
	    eastPanel3.setBackground(Color.YELLOW);
	    eastPanel2.add(eastPanel3, BorderLayout.NORTH);
	    
	    // Creating a panel called eastPanel4 which will have a flow layout, be on the south side of the eastPanel2, and have yellow as background color.
	    JPanel eastPanel4 = new JPanel(new FlowLayout());
	    eastPanel4.setBackground(Color.YELLOW);
	    eastPanel2.add(eastPanel4, BorderLayout.SOUTH);
	    
	    // Adding the computer strategy label two to eastPanel3.
	    eastPanel3.add(computerStrategyL2);
	    
	    // Here we are preparing the combo box that holds the computer strategies by converting the strategies array list to an array.
        Object[] strategyArray = currentPDGame.getStrategies().toArray();
        computerStrategyCB = new JComboBox<Object>(strategyArray);   
        computerStrategyCB.setEditable(false);
        computerStrategyCB.setSelectedIndex(0); 
        eastPanel3.add(computerStrategyCB);
        
        // Adding the buttons/labels startB, decisionL, silentB, and betrayB to eastPanel3/eastPanel4.
        eastPanel3.add(startB);                
        eastPanel4.add(decisionL);
        eastPanel4.add(silentB);
        eastPanel4.add(betrayB);
    }   
    
    // Creating the addListeners method, and this method will hook up listeners to all the buttons being used.
    public void addListeners()
    {    	
        // Hooking up listeners to the buttons startB, silentB, and betrayB.
	    startB.addActionListener(this);
	    silentB.addActionListener(this);
	    betrayB.addActionListener(this);
	    
	    computerStrategyCB.addActionListener(this);   // Hooking up a listener to the computer strategy combo box.
	    
  	    finishedGamesListPtr.addListSelectionListener(this);   // Here is the finishedGamesListPtr JLIST event listener.
    }   
    
    // Creating the actionPerformed method which will use an else if statement to call many other methods from this program.
    public void actionPerformed(ActionEvent e)
    {
	    if(e.getSource() == startB)   // Checking to see if the startB button is clicked.
	    {		
		    startGame();   // Calling the startGame method.
	    }  
	    else if(e.getSource() == silentB)   // Checking to see if the silentB button is clicked.
	    {
		    cooperate();   // Calling the cooperate method.
	    }
	    else if(e.getSource() == betrayB)   // Checking to see if the betrayB button is clicked.
	    {	    	
		    betray();   // Calling the betray method.
	    }
	    else if(e.getSource() == computerStrategyCB)   // Checking to see if the computer strategy combo box is used.
	    {
	    	computerStrategy = computerStrategyCB.getSelectedIndex() + 1;   // Here we are filling up the variable that was created above.
	    }
    }
    
    // Creating the startGame method that will be called in the actionPerformed method (above).
    public void startGame()
    {
    	// Calling the PDGame constructor, and setting the computer strategy.
    	currentPDGame = new PDGame();
    	currentPDGame.setStrategy(computerStrategy);
    	
    	// Here we are getting the game start time, and then we are storing this time in a HashMap.
    	gameStartTimeStr = (new Date()).toString();
    	stats.put(gameStartTimeStr, currentPDGame.getStats());    	   
    	
    	gameResultsTA.append("***Prisonner's Dilemma***\n");   // Here we are adding text to the game results text area.
    	
    	promptPlayer();   // Calling the promptPlayer method.
    }
    
    // Creating the promptPlayer method, and this method will be called in the startGame, cooperate, and betray methods.
    public void promptPlayer()
    {
    	// Here we are checking to see if the number of rounds played is less than 5.
    	if(currentPDGame.getStats().getNumOfRoundsPlayed() < 5)
    	{
    		// Prompting the user to make a decision by adding more text to the game results text area.
    		gameResultsTA.append("\n1. Cooperate with your partner and remain silent.\n");
    		gameResultsTA.append("2. Betray and testify against your partner.\n\n");    	
    		gameResultsTA.append("What is your decision this round?\n");
    	}
    	else
    	{
    		endGame();   // Calling the endGame method.
    	}
    }
    
    // Creating the cooperate method that will be called in the actionPerformed method (above).
    public void cooperate()
    {
    	// Creating a string variable called resultOne that will hold the decision made by the player (decision to cooperate).
    	String resultOne;
    	resultOne = currentPDGame.playRound(1);
    	
    	gameResultsTA.append(resultOne + "\n");   // Showing the results of cooperation in the game results text area.
    	
    	promptPlayer();   // Calling the promptPlayer method.
    }
    
    // Creating the betray method that will be called in the actionPerformed method (above).
    public void betray()
    {
    	// Creating a string variable called resultTwo that will hold the decision made by the player (decision to betray).
    	String resultTwo;
    	resultTwo = currentPDGame.playRound(2);
    	
    	gameResultsTA.append(resultTwo + "\n");   // Showing the results of betrayal in the game results text area.
    	
    	promptPlayer();   // Calling the promptPlayer method.
    }
    
    // Creating the endGame method that will be called in the promptPlayer method (above).
    public void endGame()
    {
    	// Creating a string variable called gameScores that will hold the results of the entire game.
    	String gameScores;
    	gameScores = currentPDGame.getScores();
    	
    	gameResultsTA.append("\n" + gameScores);   // Showing the results of the entire game in the game results text area.
    	
    	listModelPtr.addElement(gameStartTimeStr);   // Here we are adding the game start time element to the list model pointer.
    }
    
    // Creating the valueChanged method which basically stores the final values in all of the text fields that we created above.
    public void valueChanged(ListSelectionEvent e)
    {
    	// Checking to see if the finishedGamesListPtr is empty.
    	if(!finishedGamesListPtr.isSelectionEmpty())
    	{
    		// Getting the time of the current game to look up its results in the HashMap.
    		String searchKey = (String)finishedGamesListPtr.getSelectedValue();
    		
    		GameStat gs1 = stats.get(searchKey);   // Here we are getting all the stats for every single game.
    		
    		// Here we are storing the number of rounds played stat in the rounds played text field.
    		roundsTF.setText(new Integer(gs1.getNumOfRoundsPlayed()).toString());
    		roundsTF.setFont(new Font("SansSerif", Font.BOLD, 11));
    		
    		// Here we are storing the computer strategy used stat in the computer strategy text field.
    		computerStrategyTF.setText(gs1.getComputerStrategy());
    		computerStrategyTF.setFont( new Font("SansSerif", Font.BOLD, 11));
    		
    		// Here we are storing the player sentence stat in the player sentence text field.
    		playerSentenceTF.setText(String.format("%d %s", gs1.getPlayerYears(), ((gs1.getPlayerYears() > 1) ? " years" : " year")));
    		playerSentenceTF.setFont( new Font("SansSerif", Font.BOLD, 11));
    		
    		// Here we are storing the computer sentence stat in the computer sentence text field.
    		computerSentenceTF.setText(String.format("%d %s", gs1.getComputerYears(), ((gs1.getComputerYears() > 1) ? " years" : " year")));
    		computerSentenceTF.setFont( new Font("SansSerif", Font.BOLD, 11));
    		
    		// Here we are storing the winner stat in the winner text field.
    		winnerTF.setText(gs1.getWinner());
    		winnerTF.setFont( new Font("SansSerif", Font.BOLD, 11));
    	}
    }
}