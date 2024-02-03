package com.fileexplorer.search;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher implements Runnable {
//public class FileSearcher extends Thread {
	private String fileName;
	private String drive;
	List<String> pathFound =new ArrayList<>();
	int i=1;
	public String getFileName() {
		return fileName;
	}
	public String getDrive() {
		return drive;
	}
	public List<String> getPathFound() {
		return pathFound;
	}
	public FileSearcher(String fileName, String drive) {
		super();
		this.fileName = fileName;
		this.drive = drive;
	}
	public void search(String fileName, String drive) {
		// File Search takes place
		File drives= new File(drive);
		File[] listOfFiles =drives.listFiles();
		
		if(listOfFiles !=null) {
			for(File file : listOfFiles) {
				if(file.isDirectory()) {
					String content =file.toString();
					search(fileName, content);
				}
				else {
					if(file.getName().equalsIgnoreCase(fileName)) {
						pathFound.add(file.getAbsolutePath());
					}
				}
			}
		}
	}
	@Override
	public void run() {
		search(this.getFileName(), this.getDrive());
	}

}
