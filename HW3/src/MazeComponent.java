// Name: Yang Cao
// USC loginid: cao522
// CS 455 PA3
// Fall 2016

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   private static final Color BLACK = Color.BLACK; //magic number for Color
   private static final Color WHITE = Color.WHITE;
   private static final Color YELLOW = Color.YELLOW;
   private static final Color GREEN = Color.GREEN;
   private static final Color BLUE = Color.BLUE;
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze){   
	   this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g){
	   Graphics2D g2 = (Graphics2D)(g) ;
	   
	   for(int i = 0; i < maze.numRows(); i++){//draw maze, the black space is wall and the white space is empty 
		   for(int j = 0; j < maze.numCols(); j++){
			   if(true == maze.hasWallAt(new MazeCoord(i, j))){//draw wall
				   g2.setColor(BLACK);   
				   g2.fillRect(START_X + j*BOX_WIDTH, START_Y + i*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
			   }else{
				   g2.setColor(WHITE);
			   }
			   g2.fillRect(START_X + j*BOX_WIDTH, START_Y + i*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		   }
	   }
	   
	   g2.setColor(BLACK);
	   g2.drawRect(START_X, START_Y, BOX_WIDTH*maze.numCols(), BOX_HEIGHT*maze.numRows());//draw the frame of the maze
	   
	   g2.setColor(YELLOW);//draw entrance 
	   g2.fillRect(START_X + maze.getEntryLoc().getCol()*BOX_WIDTH + INSET, START_Y + maze.getEntryLoc().getRow()*BOX_HEIGHT + INSET, 
			      BOX_WIDTH - 2*INSET, BOX_HEIGHT - 2*INSET);
	   g2.setColor(GREEN);//draw exit
	   g2.fillRect(START_X + maze.getExitLoc().getCol()*BOX_WIDTH + INSET, START_Y + maze.getExitLoc().getRow()*BOX_HEIGHT + INSET, 
			      BOX_WIDTH - 2*INSET, BOX_HEIGHT - 2*INSET);
	   
	   if(0 != maze.getPath().size()){//draw path
		   ListIterator<MazeCoord> iter = maze.getPath().listIterator();
		   g2.setColor(BLUE);
		   MazeCoord end = iter.next();
		   MazeCoord start;
		   while(iter.hasNext()){
			   start = end;
			   end = iter.next();
			   g2.drawLine(START_X + start.getCol()*BOX_WIDTH + BOX_WIDTH/2, START_Y + start.getRow()*BOX_HEIGHT + BOX_HEIGHT/2, 
					      START_X + end.getCol()*BOX_WIDTH + BOX_WIDTH/2,START_Y + end.getRow()*BOX_HEIGHT + BOX_HEIGHT/2);
		   } 
	   }
   }
   
}



