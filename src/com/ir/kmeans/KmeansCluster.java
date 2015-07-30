package com.ir.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ir.DocumentSimilarity.DocumentSimilarity;
import com.ir.entity.Magnitude;

public class KmeansCluster {
	
	
	public static void intializeClusters(Map<String, Map<String, Magnitude>> documentWordMagnitudeMap, List<String> files,int k){		

		List<String> centroidsInOrder = new ArrayList<String>(); // holds the selected clusters in order
		List<Map<String,Long>> centroidList = new ArrayList<Map<String,Long>>();
		List<List<String>> clusters = new ArrayList<List<String>>();
		List<Map<String,Long>> centroidWordVectorMap = new ArrayList<Map<String,Long>>();
		List<Map<String,Long>> newCentroid;
		
		// Initialize centroids
		System.out.println("Initializing Centroids");
		Random randomGenerator = new Random();
		for (int i = 1; i <= k; i++){ // create k random numbers till 25054 
		      int randomInt = randomGenerator.nextInt(25054);
		      String fileName = files.get(randomInt);
//		      Map<String,Magnitude> docWordVector = new HashMap<String,Magnitude>(); // centroid's list
		      centroidsInOrder.add(fileName);
		      System.out.println(centroidsInOrder);
		    }
		 centroidWordVectorMap = populateCentroidWordVectorMap(centroidsInOrder,centroidWordVectorMap,documentWordMagnitudeMap);
		
		do{
		   initializeClusters(centroidsInOrder,documentWordMagnitudeMap,files,clusters,centroidList); // this function creates clusters		   
		   newCentroid = calculateCentroid(centroidsInOrder,clusters,documentWordMagnitudeMap);
		  }while(!clustersSame(centroidList,newCentroid));
		   System.out.println("Cluster Initialized");
    }

	private static List<Map<String, Long>> populateCentroidWordVectorMap(List<String> centroidsInOrder,
			List<Map<String, Long>> centroidWordVectorMap, Map<String, Map<String, Magnitude>> documentWordMagnitudeMap) {
		
		for(String centroid:centroidsInOrder){
//			centroidWordVectorMap.add(centroidsInOrder.indexOf(centroid), documentWordMagnitudeMap.get(centroid));
			Map<String,Magnitude> tempWordMag = documentWordMagnitudeMap.get(centroid);
			Map<String,Long> tempWordLong = new HashMap<String,Long>();
			for (Map.Entry<String,Magnitude> entry : tempWordMag.entrySet()) {
				tempWordLong.put(entry.getKey(), entry.getValue().getTf());
			}	
			centroidWordVectorMap.add(centroidsInOrder.indexOf(centroid), null);// destroying the contents at the location (just in case)
			centroidWordVectorMap.add(centroidsInOrder.indexOf(centroid), tempWordLong);
		}
		return centroidWordVectorMap;
	}

	private static List<Map<String,Long>> calculateCentroid(List<String> centroidsInOrder, List<List<String>> clusters, Map<String, Map<String, Magnitude>> documentWordMagnitudeMap){
		List<Map<String,Long>> newCentroid = new ArrayList<Map<String,Long>>();
		for(int i=0;i<clusters.size();i++){
			List<String> tempClustersList = clusters.get(i);
			Map<String,Long> tempWordLong = new HashMap<String,Long>();
			for(String docName: tempClustersList){
				Map<String, Magnitude> tempWordMag = documentWordMagnitudeMap.get(docName);
				for (Map.Entry<String,Magnitude> entry : tempWordMag.entrySet()) {
					String word = entry.getKey();
					Magnitude mag = entry.getValue();
					if(!tempWordLong.containsKey(word)){
						tempWordLong.put(word, mag.getTf()/tempClustersList.size());
					}
					else{
						tempWordLong.put(word,tempWordLong.get(word)+(mag.getTf()/tempClustersList.size()));
					}
				}
			}
			newCentroid.add(i, tempWordLong);
			
		}
		return newCentroid;
	}
//	private static Map<String, Double> calculateAvg(Map<String, Map<String, Magnitude>> cluster) {
//		Map<String,Double> wordToTfMap = new HashMap<String,Double>();
//		int totalDocs = cluster.size();
//		for (Map.Entry<String ,Map<String,Magnitude>> entry : cluster.entrySet()) {	
//			Map<String,Magnitude> wordToMagnitude = entry.getValue();
//			for (Map.Entry<String,Magnitude> entry2 : wordToMagnitude.entrySet()) {	
//				String word = entry2.getKey();
//				Magnitude tempMagnitude = entry2.getValue();
//				if(!wordToTfMap.containsKey(word)){
//					wordToTfMap.put(word, (double) (tempMagnitude.getTf()/totalDocs));
//				}
//				else{
//				double tempValue = wordToTfMap.get(word);
//				tempValue += ((wordToMagnitude.get(word).getTf())/totalDocs);
//				wordToTfMap.put(word, tempValue);
//				}
//			}
//			
//	    }
//		
//		return wordToTfMap;
//	}
			
