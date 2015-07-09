package com.ir.indexing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class charByinsideArray {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/home/kiran/Desktop/htmlFile.html");     
        Scanner scan = new Scanner(file);
       
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
                
	     while (scan.hasNextLine())
	      {
	         String line = scan.nextLine();
	         
	         String[] words = line.split("\\s+");
	         System.out.println(Arrays.toString(words));
	         for(int i=0;i<words.length;i++){
	        	 if(words[i].charAt(0) == '<'){
	        		 continue;
	        	 }
	        	 else if(words[i].charAt(0) != '<') {
	        		 //System.out.println(words[i]);
	        		 if(hm.containsKey(words[i])){
	        			 int val = hm.get(words[i]);
	        			 hm.put(words[i], val+1);
	        		 }
	        		 else if (!(hm.containsKey(words[i]))){
	        			 hm.put(words[i], 1);
	        		 }
	        	 }
	         }
	      }
	     System.out.println(hm);
	     scan.close();
	}
}
	        