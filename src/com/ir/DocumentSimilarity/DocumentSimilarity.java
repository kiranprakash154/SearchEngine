package com.ir.DocumentSimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.ir.entity.Magnitude;

public class DocumentSimilarity {
	public static Map<String,Magnitude> userQuery(){
		
		// Taking user Query
				System.out.println("Query:");
				Scanner in = new Scanner(System.in);
				String input = in.nextLine();
				String[] splitQuery = input.split(" ");
				Map<String,Magnitude> queryMap = new HashMap<String,Magnitude>();
				for(int i=0;i<splitQuery.length;i++){
					if(!queryMap.containsKey(splitQuery[i])){
						Magnitude tempMagnitude = new Magnitude();
						tempMagnitude.setTf(1);
						queryMap.put(splitQuery[i], tempMagnitude);
					}
					else{
						Magnitude magnitude = new Magnitude();
						magnitude = queryMap.get(splitQuery[i]);
						magnitude.incrementTF();
						queryMap.put(splitQuery[i],magnitude);
					}
				}
				
				
				
				in.close();
				return queryMap;
		
	}
	
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
	public static TreeMap<Double, List<String>> addToResultsMap(TreeMap<Double, List<String>> resultMap, double result,String fileName){
		if(!resultMap.containsKey(result)){
			List<String> files = new ArrayList<String>(); 
			files.add(fileName);
			resultMap.put(result, files);
		}
		else{
			List<String> tempFiles = resultMap.get(result);
			tempFiles.add(fileName);
			resultMap.put(result, tempFiles);
		}
		return resultMap;
		
	}

	public static List<String> displayFiles(List<String> similarFiles, TreeMap<Double, List<String>> resultMap,int count) {
			for(Map.Entry<Double, List<String>> entry : resultMap.entrySet()) {
//				  double key = entry.getKey();
				  List<String> files = entry.getValue();
				  for( int i=0;similarFiles.size()<count;i++){					 
					  similarFiles.add(files.get(i)); 
				  }
		}
			return similarFiles;
	}
}
