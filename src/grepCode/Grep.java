package grepCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class Grep {
    private final boolean reg;
    private final boolean invert;
    private final boolean ignore;

    public Grep(boolean reg, boolean invert, boolean ignore) {
        this.reg = reg;
        this.invert = invert;
        this.ignore = ignore;
    }

    public ArrayList<String> find(String word, String inputFileName) throws IOException {
        Pattern searchExpr;
        String regex;
        if (reg)  {
            regex = word;
        }
        else regex = Pattern.quote(word);
        if (!ignore) {
            searchExpr = Pattern.compile(regex);
        } else {
            searchExpr = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        }
        ArrayList<String> res = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = searchExpr.matcher(line);
                    if (matcher.find() != invert) {
                        res.add(line);
                    }
                }
            return res;
        }
    }
}