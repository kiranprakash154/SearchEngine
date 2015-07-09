package com.ir.indexing;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class charByChar {

	public static void main(String[] args) throws IOException {
		
        
		FileReader inputStream = null;
		
		inputStream = new FileReader("/home/kiran/Desktop/htmlFile.html");
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		int c;
        while ((c = inputStream.read()) != -1) {
        	
        	if((char)(c = inputStream.read()) == '<'){        		
        		while((char)(c = inputStream.read()) == '>'){
        			inputStream.read();
        		}
        	}
        	
        	if( Character.isLetter(c = (char)inputStream.read())){
        		StringBuilder word = new StringBuilder();
        		word.append((char)c);
        		while(Character.isSpaceChar(c = (char)inputStream.read())){
        			word.append((char)c);
        		}
        		if(hm.containsKey(word.toString())){
        			int val = hm.get(word.toString());
        			hm.put(word.toString(), val+1);
        		}
        		else if(!(hm.containsKey(word.toString()))){
        			hm.put(word.toString(), 1);
        		}
        	
        	}
        	}
            System.out.println(""+hm);
            inputStream.close();
        }

        
 }

