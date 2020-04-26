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
        if (!reg) {
            if (!ignore) {
                searchExpr = Pattern.compile(Pattern.quote(word));
            } else {
                searchExpr = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            }
        } else {
            if (!ignore) {
                searchExpr = Pattern.compile(word);
            } else {
                searchExpr = Pattern.compile(word, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            }
        }
        ArrayList<String> res = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            if (!invert) {
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = searchExpr.matcher(line);
                    if (matcher.find()) {
                        res.add(line);
                    }
                }
            } else {
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = searchExpr.matcher(line);
                    if (!matcher.find()) {
                        res.add(line);
                    }
                }
            }
            return res;
        }
    }
}