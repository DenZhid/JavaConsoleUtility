package grepCode;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GrepLauncher {

    @Option(name = "-r", usage = "Regular expression")
    private Pattern reg;

    @Option(name = "-v", usage = "Invert condition of filtration")
    private boolean invert;

    @Option(name = "-i", usage = "Ignore word register")
    private boolean ignore;

    @Argument(required = true, metaVar = "Word", usage = "Word for search")
    private String word;

    @Argument(required = true, metaVar = "InputName", index = 1, usage = "Input file name")
    private String inputFileName;


    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar -r reg -v -i word inputFileName.txt");
            parser.printUsage(System.err);
            return;
        }

        Grep grep = new Grep(reg, invert, ignore);
        try {
            ArrayList<String> result = grep.find(word, inputFileName);
            for (String s : result) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
