package com.fileexplorer.drives;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class ActiveDriveFinder implements IDriveFinder {
	@Override
	public List<String> findDrives() {
        List<String> drives= new ArrayList<>();
		File[] allDrives = File.listRoots();
		
		for(File d : allDrives) {
		   if(d.exists())
		     drives.add(d.getPath());
		}
		return drives;
	}
}
