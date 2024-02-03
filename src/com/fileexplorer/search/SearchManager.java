package com.fileexplorer.search;
import java.util.ArrayList;
import java.util.List;

import com.fileexplorer.connectDb.Connecto;
//It has to create threads. The number of threads will be equal to the number drives
//the user has selected
//call the search() which is in file searcher 
public class SearchManager implements ISearchManager {
	private List<String> pathFound = new ArrayList<>();
	Connecto con=new Connecto();
	String username =new com.sun.security.auth.module.NTSystem().getName();
	@Override
	public List<String> search(String fileName, List<String> drives) {
		Thread[] thread =new Thread[drives.size()];
		FileSearcher[] searches= new FileSearcher[drives.size()];
		//iterate through the threads
		for(int i=0; i< drives.size(); i++) {
			FileSearcher filesearcher = new FileSearcher(fileName, drives.get(i));
			//Store file searcher object to access it later
			searches[i] = filesearcher;
			thread[i] = new Thread(filesearcher);
			thread[i].start();
		}
		//Wait for Threads to execute
		for( Thread thread1: thread) {
			try {
				thread1.join(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Get the Results
		for(int i=0; i<searches.length; i++) {
			for(String path : searches[i].getPathFound()) {
				pathFound.add(path);
				con.InsertDb(fileName.toLowerCase(), path,username);
			}
		}
		return pathFound;
	}
}
