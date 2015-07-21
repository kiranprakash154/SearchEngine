package com.ir.entity;

import java.util.HashMap;

public class Magnitude {
	HashMap<String, Long> tf;
	long df;
	double idf;
    static long totalDocs;
	
	public HashMap<String , Long> getTf() {
		return tf;
	}
	public static void setTotalDocs(long totalDocs ){
	Magnitude.totalDocs = totalDocs;
	}
	public static long getTotalDocs(){
		return  Magnitude.totalDocs;
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
	public void setIdf(long totalDocs,double idf) {
		this.idf = Math.log(totalDocs/idf);
		
	}
	public void setIdf(double idf) {
		this.idf = Math.log(Magnitude.totalDocs/idf);
		
	}

	public void incrementDF() {
		this.df = this.getDf()+1;
		updateIDF();
		
	}

	private void updateIDF() {
		this.setIdf(Magnitude.totalDocs, this.getIdf());
		
	}

	public void incrementTF(String fileName) {
		HashMap<String, Long> tempMap = this.getTf();
		long tempTFCount = tempMap.get(fileName);
		tempMap.put(fileName, ++tempTFCount);
		this.setTf(fileName, tempTFCount+1);
	}
}
