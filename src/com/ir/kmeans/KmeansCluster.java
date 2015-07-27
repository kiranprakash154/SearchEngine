package com.ir.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ir.DocumentSimilarity.DocumentSimilarity;
import com.ir.entity.Magnitude;

public class KmeansCluster {
	
	
	public static void cluster(Map<String, Map<String, Magnitude>> documentWordMagnitudeMap, List<String> files,int k){		
//		HashSet<String> duplicateFiles = new HashSet<String>();
		List<String> clusterInOrder = new ArrayList<String>(); // holds the selected clusters in order
		List<String> newClusters = new ArrayList<String>(); // holds the selected new clusters in order 
//		Map<String,Double> clusterListValue = new HashMap<String,Double>();
		Map<String,Map<Double,String>> clusterList = new LinkedHashMap<String,Map<Double,String>>();// holds cluster's centroid and its points with similarity vector
//		Map<String,Map<Double,String>> newClusterList = new LinkedHashMap<String,Map<Double,String>>();
		System.out.println("Starting Cluster");
		Random randomGenerator = new Random();
		for (int i = 1; i <= k; i++){ // create k random numbers till 25054 
		      int randomInt = randomGenerator.nextInt(25054);
		      String fileName = files.get(randomInt);
//		      duplicateFiles.add(fileName);
//		      List<String> tempClusterList = new ArrayList<String>();		
//		      tempClusterList.add(fileName);
		      Map<Double,String> tempClusterListValue = new HashMap<Double,String>(); // centroid's list
		      tempClusterListValue.put(null,fileName);// add centroid to the list as well.
		      clusterList.put(fileName,tempClusterListValue); //  add the entire thing to the map
		      clusterInOrder.add(fileName); 
		      
		    }
		do{
		   clustering(clusterList,files,clusterInOrder,documentWordMagnitudeMap); // this function creates clusters 
		   newClusters = newClusters(clusterList);
		  }while(!clustersSame(clusterInOrder,newClusters));
    }

	private static List<String> newClusters(Map<String, Map<Double,String>> clusterList) {
		// TODO Auto-generated method stub
		List<String> newClusters = new ArrayList<String>();
		 for (Map.Entry<String, Map<Double,String>> entry : clusterList.entrySet()) {
//			    String clusterName = entry.getKey();
			    Map<Double,String> tempPoints= entry.getValue();
			    Double average = (double) 0;
			    Double averageNumerator = (double) 0;
			    int denominator = 0;
			    for (Map.Entry<Double,String> entry2 : tempPoints.entrySet()) {			    	
			    	 Double pointValue = entry2.getKey();
//			    	 String pointName = entry2.getValue();
				    if(pointValue != null){
			             averageNumerator += pointValue;
			             denominator++;
				    }
			    }
			    average = averageNumerator / denominator;
			    newClusters.add(tempPoints.get(average));
			}
		return newClusters;
	}

	private static boolean clustersSame(List<String> clusterInOrder, List<String> newClusters) {
		// TODO Auto-generated method stub
		for(int i=0;i<clusterInOrder.size();i++){
			if(clusterInOrder.get(i) != newClusters.get(i))
				return false;
		}
		return true;
	}

	private static void clustering(Map<String, Map<Double,String>> clusterList, List<String> files,
			List<String> clusterInOrder, Map<String, Map<String, Magnitude>> documentWordMagnitudeMap) {
		
		for(String fileName: files){ // take each files
			if(!clusterInOrder.contains(fileName)){ // ignore if centroid
				
				closestCluster(documentWordMagnitudeMap,clusterList,fileName); // this function calculates the closest centroid and updates the map.
				
//				// updating the cluster list
//				Map<String,Double> tempClusterFiles = clusterList.get(clusterName);
//				tempClusterFiles.add(fileName);
//				clusterList.put(clusterName, tempClusterFiles);
//				//
				
			}
		}
		
//		closestCluster();
		
	}

	private static void closestCluster(Map<String, Map<String, Magnitude>> documentWordMagnitudeMap,
			Map<String, Map<Double,String>> clusterList, String fileName) {
		double similarityVector = 0;
		String selectedCluster = "";
		 for (Map.Entry<String, Map<Double,String>> entry : clusterList.entrySet()) {
			    String clusterName = entry.getKey();
			    //Map<String, Double> docsInCluster = entry.getValue();
			    double tempSimilarityVector = DocumentSimilarity.DocumentSimilarityFunc(documentWordMagnitudeMap.get(clusterName), 
			    		documentWordMagnitudeMap.get(fileName), 1);
			    if(tempSimilarityVector < similarityVector){
			    	similarityVector = tempSimilarityVector; // holds the double value
			    	selectedCluster = clusterName; // holds the cluster name
			    }
			}
		 // update the cluster files
		 Map<Double,String> tempClusterFiles = clusterList.get(similarityVector);
		 tempClusterFiles.put(similarityVector,fileName);
		 clusterList.put(selectedCluster, tempClusterFiles);
		 //
	}
}