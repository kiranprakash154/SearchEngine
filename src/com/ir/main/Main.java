package com.ir.main;

import java.io.IOException;

import com.ir.indexing.FileIndexer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Starting search engine");
		FileIndexer fileIndexer = new FileIndexer("/media/kiran/Games/IR/Projectclass/result3/");
		fileIndexer.startIndexing();
	}
}
