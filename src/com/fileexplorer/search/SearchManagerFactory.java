package com.fileexplorer.search;
import com.fileexplorer.exceptions.InvalidRootFinderException;
public class SearchManagerFactory {
	public static ISearchManager create() throws InvalidRootFinderException {
		return new SearchManager();
	}
}