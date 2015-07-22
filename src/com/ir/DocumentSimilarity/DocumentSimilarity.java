package com.ir.DocumentSimilarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ir.entity.Magnitude;

public class DocumentSimilarity {
	
	public static void DocumentSimilarityFunc(Map<String, Magnitude> firstDocMap,Map<String, Magnitude> secDocMap){
		 List<Long> firstDoc = new ArrayList<Long>();
		 List<Long> secDoc = new ArrayList<Long>();
		 int i=0;
		 Long numerator = (long) 0; 
		 Double entiredenominator = (double) 0;
		 Double firstPartDenom = (double) 0;
		 Double secPartDenom = (double) 0;
		 Double result = (double) 0;
		 
		 for (Map.Entry<String, Magnitude> entry : firstDocMap.entrySet()) {
//			    String key = entry.getKey();
			    Magnitude value = entry.getValue();
			    firstDoc.add(i, value.getTf());	
			    i++;
			}
		 i=0;
		 for (Map.Entry<String, Magnitude> entry : secDocMap.entrySet()) {
//			    String key = entry.getKey();
			    Magnitude value = entry.getValue();
			    secDoc.add(i, value.getTf());			   
			}
		 
		 if(firstDoc.size() != secDoc.size())
		 {
			 if( secDoc.size() > firstDoc.size() ){
				int extra = secDoc.size() - firstDoc.size();
				for(i=1;i<=extra;i++)
				firstDoc.add((long) 0);
			 }
			 else{
				 int extra =  firstDoc.size() - secDoc.size();
				 for(i=1;i<=extra;i++)
					 secDoc.add((long) 0);
			 }
		 }
		 if(firstDoc.size() == secDoc.size()){
		 for(int j=0;j<firstDoc.size();j++){
			 numerator += firstDoc.get(j) * secDoc.get(j);
		 }
		 for(int z=0;z<firstDoc.size();z++){
			 firstPartDenom += Math.pow(firstDoc.get(z), 2);
			 secPartDenom += Math.pow(secDoc.get(z), 2);
		 }
		 }
		 
		 entiredenominator = Math.sqrt(firstPartDenom)+ Math.sqrt(secPartDenom);
		 result = numerator / entiredenominator;
		 
		 
	}

}
