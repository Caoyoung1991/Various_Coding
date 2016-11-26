 /**Name: Yang Cao
	USC loginid: cao522@aludra.usc.edu
	CSCI 455 PA1
	Fall 2016
	*/
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * class CoinSimViewer
 * 
 * Prompts for the number of trials, and creates the 
 * JFrame containing the CoinSimComponent.
 * 
 */
public class CoinSimViewer {
	
	private static final int WIN_WIDTH = 800;//the width of the frame
	private static final int WIN_HEIGHT = 500;//the height of the frame
	private static int numOfTrials ;
	
	
	/**
		 The main method to display the graphs and results.
		 When "Enter number of trials:" displayed in the console,
		 the client should input the trial numbers of the program.	 
	*/
	public static void main(String [] agrs){
		JFrame frame = new JFrame();
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true){//continuously test the input number
			System.out.print("Enter number of trials: ");
			Scanner in = new Scanner(System.in);
			numOfTrials = in.nextInt();
			if(numOfTrials<0)
				System.out.println("ERROR: Number entered must be greater than 0.");
			else
				break;
		}
		
		CoinSimComponent trial = new CoinSimComponent(numOfTrials); 
		frame.add(trial);
		frame.setVisible(true);
		
	}
}
