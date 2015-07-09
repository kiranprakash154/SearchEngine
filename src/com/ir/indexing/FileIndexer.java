package com.ir.indexing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ir.entity.Magnitude;

public class FileIndexer {

	private String directoryPath;
	private List<String> fileNames;
	Map<String, Map<String, Magnitude>> wordDocumentMagnitudeMap;
	
	public FileIndexer(String directoryPath) {
		super();
		this.directoryPath = directoryPath;
		this.fileNames = new ArrayList<>();
		this.wordDocumentMagnitudeMap = new HashMap<>();
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
	}
	
	private void readFromFiles() throws IOException {
		// threads
		for(String file: fileNames){
			IRFileReader reader = new IRFileReader(file);
			reader.populateMagnitudeData(wordDocumentMagnitudeMap);
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
		System.out.println(files.length+" is size");
		for(File f: files){
			
			if(f.isDirectory()){
				System.out.println("found a directory"+f.getName());
				lookThroughDirectory(f.getPath());
			}
			else{
				System.out.println(f.getAbsolutePath());
				fileNames.add(f.getAbsolutePath());
			}
				
		}
		
	}
	/*public void setDirectoryPath(String directoryPath){
		this.directoryPath = directoryPath;
	}*/
	
	
}
