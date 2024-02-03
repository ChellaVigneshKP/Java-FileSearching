package com.fileexplorer.testing;
import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.*;
import com.fileexplorer.drives.SystemDriverFinder;
import com.fileexplorer.exceptions.InvalidRootFinderException;
import com.fileexplorer.search.ISearchManager;
import com.fileexplorer.search.SearchManagerFactory;
public class TestSearchEngine {
    @Before
    public void beforeAll() {
        System.out.println("Sarting tests...");
    }
    @Test
    public void test() {
        SystemDriverFinder test = new SystemDriverFinder();
        try {
            List<String> drives;
            drives = test.findDrives();
            String file = "installAgent.log";
            ISearchManager searchManger1 = SearchManagerFactory.create();
            List<String> actual = searchManger1.search(file, drives);
            String excpect = "C:\\installAgent.log";
            assertEquals(excpect, actual.get(0));
        } catch (InvalidRootFinderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   }
}