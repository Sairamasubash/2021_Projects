/********************************************************************************
 CSCI 470     Assignment 4 - GameStat class     Spring 2021

 Programmer: Sairamasubash Muppalaneni

 Z-ID: Z1844762

 TA: Navya Kamma

 Date Due: 3/21/2021

 Purpose: The purpose of this GameStat class is to create four instance variables,
          a method called update that initializes most of the instance variables,
          the set and get accessor methods for most of the instance variables,
          and a method called getWinner (which declares the winner of the game).
          The components of this GameStat class will mostly be used/called in
          the main PDGameGui function (just like the PDGame class).
 *******************************************************************************/
package package4;   // Here we are stating that this GameStat.java file is located in package4.

public class GameStat
{
   // Here we are creating the four private instance variables (called gameStrategy, playerYears, computerYears, and numOfRounds).
   private String gameStrategy;
   private int playerYears;
   private int computerYears;
   private int numOfRounds;

   // Creating a set accessor method for the instance variable called gameStrategy.
   public void setComputerStrategy(String compStrategy)
   {
      this.gameStrategy = compStrategy;
   }

   // Creating a get accessor method for the instance variable called gameStrategy.
   public String getComputerStrategy()
   {
      return gameStrategy;
   }

   // Creating a method called update to initialize three of the instance variables (playerYears, computerYears, and numOfRounds).
   public void update(int userSentence, int compSentence)
   {
      this.playerYears = this.playerYears + userSentence;
      this.computerYears = this.computerYears + compSentence;
      this.numOfRounds = this.numOfRounds + 1;
   }

   // Creating a method called getWinner to declare the winner of the Prisoner's Dilemma game.
   public String getWinner()
   {
      if(playerYears < computerYears)   // Condition needed for the player to be the winner.
      {
         return "Player";
      }
      else if(computerYears < playerYears)   // Condition needed for the computer to be the winner.
      {
         return "Computer";
      }
      else   // If there is no winner, then it is a tie..
      {
         return "Tie";
      }
   }

   // Creating a get accessor method for the instance variable called numOfRounds.
   public int getNumOfRoundsPlayed()
   {
      return numOfRounds;
   }

   // Creating a get accessor method for the instance variable called playerYears.
   public int getPlayerYears()
   {
      return playerYears;
   }

   // Creating a get accessor method for the instance variable called computerYears.
   public int getComputerYears()
   {
      return computerYears;
   }
}
