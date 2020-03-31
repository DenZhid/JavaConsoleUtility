package grepcode;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.regex.Pattern;

public class GrepLauncher {

    @Option(name = "-r", metaVar = "", required = false, usage = "Regular expression")
    private Pattern reg;

    @Option(name = "-v", metaVar = "", required = false, usage = "Invert condition of filtration")
    private int invert = 1;

    @Option(name = "-i", metaVar = "", required = false, usage = "Ignore word register")
    private int ignore = 1;

    @Argument(required = true, metaVar = "WordForFiltration", usage = "Word for search")
    private String word;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
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

        Grep grep = new Grep(word, inputFileName);
        try {
            grep.find(reg, invert, ignore);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
