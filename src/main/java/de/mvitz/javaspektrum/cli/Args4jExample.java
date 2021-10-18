package de.mvitz.javaspektrum.cli;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.List;

class Args4jExample {

    static class Options {

        @Option(name = "-v", aliases = "--verbose", usage = "Verbose")
        public boolean verbose;

        @Option(name = "-d", aliases = "--delimiter", usage = "The delimiter to use", metaVar = "delimiter")
        public String delimiter = ",";

        @Argument(required = true, usage = "Words to join", metaVar = "words")
        public List<String> words;
    }

    public static void main(String[] args) {
        args = new String[]{ "foo", "--verbose", "bar", "-da=|", "baz" };

        var options = new Options();

        var parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
            if (options.verbose) {
                System.err.println("Running in verbose mode");
            }
            var delimiter = options.delimiter;
            var result = String.join(delimiter, options.words);
            System.out.println(result);
        } catch (CmdLineException e) {
            e.printStackTrace();;
            System.out.print("args4j");
            parser.printSingleLineUsage(System.out);
            System.out.println();
            parser.printUsage(System.out);
        }
    }
}
