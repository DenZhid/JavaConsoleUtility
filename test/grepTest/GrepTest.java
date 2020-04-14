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
        Grep grep = new Grep(null, false, false);
        ArrayList<String> test = new ArrayList<>();
        test.add("Не слово,");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));

        grep = new Grep(null, true, false);
        test.clear();
        test.add("Слово");
        test.add("Но");
        test.add("Слово");
        test.add("Есть");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));

        grep = new Grep(null, false, true);
        test.clear();
        test.add("Слово");
        test.add("Не слово,");
        test.add("Слово");
        test.add("Слово!");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));
        assertEquals(test, grep.find("Слово", "input\\In1.txt"));
        assertEquals(test, grep.find("СЛОВО", "input\\In1.txt"));

        grep = new Grep(null, true, true);
        test.clear();
        test.add("Но");
        test.add("Есть");
        assertEquals(test, grep.find("слово", "input\\In1.txt"));

        Pattern pattern = Pattern.compile("[0-9]+");
        grep = new Grep(pattern,false,false);
        test.clear();
        test.add("123");
        test.add("Сушку34");
        test.add("Да так 82");
        test.add("0099882");
        assertEquals(test, grep.find("Любое слово", "input\\In2.txt"));

        grep = new Grep(pattern, true, false);
        test.clear();
        test.add("Шла Саша");
        test.add("По шоссе");
        test.add("И сосала");
        test.add("Корабли лавировали");
        test.add("Лавировали");
        test.add("Не вылавировали");
        assertEquals(test, grep.find("Любое слово", "input\\In2.txt"));

        pattern = Pattern.compile("не(?=\\sвылавировали)|корабли(?=\\sлавировали)");
        grep = new Grep(pattern, false, true );
        test.clear();
        test.add("Корабли лавировали");
        test.add("Не вылавировали");
        assertEquals(test, grep.find("Любое слово", "input\\In2.txt"));

        grep = new Grep(pattern, true, true);
        test.clear();;
        test.add("Шла Саша");
        test.add("По шоссе");
        test.add("123");
        test.add("И сосала");
        test.add("Сушку34");
        test.add("Лавировали");
        test.add("Да так 82");
        test.add("0099882");
        assertEquals(test, grep.find("Любое слово", "input\\In2.txt"));
    }
}