	private static void initializeClusters(
			List<String> clusterInOrder,
			Map<String, Map<String, Magnitude>> documentWordMagnitudeMap,
			List<String> files, 
			List<List<String>> clusters,
			List<Map<String, Long>> centroidList
			) {		
		for(String fileName: files){ // take each files					
				closestCluster(clusters,documentWordMagnitudeMap,clusterInOrder,fileName,files); // this function calculates the closest centroid and updates the map.
		}
    }

	private static void closestCluster(List<List<String>> clusters,Map<String, Map<String, Magnitude>> documentWordMagnitudeMap, 
			List<String> clusterInOrder, String fileName,List<String> files) {
		
		double similarityVector = 0;
		String selectedCluster = "";
		
		for(String clusterName: clusterInOrder){
			double tempSimilarityVector = DocumentSimilarity.DocumentSimilarityFunc(documentWordMagnitudeMap.get(clusterName),
					documentWordMagnitudeMap.get(fileName),1);
			if(tempSimilarityVector<similarityVector){
				similarityVector = tempSimilarityVector;
				selectedCluster = clusterName;
			}
		}
		int clusterNumber = files.indexOf(selectedCluster);
		
		 // update the cluster files
		 clusters.get(clusterNumber).add(fileName);
		 //
	}
	
//	private static List<String> newClusters(Map<String, Map<Double,String>> clusterList) {
//
//		List<String> newClusters = new ArrayList<String>();
//		 for (Map.Entry<String, Map<Double,String>> entry : clusterList.entrySet()) {
////			    String clusterName = entry.getKey();
//			    Map<Double,String> tempPoints= entry.getValue();
//			    Double average = (double) 0;
//			    Double averageNumerator = (double) 0;
//			    int denominator = 0;
//			    for (Map.Entry<Double,String> entry2 : tempPoints.entrySet()) {			    	
//			    	 Double pointValue = entry2.getKey();
////			    	 String pointName = entry2.getValue();
//				    if(pointValue != null){
//			             averageNumerator += pointValue;
//			             denominator++;
//				    }
//			    }
//			    average = averageNumerator / denominator;
//			    newClusters.add(tempPoints.get(average));
//			}
//		return newClusters;
//	}

	private static boolean clustersSame(List<Map<String,Long>> clusterInOrder, List<Map<String,Long>> newCentroid) {
		
		for(int i=0;i<clusterInOrder.size();i++){
			Map<String,Long> tempMapOld = clusterInOrder.get(i);
			Map<String,Long> tempMapNew = newCentroid.get(i);
			 for (Map.Entry<String,Long> entry : tempMapOld.entrySet()) {
			         if(!tempMapNew.containsKey(entry.getKey()))
				        return false;
			         else{
			        	 if(!((tempMapNew.get(entry.getKey())) == (tempMapOld.get(entry.getKey()))))
			        		 return false;
			         }
		}
		
	}
		return true;
	}
	

	
}