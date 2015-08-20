package com.ir.vectorSpacing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DocumentWordVector {

	HashMap<String, Double> wordCount;

	public DocumentWordVector() {
		wordCount = new HashMap<String,Double>();
		
	}

	public HashMap<String, Double> getWordCount() {
		return wordCount;
	}

	public void setWordCount(HashMap<String, Double> wordCount) {
		this.wordCount = wordCount;
	}
	
	public void iterateThroughObject(DocumentWordVector obj){
		HashMap<String,Double> wordCountObj = obj.getWordCount();
		
		Iterator<Map.Entry<String, Double>> iterator = wordCountObj.entrySet().iterator();
		
		while(iterator.hasNext()){
            Map.Entry<String,Double> entry = iterator.next();
            System.out.printf("Key : %s and Value: %s %n", entry.getKey(),entry.getValue());
		}
            iterator.remove(); // right way to remove entries from Map, 
                               // avoids ConcurrentModificationException
     }
	
}
