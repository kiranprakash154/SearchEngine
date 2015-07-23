package com.ir.indexing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ir.DocumentSimilarity.DocumentSimilarity;
import com.ir.entity.Magnitude;

public class FileIndexer {

	private String directoryPath;
	private List<String> fileNames;
	Map<String, Map<String,Magnitude>> wordDocumentMagnitudeMap;
	Map<String, Map<String,Magnitude>> documentWordMagnitudeMap;
	TreeMap<Double,List<String>> resultMap;
	List<String> similarFiles;
	long totalDocs = 0;
	
	public FileIndexer(String directoryPath) {
		super();
		this.directoryPath = directoryPath;
		this.fileNames = new ArrayList<>();
		this.wordDocumentMagnitudeMap = new HashMap<String, Map<String,Magnitude>>();
		this.documentWordMagnitudeMap = new HashMap<String, Map<String,Magnitude>>();
		this.resultMap = new TreeMap<Double,List<String>>(Collections.reverseOrder());
		this.similarFiles = new ArrayList<String>();
	}
	public String getDirectoryPath() {
		return directoryPath;
	}
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	public List<String> getFileNames() {
		return fileNames;
	}
	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	
	public void startIndexing() throws IOException{
		/*
		 * looks through the files and gets all the 
		 * files under the directory and other directories 
		 * beneath it
		 */
		System.out.println("Indexer says it has started");
		scanForFiles();
		
		readFromFiles();
		System.out.println("done reading files");
		
		
		Map<String,Magnitude> userDocMap = DocumentSimilarity.userQuery();
		for(int i=0;i<fileNames.size();i++){
			String file2 = fileNames.get(i);
			Map<String,Magnitude> secDocMap = documentWordMagnitudeMap.get(file2);
			double result = DocumentSimilarity.DocumentSimilarityFunc(userDocMap, secDocMap);
			this.resultMap = DocumentSimilarity.addToResultsMap(resultMap,result,file2);
		}
	
		this.similarFiles = DocumentSimilarity.displayFiles(this.similarFiles,resultMap,10);
		System.out.println(this.similarFiles);
//		String file1 = fileNames.get(0);
		
//		
//		System.out.println("Now calculating Document similarity of: ");
//		System.out.println(file1);
//		System.out.println(file2);
//		
//		System.out.println("Result:"+result);
	}
	
	private void readFromFiles() throws IOException {
		// threads
		Magnitude.setTotalDocs(totalDocs);
		for(String file: fileNames){
			IRFileReader reader = new IRFileReader(file);
 			reader.populateMagnitudeData(wordDocumentMagnitudeMap,documentWordMagnitudeMap);
		}
	}
	
	private void scanForFiles(){
		System.out.println("Indexer started looking for files");
		File file = new File(directoryPath);
		if(file.isDirectory()){
			lookThroughDirectory(directoryPath);
		}
		System.out.println("Indexer done looking for files");
	}
	
	private void lookThroughDirectory(String directoryName){
		
		File file = new File(directoryName);
		File files[] = file.listFiles();
		totalDocs += files.length;
		System.out.println(files.length+" is size");
		System.out.println("Total Documents"+totalDocs);
		for(File f: files){			
			if(f.isDirectory()){
				System.out.println("found a directory"+f.getName());
				lookThroughDirectory(f.getPath());
			}
			else{
				//System.out.println(f.getAbsolutePath());
				fileNames.add(f.getAbsolutePath());
			}
				
		}
		
	}
	/*public void setDirectoryPath(String directoryPath){
		this.directoryPath = directoryPath;
	}*/
	
	
}
