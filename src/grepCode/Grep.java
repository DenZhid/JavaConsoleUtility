package grepCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class Grep {

    private final Pattern reg;
    private final boolean invert;
    private final boolean ignore;

    public Grep (Pattern reg, boolean invert, boolean ignore) {
        this.reg = reg;
        this.invert = invert;
        this.ignore = ignore;
    }

    public ArrayList<String> find(String word, String inputFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line;
        ArrayList<String> res = new ArrayList<>();
        int i = 0;
        if (reg != null) {
            if (!invert) {
                if (!ignore) {
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = reg.matcher(line);
                        if (matcher.find()) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
                else {
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = reg.matcher(line.toLowerCase()); //Can be a mistake
                        if (matcher.find()) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
            }
            else {
                if (!ignore) {
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = reg.matcher(line);
                        if (!matcher.find()) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
                else {
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = reg.matcher(line.toLowerCase()); //Can be a mistake
                        if (!matcher.find()) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
            }
        }
        else {
            if (!invert) {
                if (!ignore) {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(word)) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
                else {
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains(word.toLowerCase())) {//Can be a mistake
                            res.add(line);
                            //i++;
                        }
                    }
                }
            }
            else {
                if (!ignore) {
                    while ((line = reader.readLine()) != null) {
                        if (!line.contains(word)) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
                else {
                    while ((line = reader.readLine()) != null) {
                        if (!line.toLowerCase().contains(word.toLowerCase())) {
                            res.add(line);
                            //i++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
/* 1) Неправильная работа -i (выводятся строки в нижнем регистре);
   2)* (Возможно) Неправильная работа REGEX;
   3) Доделать тесты;
 */