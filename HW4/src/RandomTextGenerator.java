// Name: Yang Cao
// USC loginid: cao522
// CS 455 PA4
// Fall 2016

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class mainly handle with:
 * 1. create hash map 
 * 2. generate successor
 * 3. choose a new initial prefix
 * 4. print debug information 
 */
public class RandomTextGenerator {
	
	private static final int FIXEDSEED = 1; //fixed seed for debug model
	private static final String INIPRE = "chose a new initial prefix"; //debug information
	private static final String PREFIX = "prefix";
	private static final String SUCCESSORS = "successors";
	private static final String GENERATE = "word generated";
	private static final String ENDOFFILE = "<END OF FILE>";
	
	/* Representation invariant:
	-- Empty space should not be stored in sourceText.
	-- The key of the map should be Prefix type and the value should be ArrayList<String> type.
	-- prefixLength should not be less than 1, otherwise the program will report error.
	*/
	private ArrayList<String> sourceText; //for storing source text. 
	private HashMap<Prefix, ArrayList<String>> map; //the mainly data structure for prefix and successors
	private Random generator; //random generator
	private int prefixLength; //the length of prefix
	private Prefix currPrefix; //the current prefix
	private String successor; //the current successor
	private int numWords; //the number of words to generate
	private boolean deBug; //debug model
	
	/**
	 * Constructor for RandomTextGenerator class
	 * Initialize the class variables.
	 * @param source
	 * @param length
	 * @param isDeBugModel
	 */
	public RandomTextGenerator(ArrayList<String> source, int length, boolean isDeBugModel, int numOfWords){
		sourceText = source;
		prefixLength = length;
		map = createMap();
		if(isDeBugModel){
			generator = new Random(FIXEDSEED);
		}else{
			generator = new Random();
		}
		currPrefix = chooseNewInitial();
		numWords = numOfWords;
		deBug = isDeBugModel;
		printDebug(deBug, INIPRE);
		printDebug(deBug, PREFIX);
	}
	
	/**
	 * create the hash map, using prefix as keys, successors as values.
	 * @return map
	 */
	private HashMap<Prefix, ArrayList<String>> createMap(){
		HashMap<Prefix, ArrayList<String>> map = new HashMap<Prefix, ArrayList<String>>();
		int textLength = sourceText.size();
		
		for(int i = 0; i <= textLength - prefixLength; i++){
			LinkedList<String> prefix = new LinkedList<String>();
			ArrayList<String> value = new ArrayList<String>();
			for(int j = 0; j < prefixLength; j++){
				prefix.add(sourceText.get(i+j));
			}
			String suc;
			if((i + prefixLength) <= (textLength - 1))
				suc = sourceText.get(i + prefixLength);
			else
				suc = ENDOFFILE;
			Prefix newkey = new Prefix(prefix);
			if(map.containsKey(newkey)){
				map.get(newkey).add(suc);
			}
			else{
				value.add(suc);
				map.put(newkey, value);
			}
		}
		return map;
	}
	
	/**
	 * generate the successor.
	 * @return successor
	 */
	private String generateSuc(){
		ArrayList<String> words = map.get(currPrefix);
		int index = generator.nextInt(words.size());
		return words.get(index);
	}
	
	/**
	 * choose a new initial prefix.
	 * @param generator
	 * @param map
	 * @return new Prefix(newPrefix)
	 */
	private Prefix chooseNewInitial(){
		int index = generator.nextInt(sourceText.size() - prefixLength);
		LinkedList<String> newPrefix = new LinkedList<String>();
		for(int i = index; i < prefixLength + index; i++){
			newPrefix.add(sourceText.get(i));
		}
		return new Prefix(newPrefix);
	}
	
	/**
	 * generate the target text and store the text in an ArrayList.
	 * @param numWords
	 * @return targetText
	 */
	public ArrayList<String> generateText(){
		ArrayList<String> targetText = new ArrayList<String>();
		while(numWords > 0){
			successor = generateSuc(); //generate a new successor
			printDebug(deBug, SUCCESSORS);
			if(!successor.equals(ENDOFFILE)){
				printDebug(deBug, GENERATE);
			}	
			while(successor.equals(ENDOFFILE)){
				currPrefix = chooseNewInitial(); //choose a new prefix
				printDebug(deBug, INIPRE);
				printDebug(deBug, PREFIX);
				successor = generateSuc();
				printDebug(deBug, SUCCESSORS);
				if(!successor.equals(ENDOFFILE)){
					printDebug(deBug, GENERATE);
				}
			}
			targetText.add(successor);
			currPrefix = currPrefix.pshiftIn(successor); //update the prefix
			printDebug(deBug, PREFIX);
			numWords --;
		}
		return targetText;
	}
	
	/**
	 * print debug information.
	 * @param deBug
	 * @param str
	 */
	private void printDebug(boolean deBug, String str){
		if(deBug == false)
			return ;
		else{
			if(str.equals(INIPRE)) //debug : choose a new initial prefix
				System.out.println("DEBUG: " + INIPRE + ": " + currPrefix);
			if(str.equals(PREFIX)) //debug : update the current prefix
				System.out.println("DEBUG: " + PREFIX + ": " + currPrefix);
			if(str.equals(SUCCESSORS)){ //debug : all successors of the current prefix
				String successors = "";
				int size = map.get(currPrefix).size();
				for(int i = 0; i < size - 1; i++){
					successors += map.get(currPrefix).get(i) + " ";
				}
				successors += map.get(currPrefix).get(size - 1);
				System.out.println("DEBUG: " + SUCCESSORS + ": " + successors);
			}
			if(str.equals(GENERATE)) //debug : the current successor randomly chosen.
				System.out.println("DEBUG: " + GENERATE + ": " + successor);
		}
	}
}
