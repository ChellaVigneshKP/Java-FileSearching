package com.fileexplorer.drives;

import com.fileexplorer.exceptions.InvalidDriveChoiceException;

public class DriveFinderFactory {
	public static IDriveFinder create(String choice) {
		IDriveFinder finder = null;
		if (choice.equalsIgnoreCase("ALL")) {
			finder = new SystemDriverFinder();
		}

		else if (choice.equalsIgnoreCase("Active")) {
			finder = new ActiveDriveFinder();
		} else
			throw new InvalidDriveChoiceException();

		return finder;
	}
}
