package com.fileexplorer.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.fileexplorer.drives.SearchEngineConsole;
import com.fileexplorer.exceptions.InvalidDriveChoiceException;
import com.fileexplorer.exceptions.InvalidRootFinderException;

public class SearchEngineMain {

	public List<String> mainq(String hi, String[] Drives, int count, String state)
			throws InvalidDriveChoiceException, InvalidRootFinderException {
		final Logger logger=Logger.getLogger(SearchEngineMain.class);
		try {
			SearchEngineConsole.displayWelcomeMessage();

			List<String> list = SearchEngineConsole.findDrives(state);

			list.stream().forEach(System.out::println);

			SearchEngineConsole.displaySearchResult(hi, count, Drives);
			String username =new com.sun.security.auth.module.NTSystem().getName();
			//logger.addAppender(null);
			//logger.info("searched by"+username);
			//System.out.println(username);
		} catch (InvalidDriveChoiceException e) {
			// logger.error("error in entered files");
			System.out.println("Error in MAIN");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SearchEngineConsole.displaySearchResult(hi, count, Drives);
	}

}
