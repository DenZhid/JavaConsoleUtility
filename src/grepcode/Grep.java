package grepcode;

import java.io.*;
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
                    while (reader.readLine() != null) {
                        line = reader.readLine();
                        Matcher matcher = reg.matcher(line);
                        if (matcher.find()) {
                            res.add(i, line);
                            i++;
                        }
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        Matcher matcher = reg.matcher(line);
                        if (matcher.find()) {
                            res.add(i, line);
                            i++;
                        }
                    }
                }
            }
            else {
                if (!ignore) {
                    while (reader.readLine() != null) {
                        line = reader.readLine();
                        Matcher matcher = reg.matcher(line);
                        if (!matcher.find()) {
                            res.add(i, line);
                            i++;
                        }
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        Matcher matcher = reg.matcher(line);
                        if (!matcher.find()) {
                            res.add(i, line);
                            i++;
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
                            res.add(i, line);
                            i++;
                        }
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        if (line.contains(word)) {
                            res.add(i, line);
                            i++;
                        }
                    }
                }
            }
            else {
                if (!ignore) {
                    while ((line = reader.readLine()) != null) {
                        if (!line.contains(word)) {
                            res.add(i, line);
                            i++;
                        }
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        if (!line.contains(word)) {
                            res.add(i, line);
                            i++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
/* 1) Неправильная работа -i (выводятся строки в нижнем регистре);
   2) Приложение не выводит одинаковые строки;
   3)* (Возможно) Неправильная работа REGEX;
   4) Неправильная работа -v (выводит не все строки);
   5) Доделать тесты;
 */
