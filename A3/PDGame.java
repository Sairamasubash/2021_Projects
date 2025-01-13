/********************************************************************************
 CSCI 470     Assignment 3 - PDGame class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 2/24/2021

 Purpose: The purpose of this PDGame class is to create four instance variables,
          a constructor called PDGame that adds data to most of the instance
          variables, the set and get accessor methods for most of the instance
          variables, and a method called playRound (which reguires other small
          methods). The components of this PDGame class will mostly be used/called
          in the main PDGameApp function (just like the GameStat class).
 *******************************************************************************/
import java.util.*; // Importing the java utilities library into this PDGame class.

public class PDGame
{
   // Declaring the four private instance variables (called gs2, strategiesArrayList, usersHistoryArrayList, and strategyNumber).
   private GameStat gs2 = null;
   private ArrayList<String> strategiesArrayList = new ArrayList<String>();
   private ArrayList<Integer> usersHistoryArrayList = new ArrayList<Integer>();
   private int strategyNumber;

   // Creating the PDGame constructor that adds additional data to the strategiesArrayList instance variable.
   public PDGame()
   {
      gs2 = new GameStat();   // Initializing the gs2 GameStat object (by "newing" it up).

      // Adding additional data to the strategiesArrayList instance variable.
      strategiesArrayList.add("1. Tit-For-Tat");
      strategiesArrayList.add("2. Tit-For-Two-Tats");
      strategiesArrayList.add("3. Random Choice by Computer");
   }

   // Implementing the playRound method, this is basically where most of the Prisoner's Dilemma game is being played (it is also the biggest method being created in this assignment).
   public String playRound(int decision)
   {
      usersHistoryArrayList.add(decision);   // Adding the users decision to the usersHistoryArrayList instance variable.

      // Creating the decisionOfComputer variable and setting it eqaul to zero.
      int decisionOfComputer = 0;

      // Here are four String variables that will be returned later on in this playRound method.
      String outputOne = "\nYou and your partner remain silent.\nYou both get 2 years in prison.";
      String outputTwo = "\nyou remain silent and your partner testified against you.\nYou get 5 years in prison and they get 1.";
      String outputThree = "\nYou testify against your partner and they remain silent.\nYou get 1 year in prison and they get 5.";
      String outputFour = "\nYou and your partner testify against one another.\nYou both get 3 years in prison.";

      // Creating a switch statement to check the current strategy number (strategyNumber).
      switch(strategyNumber)
      {
         case 1:   // If the strategyNumber instance variable is equal to 1, setting the decisionOfComputer variable equal to the return value of the tatStrategy method.
           decisionOfComputer = tatStrategy();
           break;
         case 2:   // If the strategyNumber instance variable is equal to 2, setting the decisionOfComputer variable equal to the return value of the twoTatsStrategy method.
           decisionOfComputer = twoTatsStrategy();
           break;
         case 3:   // If the strategyNumber instance variable is equal to 3, setting the decisionOfComputer variable equal to the return value of the randomStrategy method.
           decisionOfComputer = randomStrategy();
           break;
         default:   // If the strategyNumber instance variable is not equal to 1, 2, or 3, we are not doing anything.
           break;
      }

      if(decision == 1)   // Checking to see if the users decision is equal to 1.
      {
         switch(decisionOfComputer)   // Creating a switch statement to check the decision of the computer (decisionOfComputer).
         {
            case 1:   // If the decisionOfComputer variable is equal to 1, calling the GameStat update function, and returing the outputOne String variable.
              gs2.update(2, 2);
              return outputOne;
            case 2:   // If the decisionOfComputer variable is equal to 2, calling the GameStat update function, and returing the outputTwo String variable.
              gs2.update(5, 1);
              return outputTwo;
            default:   // If the decisionOfComputer variable is not equal to 1, or 2, we are not doing anything.
              break;
         }
      }
      else if(decision == 2)   // Checking to see if the users decision is equal to 2.
      {
         switch(decisionOfComputer)// Creating another switch statement to check the decision of the computer (decisionOfComputer).
         {
            case 1:   // If the decisionOfComputer variable is equal to 1, calling the GameStat update function, and returing the outputThree String variable.
              gs2.update(1, 5);
              return outputThree;
            default:   // If the decisionOfComputer variable is not equal to 1, we are not doing anything.
              break;
         }
      }

      // Calling the GameStat update function, and returing the outputFour String variable.
      gs2.update(3, 3);
      return outputFour;
   }

   // Implementing the "Tit-For-Tat" strategy in a seperate function.
   private int tatStrategy()
   {
      if(gs2.getNumOfRoundsPlayed() == 0)   // If the current number of rounds is equal 0, we are returning the number one.
      {
         return 1;
      }
      else   // If the current number of rounds is not equal 0, we are returning the player's last move.
      {
         return usersHistoryArrayList.get(usersHistoryArrayList.size() - 2);
      }
   }

   // Implementing the "Tit-For-Two-Tats" strategy in a seperate function.
   private int twoTatsStrategy()
   {
      if(gs2.getNumOfRoundsPlayed() <= 1)   // If the current number of rounds is less than or equal to 1, we are returning the number one.
      {
         return 1;
      }
      else
      {
         // If the current number of rounds is not less than or equal to 1, and if the player's last two movers are equal to 2, we are returning the number two.
         if(usersHistoryArrayList.get(usersHistoryArrayList.size() - 2) == 2 && usersHistoryArrayList.get(usersHistoryArrayList.size() - 3) == 2)
         {
            return 2;
         }
      }

      return 1;   // If the above two statements are not true, we are returning the number one again.
   }

   // Implementing the "Random Choice by Computer" strategy in a seperate function.
   private int randomStrategy()
   {
      return (int)(Math.random() * 2 + 1);   // Returning a random number between 1 to 2.
   }

   // Creating a get accessor method for the instance variable called strategiesArrayList.
   public ArrayList<String> getStrategies()
   {
      return strategiesArrayList;
   }

   // Creating a get accessor method to get the final scores of the Prisoner's Dilemma game.
   public String getScores()
   {
      // Creating a scores String variable that holds the current Prisoner's Dilemma game scores.
      String scores;
      scores = " --Your prison sentence is: " + gs2.getPlayerYears() + "\n\n------Your partner's/computer prison sentence is: " + gs2.getComputerYears() + "\n\n";

      return scores;
   }

   // Creating a get accessor method for the instance variable called gs2.
   public GameStat getStats()
   {
      return gs2;
   }

   // Creating a set accessor method for the instance variable called strategyNumber.
   public void setStrategy(int strategy)
   {
      this.strategyNumber = strategy;

      // Creating an arrayListStrategy variable to pass it in a parameter to the setComputerStrategy method from the GameStat class.
      String arrayListStrategy = strategiesArrayList.get(strategy - 1);
      gs2.setComputerStrategy(arrayListStrategy);
   }
}
