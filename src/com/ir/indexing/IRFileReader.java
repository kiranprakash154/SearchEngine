package com.ir.indexing;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ir.entity.Magnitude;

public class IRFileReader {
	String filePath;

	public String getFilePath() {
		return filePath;
	}
	

	public IRFileReader(String filePath) {
		super();
		this.filePath = filePath;
		
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void populateMagnitudeData(Map<String,Map<String, Magnitude>> wordDocumentMagnitudeMap,Map<String,Map<String, Magnitude>> documentWordMagnitudeMap) throws IOException{
		/*
		 * STUB for populating the word document magnitude amp
		 */


		System.out.println("Started reading file");
		FileReader reader = null;		


		/*String wordSeparators = WordSeparator.getString("TryingFile.0"); //$NON-NLS-1$
			String splits[] = wordSeparators.split(",");
			Set<Character> separatorSet = new HashSet<>();
			for(String split: splits){
				separatorSet.add(split.toCharArray()[0]);
			}*/

		reader = new FileReader(this.getFilePath()); //$NON-NLS-1$
		System.out.println("File Name:"+this.getFilePath());

		int c;
		boolean skippingCondition = false;
	//	boolean commentCondition = false;
		StringBuilder builder = new StringBuilder();
		
		// add the existing file to the map
		
		  Map<String,Magnitude> currDocWordToMagnitude = new HashMap<String,Magnitude>();
		  
		  
//		  documentWordMagnitudeMap.put(this.getFilePath(), new DocumentWordVector());
//		  DocumentWordVector wordCountMapObj = documentWordMagnitudeMap.get(this.getFilePath());
//		  HashMap<String,Double> wordCountMap = wordCountMapObj.getWordCount();
		
		
		
		
		while((c=reader.read())!=-1){
		//	System.out.print((char)c);
			if(c=='<')
			{
				if(skippingCondition && builder.toString().startsWith("!--")){
					continue;
				}
				
				else if(skippingCondition){
					builder = new StringBuilder();
				}
				
				else{
					saveWordDocument(wordDocumentMagnitudeMap, builder);
					saveDocumentWordVector(currDocWordToMagnitude,wordDocumentMagnitudeMap,builder);
				}
				skippingCondition = true;

			}
			else{
				if(skippingCondition){
					if(c=='>'){
						// to check style tags
						if(builder.toString().startsWith("style")){
							skippingCondition = true;
							builder = new StringBuilder();
						}
						else if( builder.toString().startsWith("!--") && !builder.toString().endsWith("--") ){
							continue;
						}
						else if( builder.toString().startsWith("!--") && builder.toString().endsWith("--") ){
							builder = new StringBuilder();
							skippingCondition = false;
						}
						else {
							skippingCondition = false;
							builder = new StringBuilder();
						}
						continue;
					}
					else{
						builder.append((char)c);
						continue;
					}
				}
				else if(c==' ' || c=='\n' || c=='<' || c==',' || c==';' || c=='{' || c=='}' || c==':' || c=='?' || c=='['
						|| c==']' || c=='-' || c=='_' || c=='+' || c=='=' || c=='!' || c=='(' || c==')' || c=='<' || c=='\r' || c=='.')
				{
					//							if(c=='<'){
					//								skippingCondition = true;
					//								continue;
					//							}
					if(builder.toString().equals(" ") || builder.length()==0){
						continue;
					}

					if(!skippingCondition){								
						
						saveWordDocument(wordDocumentMagnitudeMap, builder);
						saveDocumentWordVector(currDocWordToMagnitude,wordDocumentMagnitudeMap,builder);


					}
					if(c=='<'){
						skippingCondition = true;
						continue;
					}
					builder = new StringBuilder();
				}
				else{
					builder.append((char)c);
				}
			}
		}
		//System.out.println(wordDocumentMagnitudeMap);
		documentWordMagnitudeMap.put(this.getFilePath(), currDocWordToMagnitude);
	//	wordCountMapObj.setWordCount(wordDocumentMagnitudeMap);
		
		
		
		
		reader.close();
	}

	private void saveDocumentWordVector(Map<String,Magnitude> currDocWordToMagnitude,Map<String,Map<String, Magnitude>> wordDocumentMagnitudeMap, StringBuilder builder) {
		if(builder.toString().equals("") ||  builder.toString().equals(" ")){
			return;
		}
		if(!currDocWordToMagnitude.containsKey(builder.toString().toLowerCase())){ // If the word is not present then add it with count 1
			//find the reference
			
			Map<String,Magnitude> tempMap = wordDocumentMagnitudeMap.get(builder.toString().toLowerCase());
			Magnitude tempMagnitude = tempMap.get(this.getFilePath());			
			
			//
			currDocWordToMagnitude.put(builder.toString().toLowerCase(), tempMagnitude);
		}
		else{
			; // Do nothing
		}
		
	}


	private void saveWordDocument(Map<String,Map<String, Magnitude>> wordDocumentMagnitudeMap, StringBuilder builder) {
		if(builder.toString().equals("") ||  builder.toString().equals(" ")){
			return;
		}
		
		if(!wordDocumentMagnitudeMap.containsKey(builder.toString().toLowerCase())){ // IF we do not have the word
			Map<String, Magnitude> DocumentMagnitudeMap = new HashMap<String,Magnitude>();
			Magnitude magnitude = new Magnitude();
			magnitude.setTf(1); 													// setting magnitude object
			magnitude.setDf(DocumentMagnitudeMap.size());
			magnitude.updateIDF();
			DocumentMagnitudeMap.put(this.getFilePath(), magnitude);
			wordDocumentMagnitudeMap.put(builder.toString().toLowerCase(), DocumentMagnitudeMap);
		}
			
		
		else { // If we do have the word
			Map<String, Magnitude> tempDocumentMagnitudeMap = wordDocumentMagnitudeMap.get((builder).toString().toLowerCase());
			if(!tempDocumentMagnitudeMap.containsKey(this.getFilePath())){// If file name not present
				Magnitude tempMagnitude = new Magnitude();
				tempMagnitude.setTf(1);
				tempMagnitude.setDf(tempDocumentMagnitudeMap.size());
				tempMagnitude.updateIDF();
				tempDocumentMagnitudeMap.put(this.getFilePath(),tempMagnitude);
				wordDocumentMagnitudeMap.put(builder.toString().toLowerCase(), tempDocumentMagnitudeMap);
			}
			else{ // if file name present, then just increment the tf
				Magnitude tempMagnitude = tempDocumentMagnitudeMap.get(this.getFilePath());
				tempMagnitude.setTf(tempMagnitude.getTf()+1);
				tempDocumentMagnitudeMap.put(this.getFilePath(), tempMagnitude);
				wordDocumentMagnitudeMap.put(builder.toString().toLowerCase(), tempDocumentMagnitudeMap);
			}
		}
	}
			
		
	}

