package com.ir.vectorSpacing;

import java.util.HashMap;

public class DocumentWordVector {

	HashMap<String,Integer> wordCount;

	public DocumentWordVector() {
		wordCount = new HashMap<String,Integer>();
		
	}

	public HashMap<String, Integer> getWordCount() {
		return wordCount;
	}

	public void setWordCount(HashMap<String, Integer> wordCount) {
		this.wordCount = wordCount;
	}
}
