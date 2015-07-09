package com.ir.indexing;
import java.io.*;
import java.util.*;


public class singleFile {

	public static void main(String[] args) throws IOException {
		
		/*FileReader in = null;
		in = new FileReader("/home/kiran/Desktop/first_file.txt");
		
		int c;
		c=in.read();
		System.out.println(""+(char)(c));
		in.close();*/
		
		
		File file = new File("/home/kiran/Desktop/first_file.txt");     
        Scanner scan = new Scanner(file);
       
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        
	     while (scan.hasNextLine())
	      {
	         String line = scan.nextLine(); 
	         String[] words = line.split("\\s+");
	       //  System.out.println(""+words[0]);
	       //  System.out.println(""+Arrays.toString(words));
	         for(int i=0;i<words.length;i++)
	         {
	        	 if( hm.containsKey(words[i]) )
	        	 {
	        		 int temp_count = hm.get(words[i]);
	        		 hm.put(words[i], temp_count+1);
	        	 }
	        	 else{
	        		 hm.put(words[i], 1);
	        	 }
	         }
	         
	        // words.addAll(	
	        // System.out.println(""+Arrays.toString(splitStr));
	      }
	     System.out.println(""+hm);
	     scan.close();
  
}










System.out.println(line);
String[] words = line.split("\\s+");
for(int i=0;i<words.length;i++)
{
	for( int j=0;j<words[i].length();j++)
	{
		if(words[i].charAt(j) == '<'){
			while(words[i].charAt(++j) == '>'){
				;
			}
		}
		StringBuilder str = new StringBuilder();
		if(words[i].charAt(j) != '<'){
			str.append(words[i].charAt(j));
			if(j+1 <= words[i].length()) j++;
				
				
			}
		 if(hm.containsKey(str))
		 {
			 int val = hm.get(words);
			 hm.put(str.toString(), val+1);
		 }
		 else
		 hm.put(str.toString(),1);
		}
		}
	}
	 
	 scan.close();
}
}
	 /*if(line.charAt(i) == '<' && i < line.length() )
	 {
		 i++;
		 while(line.charAt(i) == '>' && i < line.length())
		 i++;
		 
		 
	 }
	 if(i+1 <= line.length()){
	 while(line.charAt(i)==' '){
		 i++;
	 }
	 StringBuilder word_build = new StringBuilder();
	 while(line.charAt(i)!=' ')
	 {	        		 
		 word_build.append(line.charAt(i));
		 if(i+1 <= line.length())  
		 i++;
		
	 }
		 if(hm.containsKey(word))
		 {
			 int val = hm.get(word);
			 hm.put(word_build.toString(), val+1);
		 }
		 else
		 hm.put(word_build.toString(),1);
		 
	 }
	 
}*/


















































