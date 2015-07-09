package com.ir.indexing;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TryingFile {

	public static void main(String[] args) throws IOException {		
		FileReader reader = null;		
		/*String wordSeparators = WordSeparator.getString("TryingFile.0"); //$NON-NLS-1$
		String splits[] = wordSeparators.split(",");
		Set<Character> separatorSet = new HashSet<>();
		for(String split: splits){
			separatorSet.add(split.toCharArray()[0]);
		}*/
		
		
		reader = new FileReader("/home/kiran/Desktop/www.asu.edu%%aad%%catalogs%%2002-2003%%general%%0003-ug-intro-statement.html"); //$NON-NLS-1$
		HashMap<String,Integer> wordCountMap = new HashMap<String,Integer>();
		int c;

		//		c = reader.read();

		boolean foundOpeningBracket = false;
		StringBuilder builder = new StringBuilder();
		while((c=reader.read())!=-1){
			if(c=='<'){
				foundOpeningBracket = true;
				continue;
			}
			else{
				if(foundOpeningBracket){
					if(c=='>'){
						foundOpeningBracket = false;
					}
					else{
						continue;
					}
				}
				else{
					if(c==' ' || c=='\n' || c=='<' || c==','){
						if(c=='<') foundOpeningBracket = true;
						if(builder.toString().equals(" ") || builder.length()==0){ //$NON-NLS-1$
							continue;
						}
						if(!wordCountMap.containsKey(builder.toString())){
							wordCountMap.put(builder.toString(), 1);
						}
						else{
							int tempCount = wordCountMap.get(builder.toString());
							wordCountMap.put(builder.toString(), tempCount+1);
						}
						builder = new StringBuilder();
					}
					else{
						builder.append((char)c);
					}
				}
			}
		}

		/*while( c != -1) 
        {
        	if(c == '>' || c == ' ' || c == '\n')
        	{
        		letter = (char)(c = reader.read());

        	}
        	else
        		letter = (char)c;   


        	if( letter == '<' )
        	  {
        		while(letter != '>')
        			letter = (char)(c = reader.read());
        	  }
            if(Character.isLetter(letter))
            {
        		StringBuilder builder = new StringBuilder();
        		builder.append(Character.toLowerCase(letter));
        		letter = Character.toLowerCase((char)(c = reader.read()));

        		while(letter != space && (Character.isLetter(letter)))
        		{
        		builder.append(letter);
        		letter = Character.toLowerCase((char)(c = reader.read()));        				
        	    }
        	   // System.out.println(builder.toString());//HASMAP STARTS
        	    if(wordCountMap.containsKey(builder.toString()))
        	    {
        	      int value = wordCountMap.get(builder.toString());
        		  wordCountMap.put(builder.toString(), value+1);
        	    }

        	    if(!(wordCountMap.containsKey(builder.toString())))
        			wordCountMap.put(builder.toString(), 1);
        	  }

           }*/
		System.out.print(wordCountMap.toString());
		reader.close();
	}
}  


