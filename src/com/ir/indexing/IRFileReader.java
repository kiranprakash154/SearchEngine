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

	public void populateMagnitudeData(Map<String, Magnitude> wordDocumentMagnitudeMap) throws IOException{
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
		while((c=reader.read())!=-1){
			//System.out.print((char)c);
			if(c=='<')
			{
				if(skippingCondition && builder.toString().startsWith("!--")){
					continue;
				}
				
				else if(skippingCondition){
					builder = new StringBuilder();
				}
				
				else{
					saveWord(wordDocumentMagnitudeMap, builder);
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
						
						saveWord(wordDocumentMagnitudeMap, builder);
						


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

		reader.close();
	}

	private void saveWord(Map<String, Magnitude> wordDocumentMagnitudeMap, StringBuilder builder) {
		if(builder.toString().equals("") ||  builder.toString().equals(" ")){
			return;
		}
		
		if(!wordDocumentMagnitudeMap.containsKey(builder.toString().toLowerCase())){ // IF we do not have the word
			Magnitude magnitude = new Magnitude();
			magnitude.setTf(this.getFilePath(), 1);
			magnitude.setDf(1);
			magnitude.setIdf(Magnitude.getTotalDocs(),1);
			wordDocumentMagnitudeMap.put(builder.toString().toLowerCase(), magnitude);			
		}
			
			
			
			
			
//			Map<String, Magnitude> map = new HashMap<String, Magnitude>();
//			Magnitude magnitude = new Magnitude();
//			magnitude.setTf(1);
//			magnitude.setDf(1);
//			map.put(this.getFilePath(), magnitude);
//
//			wordDocumentMagnitudeMap.put(builder.toString().toLowerCase(), map);
			//System.out.println("Now displaying contents of Hashmap");
			//System.out.println(""+builder.toString().toLowerCase());
			//System.out.println(""+magnitude.getTf());
		
		else { // If we do have the word
			Magnitude tempMagnitude = wordDocumentMagnitudeMap.get(builder.toString().toLowerCase());			
			HashMap<String, Long> tempMap = tempMagnitude.getTf();
			
			if(tempMap.containsKey(this.getFilePath())){ // If we have the file in the map then update the tf count
				tempMagnitude.incrementTF(this.getFilePath());
//				long tempCount = tempMap.get(this.getFilePath());
//				tempMap.put(this.getFilePath(), ++tempCount);
			}
			else{ // If we do not have the file in the map then update the map and increment the Df
				tempMagnitude.setTf(this.getFilePath(), 1);				
				tempMagnitude.incrementDF();
				
				
			}
				
				
//			}
//			Map<String, Magnitude> map = wordDocumentMagnitudeMap.get(builder.toString());
//			if(!map.containsKey(this.getFilePath())){
//				map.put(this.getFilePath(), new Magnitude());
//			}
//			Magnitude tempMagnitude = map.get(this.getFilePath());
//			long tempTfCount = tempMagnitude.getTf();								
//			tempMagnitude.setTf(tempTfCount+1);		
			
			
			//System.out.println("Now displaying contents of Hashmap");
		    //System.out.println(""+builder.toString().toLowerCase());
			//System.out.println(""+tempMagnitude.getTf());
		}
	}
}
