package grepcode;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Grep {

    private final String inputFileName;

    private final String word;

    public Grep (String word, String inputFileName) {
        this.word = word;
        this.inputFileName = inputFileName;
    }

    public void find(Pattern reg, int invert, int ignore) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line;
        if (reg != null) {
            if (invert != 1) {
                if (ignore != 1) {
                    while (reader.readLine() != null) {
                        line = reader.readLine();
                        Matcher matcher = reg.matcher(line);
                        if (matcher.find()) System.out.println(line);
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        Matcher matcher = reg.matcher(line);
                        if (matcher.find()) System.out.println(line);
                    }
                }
            }
            else {
                if (ignore != 1) {
                    while (reader.readLine() != null) {
                        line = reader.readLine();
                        Matcher matcher = reg.matcher(line);
                        if (!matcher.find()) System.out.println(line);
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        Matcher matcher = reg.matcher(line);
                        if (!matcher.find()) System.out.println(line);
                    }
                }
            }
        }
        else {
            if (invert != 1) {
                if (ignore != 1) {
                    while (reader.readLine() != null) {
                        line = reader.readLine();
                        if (line.contains(word)) System.out.println(line);
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        if (line.contains(word)) System.out.println(line);
                    }
                }
            }
            else {
                if (ignore != 1) {
                    while (reader.readLine() != null) {
                        line = reader.readLine();
                        if (!line.contains(word)) System.out.println(line);
                    }
                }
                else {
                    while (reader.readLine() != null) {
                        line = reader.readLine().toLowerCase();
                        if (!line.contains(word)) System.out.println(line);
                    }
                }
            }
        }
    }
}
