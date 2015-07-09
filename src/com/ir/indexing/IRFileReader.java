package com.ir.indexing;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.ir.entity.Magnitude;

public class IRFileReader {
	String filePath;

	public String getFilePath() {
		return filePath;
	}

	public IRFileReader(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void populateMagnitudeData(Map<String, Map<String, Magnitude>> wordDocumentMagnitudeMap) throws IOException{
		/*
		 * STUB for populating the word document magnitude amp
		 */
        FileReader inputStream = null;		
		inputStream = new FileReader(this.getFilePath());
		Map<String, Magnitude> hm = new HashMap<String,Magnitude>();
		int count;
		char letter = '*';
		char space = ' ';
        while ((count = inputStream.read()) != -1) {
        	letter = (char)count;        	
        	if( letter == '<' ){
        		letter = (char)(count = inputStream.read());
        		while(letter != '>'){
        			letter = (char)(count = inputStream.read());
        		}
        	}
        		
        		if(Character.isLetter(letter)){
        			StringBuilder builder = new StringBuilder();
        			builder.append(Character.toLowerCase(letter));
        			letter = Character.toLowerCase((char)(count = inputStream.read()));
        			while(letter != space){
        				builder.append(letter);
        				letter = Character.toLowerCase((char)(count = inputStream.read()));        				
        			}
        			System.out.println(builder);
        			if(wordDocumentMagnitudeMap.containsKey(builder.toString())){
        				
        				hm = wordDocumentMagnitudeMap.get(builder);
        				hm.put(builder.toString(), value+1);
        			}
        			
        			if(!(hm.containsKey(builder.toString()))){
        				hm.put(builder.toString(), 1);
        			}
        		}
        		
        		}
        	
        System.out.print(hm);
        inputStream.close();
		
		
	}
}
