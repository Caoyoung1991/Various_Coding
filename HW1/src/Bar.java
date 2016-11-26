 /**Name: Yang Cao
	USC loginid: cao522@aludra.usc.edu
	CSCI 455 PA1
	Fall 2016
	*/
// we added the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {

   private int bottom;
   private int left;
   private int width;
   private int numUnits;
   private double unitsPerPixel;
   private Color color;
   private String label;
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter unitsPerPixel). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar
      @param numUnits  height of the bar in application units
      @param unitsPerPixel  how many application units per pixel for height
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int numUnits, 
              double unitsPerPixel, Color color, String label) {
	   this.bottom = bottom;
	   this.left = left;
	   this.width = width;
	   this.numUnits = numUnits;
	   this.unitsPerPixel = unitsPerPixel;
	   this.color = color;
	   this.label = label;
   }
   

   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   int height =(int)(numUnits*0.8/unitsPerPixel);
	   
	   int top = bottom - height;
	   Rectangle bar = new Rectangle(left, top , width, height);
	   g2.setColor(color);
	   g2.fill(bar);
	   //g2.draw(bar);
	   
	   String str = label;   // suppose this is the label you want to display
	   Font font = g2.getFont();
	   FontRenderContext context = g2.getFontRenderContext();
	   Rectangle2D labelBounds = font.getStringBounds(str, context);
	   double widthOfLabel = labelBounds.getWidth();
	   double heightOfLabel = labelBounds.getHeight();
	   
	   int label_x = (int) (left + width/2 - widthOfLabel/2);
	   int label_y = (int) (bottom + heightOfLabel);
	   g2.setColor(Color.BLACK);
	   g2.drawString(str, label_x, label_y);
   }
}
