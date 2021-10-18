package de.mvitz.javaspektrum.cli;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

class ApacheExample {

    public static void main(String[] args) {
        args = new String[]{ "foo", "--verbose", "bar", "-d=|", "baz" };

        var options = new Options()
            .addOption("v", "verbose", false, "Verbose")
            .addOption(Option.builder("d")
                .longOpt("delimiter")
                .hasArg(true)
                .desc("The delimiter to use")
                .argName("delimiter")
                .build());

        var parser = new DefaultParser();
        try {
            var cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption('v')) {
                System.err.println("Running in verbose mode");
            }
            var delimiter = cmdLine.getOptionValue('d', ",");
            var result = String.join(delimiter, cmdLine.getArgs());
            System.out.println(result);
        } catch (ParseException e) {
            e.printStackTrace();
            new HelpFormatter().printHelp("apache args...", options);
        }
    }
}
