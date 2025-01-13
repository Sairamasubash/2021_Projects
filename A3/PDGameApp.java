/********************************************************************************
 CSCI 470     Assignment 3 - main function     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 2/24/2021

 Purpose: The purpose of this main function located in this PDGameApp class is
          to basically bring togeather both the PDGame class and the GameStat class.
          This is being done by calling all the methods from both the PDGame class
          and the GameStat class. Finally, the overall end result of the function
          will be a perfectly working Prisoner's Dilemma game (that allows you to
          play five rounds in each session, with unlimited sessions available).
 *******************************************************************************/
import java.util.*; // Importing the java utilities library into this PDGameApp class.

public class PDGameApp   // Here is the start of the PDGameApp class.
{
   public static void main(String args[])   // Here is the start of the main function in the PDGameApp class.
   {
      Scanner scan1 = new Scanner(System.in);   // Here we are creating a Scanner object called scan1 to get the user input (basically the strategies, and the decisions).

      GameStat gs1 = null;   // This GameStat object will eventually hold the pointer to a gameStat object for this current game being played.

      PDGame currentGamePtr1 = null;   // This PDGame object will hold the pointer to the current game that is being played.

      HashMap<String, GameStat> hashMap1 = new HashMap<>();   // Defining a hashtable to be filled in with a date, and a pointer to the GameStat object.

      // These three  boolean variables will be forced into a loop to get the game session going.
      boolean playSession = true;
      boolean strategyLoop = true;
      boolean decisionLoop = true;

      // Here are the two integer variables that will hold the selected strategy numbers, and the selected decision numbers.
      int strategyInput = 0;
      int choiceInput = 0;

      // Here are all the String variables that we will be using in the while loop below.
      String currentDate;
      String result;
      String yesOrNoString;
      String gameScores;

      // This is the start a while loop that will run until we use the "break" keyword, or if the playSession variable is set to false.
      while(playSession = true)
      {
         currentGamePtr1 = new PDGame();   // Filling in pointer above with current game to be played (5 rounds)

         // Here we are printing out how many rounds will be played in each prisoner's dilemma game along with two output headers.
         System.out.println("***Starting A Session of Prisoner's Dilemma ***-5 rounds in a game\n");
         System.out.println("--HERE ARE STRATEGIES AVAILABLE FOR THE COMPUTER\n");

         // Creating a loop to get a String variable that holds all of the available strategies for the prisoner's dilemma game.
         for(String availableStrategies:currentGamePtr1.getStrategies())
         {
            System.out.println(availableStrategies + "\n");   // Here we are printing out all the available strategies for the prisoner's dilemma game.
         }

         // This is the start a while loop that will run until we use the "break" keyword, or if the strategyLoop variable is set to false.
         while(strategyLoop == true)
         {
            // Using a try/catch to see if the user entered a valid value for the strategyInput variable.
            try
            {
               // Here we are asking the user to enter a number between 1 and 3 (which represent the available strategies).
               System.out.println("Select a strategy from above for the Computer to use in the 5 rounds:");

               strategyInput = scan1.nextInt();   // Here were are converting the strategy character number that the user entered into an integer, and storing it in the strategyInput variable.

               // Checking to see if the strategyInput variable is less than one or greater than three.
               if(strategyInput < 1 || strategyInput > 3)
               {
                  // Here we are telling the user that their entered number is out of range.
                  System.out.println("\nThe integer that you have entered is invalid, please select the right strategy (1 to 3):\n");

                  scan1.nextLine();   // Clearing out anything that is remaining on the line, and then we are starting over.
               }
               else   // If the strategyInput variable is between one and three.
               {
                  strategyLoop = false;   // We are setting the strategyLoop variable equal to false.
               }
            }
            catch(InputMismatchException e)   // Looking for an InputMismatchException if the user did not enter an integer.
            {
               // Here we are telling the user that they have not entered a number.
               System.out.println("\nYou have not entered an integer value, please select the right strategy (1 to 3):\n");

               scan1.nextLine();   // Clearing out anything that is remaining on the line, and then we are starting over.
            }
         }

         strategyLoop = true;   // Here we are setting the strategyLoop variable equal to true.

         currentGamePtr1.setStrategy(strategyInput);    // Here we are calling in the setStrategy method from the PDGame class while passing in the strategyInput variable as a parameter.

         gs1 = currentGamePtr1.getStats();   // Here we are calling in the getStats method from the PDGame class while storing the result in the gs1 GameStat pointer.

         // Here we are filling in the hashMap1 entry with the current date/time and the gs1 GameStat pointer.
         currentDate = new Date().toString();
         hashMap1.put(currentDate, gs1);

         // Here we are creating a for loop that will run five times (basically once every round).
         for(int i = 0; i < 5; i++)
         {
            System.out.println("\nBEGIN A ROUND - Here are your 2 choices");   // Here we are printing out the Header for each round of the prisoner's dilemma game.

            // Here we are printing out all the decisions that are available for the user to select.
            System.out.println("\n1. Remain silent.");
            System.out.println("\n2. Betray and testify against.\n");

            // This is the start a while loop that will run until we use the "break" keyword, or if the decisionLoop variable is set to false.
            while(decisionLoop == true)
            {
               // Using a try/catch to see if the user entered a valid value for the choiceInput variable.
               try
               {
                  // Here we are asking the user to enter a number between 1 and 2 (which represent the available decisions).
                  System.out.println("----What is your decision this round?");

                  choiceInput = scan1.nextInt();   // Here were are converting the decision character number that the user entered into an integer, and storing it in the choiceInput variable.

                  // Checking to see if the choiceInput variable is less than one or greater than two.
                  if(choiceInput < 1 || choiceInput > 2)
                  {
                     // Here we are telling the user that their entered number is out of range.
                     System.out.println("\nThe integer that you have entered is invalid, please select the right choice (1 to 2):\n");

                     scan1.nextLine();   // Clearing out anything that is remaining on the line, and then we are starting over.
                  }
                  else   // If the choiceInput variable is between one and two.
                  {
                     decisionLoop = false;   // We are setting the decisionLoop variable equal to false.
                  }
               }
               catch(InputMismatchException e)   // Looking for an InputMismatchException if the user did not enter an integer.
               {
                  // Here we are telling the user that they have not entered a number.
                  System.out.println("\nYou have not entered an integer value, please select the right choice (1 to 2):\n");

                  scan1.nextLine();   // Clearing out anything that is remaining on the line, and then we are starting over.
               }
            }

            decisionLoop = true;   // Here we are setting the decisionLoop variable equal to true.

            // Here we are calling in the playRound method from the PDGame class while storing the result in a variable called result (then we are printing out this result variable).
            result = currentGamePtr1.playRound(choiceInput);
            System.out.println(result);
         }

         gameScores = currentGamePtr1.getScores();   // Here we are calling in the getScores method from the PDGame class while storing the result in a variable called gameScores.

         // Here we are printing out the the gameScores variable, and we are also printing out the winner of the game based of the gameScores variable.
         System.out.println("\nEND OF ROUNDS, GAME OVER" + gameScores + gs1.getWinner());

         // Here we are asking the user to enter either y (for yes) or n (for no) (which tells us if the user wants to keep playing).
         System.out.println("\n--Would you like to play another game (y/n)? ");

         yesOrNoString = scan1.next();   // Here were are storing what the user entered in a variable called yesOrNoString.

         // If the user entered Y or y, we are restarting the entire prisoner's dilemma game process.
         if(yesOrNoString.equalsIgnoreCase("y") || yesOrNoString.equalsIgnoreCase("Y"))
         {
            continue;   // This is being done by using the "continue" keyword.
         }

         // If the user entered N or n, we are ending the while loop and stoping the prisoner's dilemma game process.
         if(yesOrNoString.equalsIgnoreCase("n") || yesOrNoString.equalsIgnoreCase("N"))
         {
            break;   // This is being done by using the "break" keyword.
         }

         // Using an if statement to check if the user entered anything other that Y, y, N, or n .
         if(!(yesOrNoString.equalsIgnoreCase("y") || yesOrNoString.equalsIgnoreCase("Y") || yesOrNoString.equalsIgnoreCase("n") || yesOrNoString.equalsIgnoreCase("N")))
         {
            // If the user entered anything other that Y, y, N, or n we are assuming that the user does not want to play the prisoner's dilemma game any more.
            System.out.println("\nYou have not correctly stated if you would like to play another game or not, we are assuming that your answer is no.");

            break;   // This is being done by using the "break" keyword.
         }
      }

      System.out.println("\n\t      Summary of games and session times:\n");   // Here we are printing out the header for the summary.

      Set<String> keySet = hashMap1.keySet();   // Here we are creating the keySet variable which will be used in the loop below.

      // Using a loop to get the session times for each prisoner's dilemma game.
      for(String allSessionTimes:keySet)
      {
         System.out.println(allSessionTimes + "\n");   // Here we are printing out all the session times for each prisoner's dilemma game.

         // Here we are printing out the winner of each prisoner's dilemma game, after printing out the session times for each prisoner's dilemma game.
         System.out.println(hashMap1.get(allSessionTimes).getWinner() + " The computer used " + hashMap1.get(allSessionTimes).getComputerStrategy() + " strategy.\n");
      }
   }
}
