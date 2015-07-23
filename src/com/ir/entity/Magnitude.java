package com.ir.entity;

public class Magnitude {
	long tf;
	long df;
	double idf;
    static long totalDocs;
    
//    public Magnitude() {
//		super();
//	}
    
	
	public long getTf() {
		return tf;
	}
	
	public void setTf(long tf) {
		this.tf = tf;
	}
	public void incrementTF() {
		this.tf = this.getTf()+1;		
	}
	public static void setTotalDocs(long totalDocs ){
	Magnitude.totalDocs = totalDocs;
	}
	public static long getTotalDocs(){
		return  Magnitude.totalDocs;
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

	public void updateIDF() {
		this.setIdf(Magnitude.totalDocs, this.getDf());
		
	}

//	public void incrementTF(String fileName) {
//		HashMap<String, Long> tempMap = this.getTf();
//		long tempTFCount = tempMap.get(fileName);
//		tempMap.put(fileName, ++tempTFCount);
//		this.setTf(fileName, tempTFCount+1);
//	}
}
