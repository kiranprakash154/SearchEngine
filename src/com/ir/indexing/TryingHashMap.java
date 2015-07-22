package com.ir.indexing;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class TryingHashMap {
	 
	  public static void main(String[] args) {
	   
	    //create HashMap object
	    HashMap<String, String> hMap = new HashMap<String, String>();
	   
	    //add key value pairs to HashMap
	    hMap.put("1","One");
	    hMap.put("2","Two");
	    hMap.put("3","Three");
	   
	    /*
	      get Collection of values contained in HashMap using
	      Collection values() method of HashMap class
	    */
	    Collection<String> c = hMap.values();
	   
	    //obtain an Iterator for Collection
	    Iterator<String> itr = c.iterator();
	   
	    //iterate through HashMap values iterator
	    for(int i=0;i<2;i++)
	      System.out.println(itr.next());
	  }
	}
	

