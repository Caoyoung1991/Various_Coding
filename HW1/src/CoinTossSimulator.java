 /**Name: Yang Cao
	USC loginid: cao522@aludra.usc.edu
	CSCI 455 PA1
	Fall 2016
	*/
import java.util.Random;

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

   private static int numTrials;
   private static int numTwoheads;
   private static int numTwotails;
   private static int numHeadtails;
   private static int totalTrials;
   private Random generator;
   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   numTrials = 0;
	   numTwoheads = 0;
	   numTwotails = 0;
	   numHeadtails = 0;
	   totalTrials = 0;
	   generator = new Random();
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 0

    */
   public void run(int numTrials)  {
	   
	   totalTrials += numTrials;//to record the total trails
	   
	   for(int i=0; i<numTrials; i++){//using random() to simulate 
		   switch (generator.nextInt(4)){
			   case 0: numTwoheads++;
			   			break;
	   		   case 1: numTwotails++;
	   		   			break;
			   case 2: numHeadtails++;
			   			break;
			   case 3: numHeadtails++;
			   			break;
		   }
	   }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return totalTrials; 
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       
	   return numTwoheads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numTwotails; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadtails; 
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   numTwoheads = 0;//reset all numbers to zero
	   numTwotails = 0;
	   numHeadtails = 0;
	   totalTrials = 0;
   }

}
