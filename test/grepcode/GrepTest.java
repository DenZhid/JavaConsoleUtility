package grepcode;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void find() throws IOException {
        Grep grep = new Grep(null, false, false);
        ArrayList<String> test = new ArrayList<>();
        /*test.add("Не слово,");
        assertEquals(test, grep.find("слово", "out/artifacts/JavaConsoleUtility_jar/In1.txt"));*/

        grep = new Grep(null, true, false);
        //test.clear();
        test.add("Слово");
        test.add("Но");
        test.add("Слово");
        test.add("Есть");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "out/artifacts/JavaConsoleUtility_jar/In1.txt"));
    }
}