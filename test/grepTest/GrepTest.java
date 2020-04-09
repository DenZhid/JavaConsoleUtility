package grepTest;

import grepCode.Grep;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import java.util.ArrayList;

public class GrepTest {

    @Test
    public void find() throws IOException {
        Grep grep = new Grep(null, false, false);
        ArrayList<String> test = new ArrayList<>();
        test.add("Не слово,");
        assertEquals(test, grep.find("слово", "resources\\In1.txt"));

        grep = new Grep(null, true, false);
        test.clear();
        test.add("Слово");
        test.add("Но");
        test.add("Слово");
        test.add("Есть");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "resources\\In1.txt"));

        grep = new Grep(null, false, true);
        test.clear();
        test.add("Слово");
        test.add("Не слово,");
        test.add("Слово");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "resources\\In1.txt"));
        assertEquals(test, grep.find("Слово", "resources\\In1.txt"));
        assertEquals(test, grep.find("Слово", "resources\\In1.txt"));
    }
}