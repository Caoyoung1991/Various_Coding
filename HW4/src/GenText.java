// Name: Yang Cao
// USC loginid: cao522
// CS 455 PA4
// Fall 2016

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class mainly handle with error checking 
 * and file reading and generating. This class 
 * contains main method.
 */
public class GenText {
	
	private static final int NUMPERLINE = 80; //the maximum characters of a line.
	private static final int ARGS_WITH_DEBUG = 5; //the exact args numbers with debug turn on.
	private static final int ARGS_WITHOUT_DEBUG = 4; //the exact args numbers with debug turn off.
	private static final String DEBUG_SIGN = "-d";
	private static final String COMMAND_USAGE = "Command Usage Summary: java GenText [-d] prefixLength numWords sourceFile outFile";
	
	private static final String FILE_NOT_EXIST = "ERROR: input file does not exist"; //for error checking
	private static final String CANNOT_WRITE = "ERROR: can't write to output file";
	private static final String NOT_INTEGER = "ERROR: prefixLength or numWords arguments are not integers";
	private static final String MISS_ERROR = "ERROR: missing command-line arguments";
	private static final String LENGTH_ERROR = "ERROR: prefixLength < 1";
	private static final String NUMWORDS_ERROR = "ERROR: numWords < 0";
	private static final String LENGTH_MISMATCH = "ERROR: prefixLength >= number of words in sourceFile";
	
	/**
	 * read the source text into an ArrayList.
	 * @param inputFileName
	 * @return sourceText
	 */
	private static ArrayList<String> readFromFile(String inputFileName) {
		ArrayList<String> sourceText = new ArrayList<String>();
		try {
			File inFile = new File(inputFileName);
		    Scanner in;
			in = new Scanner(inFile);
			
			while(in.hasNext()){
				String str = in.next();
				sourceText.add(str);
			}	
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_EXIST + ": " + inputFileName);
			System.exit(1);
		}
		return sourceText;
	}
	
	/**
	 * write the source text into a file.
	 * @param targetText
	 * @param outputFileName
	 */
	private static void generateFile(ArrayList<String> targetText, String outputFileName){
		PrintWriter out;
		try {
			out = new PrintWriter(outputFileName);
			int charCount = 0;
			for(int i = 0; i < targetText.size(); i++){	
				String textGet = targetText.get(i);
				charCount += textGet.length();
				if(charCount <= NUMPERLINE){
					out.write(textGet);
				}
				if(i < targetText.size() - 1 && charCount + targetText.get(i+1).length() + 1 > NUMPERLINE){
					out.println(); 
					charCount = 0;
				}else if( i != targetText.size() - 1){
					out.write(" ");
					charCount ++;
				}
			}
			out.close();
		} catch (IOException e) {
			System.out.println(CANNOT_WRITE + ": " + outputFileName);
			System.exit(1);
		}
	}
	
	/**
	 * check the number of args.
	 * @param args
	 */
	private static void argsNumChecking(String args[]){
		if (args.length < 1) { // no parameters error.
			System.out.println(MISS_ERROR);
			System.out.println(COMMAND_USAGE);
	        System.exit(1);
        }
		if (args[0].equals(DEBUG_SIGN)){
			if(args.length < ARGS_WITH_DEBUG){
				System.out.println(MISS_ERROR);
				System.out.println(COMMAND_USAGE);
		        System.exit(1);
			}
		}else{
			if(args.length < ARGS_WITHOUT_DEBUG){
				System.out.println(MISS_ERROR);
				System.out.println(COMMAND_USAGE);
		        System.exit(1);
			}
		}
	}
	
	/**
	 * check the args' accuracy: prefixLength should greater than 1, numWords should greater than 0.
	 */
	private static void argsChecking(int prefixLength, int numWords){
		if(prefixLength < 1){ //prefixLength < 1
			System.out.println(LENGTH_ERROR);
			throw new IllegalArgumentException();
		}
		if(numWords < 0){ // numWords < 0
			System.out.println(NUMWORDS_ERROR);
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * check weather prefixLength >= number of words in sourcefile
	 */
	private static void lenChecking(int prefixLength, ArrayList<String> sourceText){
		if(prefixLength >= sourceText.size()){
			System.out.println(LENGTH_MISMATCH);
			System.exit(1);
		}
	}
	
	/**
	 * java GenText [-d] prefixLength numWords sourceFile outFile
	 * @param args
	 */
	public static void main(String [] args){
		boolean isDeBugModel = false;
		try {
			argsNumChecking(args); //check args missing error
			if(args[0].equals(DEBUG_SIGN)){
				isDeBugModel = true;
				for(int i = 1; i < args.length; i++){
					args[i-1] = args[i];
				}
			}
			int prefixLength = Integer.parseInt(args[0]); // get the paras from command line.
			int numWords = Integer.parseInt(args[1]);
			argsChecking(prefixLength, numWords); // check prefixLength < 1 or numWords < 0
			String sourceFile = args[2];
			String outFile = args[3];
			
			ArrayList<String> sourceText = readFromFile(sourceFile); // read from source file.
			lenChecking(prefixLength, sourceText);
			RandomTextGenerator rtg = new RandomTextGenerator(sourceText, prefixLength, isDeBugModel, numWords);
			ArrayList<String> targetText = rtg.generateText(); // generate target text.
			generateFile(targetText, outFile);	// output the target text into a file.
		}catch (NumberFormatException e){
			System.out.println(NOT_INTEGER);
			System.out.println(COMMAND_USAGE);
		}catch (IllegalArgumentException e){
			System.out.println(COMMAND_USAGE);
		} 
	}
}
