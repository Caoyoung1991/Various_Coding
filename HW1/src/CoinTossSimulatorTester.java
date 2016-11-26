 /**Name: Yang Cao
	USC loginid: cao522@aludra.usc.edu
	CSCI 455 PA1
	Fall 2016
	*/

/**
 * class CoinTossSimulatorTester
 * 
 * Test the functions in CoinTossSimulatorTester.
 * Especially, run() and reset().
 * 
 */
public class CoinTossSimulatorTester {
		
		/**
    		To determine whether the sum of all possibilities equals to the trails.
		 */ 
		private static boolean sumTest(int NumTrials,int TwoHeads, int TwoTails, int HeadTails){
			if(TwoHeads+TwoTails+HeadTails == NumTrials)
				return true;
			else return false;
		}
		
		/**
			 A non-interactive program (i.e., fixed data), 
			 that tests every method multiple times, printing 
			 informative output to the console with the results 
			 of each operation.rails.
		 */
	    public static void main(String [] args){
	    	
	    	CoinTossSimulator test = new CoinTossSimulator();//test case1
	    	System.out.println("After constructor:");
	    	System.out.println("Number of trials:"+test.getNumTrials());
	    	System.out.println("Two-head tosses:"+test.getTwoHeads());
	    	System.out.println("Two-tail tosses:"+test.getTwoTails());
	    	System.out.println("One-head one-tail tosses:"+test.getHeadTails());
	    	System.out.println("Tosses add up correctly?"+sumTest(test.getNumTrials(),test.getTwoHeads(),test.getTwoTails(),test.getHeadTails()));
	    	System.out.println("");
	    	System.out.println("");
	    	
	    	
	    	test.run(1);//test case2
	    	System.out.println("After run(1):");
	    	System.out.println("Number of trials:"+test.getNumTrials());
	    	System.out.println("Two-head tosses:"+test.getTwoHeads());
	    	System.out.println("Two-tail tosses:"+test.getTwoTails());
	    	System.out.println("One-head one-tail tosses:"+test.getHeadTails());
	    	System.out.println("Tosses add up correctly?"+sumTest(test.getNumTrials(),test.getTwoHeads(),test.getTwoTails(),test.getHeadTails()));
	    	System.out.println("");
	    	System.out.println("");
	    	
	    	
	    	test.run(10);//test case3
	    	System.out.println("After run(10):");
	    	System.out.println("Number of trials:"+test.getNumTrials());
	    	System.out.println("Two-head tosses:"+test.getTwoHeads());
	    	System.out.println("Two-tail tosses:"+test.getTwoTails());
	    	System.out.println("One-head one-tail tosses:"+test.getHeadTails());
	    	System.out.println("Tosses add up correctly?"+sumTest(test.getNumTrials(),test.getTwoHeads(),test.getTwoTails(),test.getHeadTails()));
	    	System.out.println("");
	    	System.out.println("");
	    	
	    	
	    	test.run(100);//test case4
	    	System.out.println("After run(100):");
	    	System.out.println("Number of trials:"+test.getNumTrials());
	    	System.out.println("Two-head tosses:"+test.getTwoHeads());
	    	System.out.println("Two-tail tosses:"+test.getTwoTails());
	    	System.out.println("One-head one-tail tosses:"+test.getHeadTails());
	    	System.out.println("Tosses add up correctly?"+sumTest(test.getNumTrials(),test.getTwoHeads(),test.getTwoTails(),test.getHeadTails()));
	    	System.out.println("");
	    	System.out.println("");
	    	
	    	
	    	test.reset();//test case5
	    	System.out.println("After reset:");
	    	System.out.println("Number of trials:"+test.getNumTrials());
	    	System.out.println("Two-head tosses:"+test.getTwoHeads());
	    	System.out.println("Two-tail tosses:"+test.getTwoTails());
	    	System.out.println("One-head one-tail tosses:"+test.getHeadTails());
	    	System.out.println("Tosses add up correctly?"+sumTest(test.getNumTrials(),test.getTwoHeads(),test.getTwoTails(),test.getHeadTails()));
	    	System.out.println("");
	    	System.out.println("");
	    	
	    	
	    	test.run(1000);//test case6
	    	System.out.println("After run(1000):");
	    	System.out.println("Number of trials:"+test.getNumTrials());
	    	System.out.println("Two-head tosses:"+test.getTwoHeads());
	    	System.out.println("Two-tail tosses:"+test.getTwoTails());
	    	System.out.println("One-head one-tail tosses:"+test.getHeadTails());
	    	System.out.println("Tosses add up correctly?"+sumTest(test.getNumTrials(),test.getTwoHeads(),test.getTwoTails(),test.getHeadTails()));
	    	System.out.println("");
	    	System.out.println("");
	    }


}
