// Name: Yang Cao
// USC loginid: cao522
// CS 455 PA3
// Fall 2016

import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
   /* Representation invariant:
   -- The objects stored in pathList should be MazeCoord.
   -- The MazeCoord object should be stored from start location to end location.
   */
   private boolean[][] mazeData;//2D array to store maze information.
   private MazeCoord startLoc;//the start location.
   private MazeCoord endLoc;//the end location.
   private LinkedList<MazeCoord> pathList;//a Linkedlist to store the path.
   
   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc){
	   this.mazeData = mazeData;
	   if(startLoc.getRow() >= 0 && startLoc.getRow() <= mazeData.length 
			   && startLoc.getCol() >= 0 && startLoc.getCol() < mazeData[0].length)	
		   this.startLoc = startLoc;
	   if(endLoc.getRow() >= 0 && endLoc.getRow() <= mazeData.length 
			   && endLoc.getCol() >= 0 && endLoc.getCol() < mazeData[0].length)
		   this.endLoc = endLoc;
	   pathList = new LinkedList<MazeCoord>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return mazeData.length;   
   }

   
   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return mazeData[0].length;  
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
       if(true == mazeData[loc.getRow()][loc.getCol()])
    	   return true;   
       return false;
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;   
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return endLoc;   
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() { 
	  return pathList;   
   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  { 
	  boolean [][] visited = new boolean[numRows()][numCols()]; //a 2D array to store the visit information. 
      if(pathList.isEmpty()){ //didn't do search yet	
    	  return pathDFS(startLoc.getRow(), startLoc.getCol(), pathList, visited);
      }
      else{//already searched
    	  return true;
      }
   }
   
   /**
      help method for search() function to do the DFS search.
      @param row, the row number of the current location.
      @param col, the column number of the current location.
      @param pathList, the LinkedList used to store the locations in the path.
      @param visited, this 2D arrays used to remember whether a location has been visited before.
      @return
    */
   private boolean pathDFS(int row, int col, LinkedList<MazeCoord> pathList, boolean[][] visited){
	   if(row < 0 || row > numRows() - 1 || col < 0 || col > numCols() - 1)
		   return false;
	   MazeCoord grid = new MazeCoord(row, col);
	   if(hasWallAt(grid)){//if the current location is in the wall, return false.
		   return false;
	   }
	   if(visited[row][col]){//if the current location was visited, return false.
		   return false;
	   }
	   if(grid.equals(endLoc)){//if find the endLoc, add the endLoc and return true. 
		   pathList.add(new MazeCoord(row, col));
		   return true;
	   }
	   
	   visited[row][col] = true;//sign this location as visited.
	   boolean res = pathDFS(row, col - 1, pathList, visited) ||
	   				 pathDFS(row, col + 1, pathList, visited) ||   
	                 pathDFS(row - 1, col, pathList, visited) ||   
	                 pathDFS(row + 1, col, pathList, visited);
	   if(res == true){//find the endLoc, add the current location.
		   pathList.add(new MazeCoord(row, col));
	   }
	   visited[row][col] = false;//release this location as unvisited.
	
	   return res;
   }

}
