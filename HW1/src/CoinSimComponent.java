 /**Name: Yang Cao
	USC loginid: cao522@aludra.usc.edu
	CSCI 455 PA1
	Fall 2016
	*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 * CoinSimComponent class Extends JComponent. 
 * Constructor initializes any necessary data and runs the simulation. 
 * Overrides paintComponent to draw the bar graph, using Bar objects for each bar in the graph. 
 */
public class CoinSimComponent extends JComponent {
	
	private CoinTossSimulator sim;
	private int win_x, win_y;
	private int barLeft1, barLeft2, barLeft3;
	private int barBottom;
	private int unmUnits1, unmUnits2, unmUnits3;
	private double unitsPerPixel;
	private static final int WIDTH = 50;
	private static final String TWO_HEADS_LABEL = "Two Heads: "; 
	private static final String TWO_TAILS_LABEL = "Two Tails: ";
	private static final String HEAD_TAIL_BABEL = "a Head and a Tail: ";
	private double twoheadsPer, twotailsPer, headtailPer;
	private static final Color TWO_HEADS_COLOR = Color.RED;
	private static final Color TWO_TAILS_COLOR = Color.GREEN;
	private static final Color HEAD_TAIL_COLOR = Color.BLUE;
	
	/**
	    The constructor initializes any necessary data and runs the simulation.  
	    You give the number of trials.
	
	    @param trialNums  the number of trials, this parameter passed by main method
	 */
	public CoinSimComponent(int trialNums) {
		
		sim = new CoinTossSimulator();
		sim.run(trialNums);//run the simulation
		
	}
	
	/**
    	paintComponent draw the bar graph, using Bar objects for each bar in the graph

    	@param g  the object of Graphics class
	 */
	public void paintComponent(Graphics g){
		
		win_x = getWidth();//current window size
		win_y = getHeight();
		
		barLeft1 = (win_x-150)/4;//left boundaries
		barLeft2 = (win_x-150)/2 + 50;
		barLeft3 = 3*(win_x-150)/4 + 100;
		
		barBottom = win_y-30;//bottom of the bar, leave space for label
		
		unmUnits1 = sim.getTwoHeads();//unmUnits & unitsPerPixel
		unmUnits2 = sim.getTwoTails();
		unmUnits3 = sim.getHeadTails();
		unitsPerPixel = (double)sim.getNumTrials()/(double)win_y;
		
		
		twoheadsPer = (double)sim.getTwoHeads() /(double)sim.getNumTrials();//percentage of each possibility
		twotailsPer = (double)sim.getTwoTails() /(double)sim.getNumTrials() ;
		headtailPer = (double)sim.getHeadTails() /(double)sim.getNumTrials() ;
		
		Bar pen1 = new Bar(barBottom,barLeft1,WIDTH,unmUnits1,unitsPerPixel, TWO_HEADS_COLOR, 
				TWO_HEADS_LABEL+sim.getTwoHeads()+"("+Math.round(twoheadsPer*100)+"%"+")" );
		pen1.draw((Graphics2D) g);//draw bar1
		
		Bar pen2 = new Bar(barBottom,barLeft2,WIDTH,unmUnits2,unitsPerPixel, TWO_TAILS_COLOR, 
				TWO_TAILS_LABEL+sim.getTwoTails()+"("+Math.round(twotailsPer*100)+"%"+")" );
		pen2.draw((Graphics2D) g);//draw bar2
		
		Bar pen3 = new Bar(barBottom,barLeft3,WIDTH,unmUnits3,unitsPerPixel, HEAD_TAIL_COLOR, 
				HEAD_TAIL_BABEL+sim.getHeadTails()+"("+Math.round(headtailPer*100)+"%"+")" );
		pen3.draw((Graphics2D) g);//draw bar3
	
	}
}
