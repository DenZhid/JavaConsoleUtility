package grepTest;

import grepCode.Grep;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GrepTest {

    @Test
    public void find() throws IOException {
        Grep grep = new Grep(false, false, false);
        ArrayList<String> test = new ArrayList<>();
        test.add("Не слово,");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));

        grep = new Grep(false, true, false);
        test.clear();
        test.add("Слово");
        test.add("Но");
        test.add("Слово");
        test.add("Есть");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));

        grep = new Grep(false, false, true);
        test.clear();
        test.add("Слово");
        test.add("Не слово,");
        test.add("Слово");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));
        assertEquals(test, grep.find("Слово", "input\\In1.txt"));
        assertEquals(test, grep.find("СЛОВО", "input\\In1.txt"));

        grep = new Grep(false, true, true);
        test.clear();
        test.add("Но");
        test.add("Есть");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));

        grep = new Grep(true,false,false);
        test.clear();
        test.add("123");
        test.add("Сушку34");
        test.add("Да так 82");
        test.add("0099882");
        assertEquals(test, grep.find("[0-9]+", "input\\In2.txt"));

        grep = new Grep(true, true, false);
        test.clear();
        test.add("Шла Саша");
        test.add("По шоссе");
        test.add("И сосала");
        test.add("Корабли лавировали");
        test.add("Лавировали");
        test.add("Не вылавировали");
        assertEquals(test, grep.find("[0-9]+", "input\\In2.txt"));

        grep = new Grep(true, false, true );
        test.clear();
        test.add("Корабли лавировали");
        test.add("Не вылавировали");
        assertEquals(test, grep.find("не(?=\\sвылавировали)|корабли(?=\\sлавировали)", "input\\In2.txt"));

        grep = new Grep(true, true, true);
        test.clear();
        test.add("Шла Саша");
        test.add("По шоссе");
        test.add("123");
        test.add("И сосала");
        test.add("Сушку34");
        test.add("Лавировали");
        test.add("Да так 82");
        test.add("0099882");
        assertEquals(test, grep.find("не(?=\\sвылавировали)|корабли(?=\\sлавировали)", "input\\In2.txt"));
    }
}