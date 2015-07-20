package com.ir.entity;

import java.util.HashMap;

public class Magnitude {
	HashMap<String, Long> tf;
	long df;
	double idf;
	
	public HashMap<String , Long> getTf() {
		return tf;
	}
	
	public void setTf(String fileName, long tf) {
		HashMap<String, Long> tempMap = this.getTf();
		tempMap.put(fileName, tf);
	}
	public long getDf() {
		return df;
	}
	public void setDf(long df) {
		this.df = df;
	}
	public double getIdf() {
		return idf;
	}
	public void setIdf(double idf) {
		this.idf = idf;
	}

	public void incrementDF() {
		this.df = this.getDf()+1;
		
	}

	public void incrementTF(String fileName) {
		HashMap<String, Long> tempMap = this.getTf();
		long tempTFCount = tempMap.get(fileName);
		tempMap.put(fileName, ++tempTFCount);
		this.setTf(fileName, tempTFCount+1);
	}
}
