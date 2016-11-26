// Name: Yang Cao
// USC loginid: cao522
// CS 455 PA4
// Fall 2016

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * The class about Prefix, using Prefix as Hash key.
 * This class implements pshiftIn, and override 
 * hashCode, toString and equals method.
 */
public class Prefix {
	private static final int HASH_COEFF = 31; //hash code coeff.
	
	/* Representation invariant:
	-- The objects stored in pathList should be continuously Strings in source text.
	-- Empty space should not be stored as String.
	*/
	private LinkedList<String> prefixWords; // the LinkedList to store prefix and used as hash key.
	
	public Prefix(LinkedList<String> iniPrefixWords){
		prefixWords = new LinkedList<String>();
		if(iniPrefixWords == null){
			prefixWords.add("");
		}
		else{
			ListIterator<String> iter = iniPrefixWords.listIterator();
			while(iter.hasNext()){
				String str = iter.next();
				prefixWords.add(str);
			}
		}
	}
	
	/**
	 * update the prefix: delete the fist element and add successor to the end.
	 * @param successor
	 * @return
	 */
	public Prefix pshiftIn(String successor){	
		Prefix shiftPrefix = new Prefix(prefixWords);
		shiftPrefix.prefixWords.removeFirst();
		shiftPrefix.prefixWords.addLast(successor);
		return shiftPrefix;
	}
	
	/**
	 * override the equals method.
	 */
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
		    return false;
		}
		if (getClass() != otherObject.getClass()) { //make sure the other object is the same type as Prefix
		    return false;                            
		}
		Prefix other = (Prefix) otherObject;  //down cast to Prefix

		if(prefixWords.equals(other.prefixWords))
			return true;
		else
			return false;
    }
	
	/**
	 * override the hashCode method for Prefix class.
	 */
	public int hashCode(){
		int hashCode = 0;
		for(String s : prefixWords){
			hashCode += HASH_COEFF * hashCode + s.hashCode();
		}
		return hashCode;
	}
	
	/**
	 * override the toString method to print the information.
	 */
	public String toString(){
		if(prefixWords == null)
			return "";
		String str = "";
		for(int i = 0; i < prefixWords.size(); i++){
			str += prefixWords.get(i) + " ";
		}
		return str;
	}
	
}
