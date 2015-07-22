package com.ir.DocumentSimilarity;

import java.util.Map;

import com.ir.entity.Magnitude;

public class DocumentSimilarity {
	
	public static double DocumentSimilarityFunc(Map<String, Magnitude> firstDocMap,Map<String, Magnitude> secDocMap){
//		 List<Long> firstDoc = new ArrayList<Long>();
//		 List<Long> secDoc = new ArrayList<Long>();
		
		 Long numerator = (long) 0; 
		 Double entiredenominator = (double) 0;
		 Double firstPartDenom = (double) 0;
		 Double secPartDenom = (double) 0;
		 Double result = (double) 0;
		 
		 //Reading from the first doc
		 for (Map.Entry<String, Magnitude> entry : firstDocMap.entrySet()) {
			    String key = entry.getKey();
			    long value1 = entry.getValue().getTf();
			    if(secDocMap.containsKey(key)){
			    	long value2 = secDocMap.get(key).getTf();
			    	numerator += value1 * value2;
			    }
			    else{
			    	;
			    }
			    firstPartDenom += Math.pow(value1, 2);
			}
		 for (Map.Entry<String, Magnitude> entry : secDocMap.entrySet()) {
//			    String key = entry.getKey();
			    long value = entry.getValue().getTf();		
			    secPartDenom += Math.pow(value, 2);
			}
		 
		 entiredenominator = Math.sqrt(firstPartDenom)+ Math.sqrt(secPartDenom);
		 result = numerator / entiredenominator;
		 return result;
	}
}
