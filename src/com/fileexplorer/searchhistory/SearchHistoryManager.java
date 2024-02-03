package com.fileexplorer.searchhistory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import com.fileexplorer.connectDb.Connecto;
public class SearchHistoryManager {
	static Properties prop = new Properties();
	Connecto con=new Connecto();
	public void logSearchResult(List<String> searchResult) {
		Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  
	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(prop.getProperty("LogFileLocation"), true);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        // the following statement is used to log any messages 
	        searchResult.stream().forEach(result -> logger.info(result));
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
public List<String> logCheck(String fileName) {
	try {
		prop.load(new FileInputStream("C:\\Users\\chella.vigneshkp\\OneDrive - HCL Technologies Ltd\\Desktop\\StoreHistory\\Data.properties"));
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		List<String> path= new ArrayList<>();
		List<String> pathdb=new ArrayList<>();
		pathdb=con.CheckFile(fileName.toLowerCase());
	File file = new File(prop.getProperty("LogFileLocation"));
	try {
	    Scanner scanner = new Scanner(file);
	    //now read the file line by line...
	    int lineNum = 0;
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        lineNum++;
	        //Check String is present in the line
	        if(line.contains(fileName)) { 
	            //System.out.println(" i found it on line " +lineNum);
	            path.add(line.substring(6));   
	        }
	    }
	} catch(FileNotFoundException e) {  
	    e.getMessage();
	}
	return pathdb;
	}
}
