package de.mvitz.javaspektrum.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.util.List;

class JCommanderCommandExample {

    static class GlobalOptions {

        @Parameter(names = "--verbose")
        public boolean verbose;
    }

    @Parameters(commandDescription = "Adds some files")
    static class AddOptions {

        @Parameter(names = "-i")
        public boolean interactive;

        @Parameter
        public List<String> files;
    }

    @Parameters(commandDescription = "Removes some files")
    static class RmOptions {

        @Parameter(names = "-r")
        public boolean recursive;

        @Parameter
        public List<String> files;
    }

    public static void main(String[] args) {
        args = new String[] { "--verbose", "add", "-i", "foo", "bar" };

        var opts = new GlobalOptions();
        var addOpts = new AddOptions();
        var rmOpts = new RmOptions();

        var jc = JCommander.newBuilder()
            .addObject(opts)
            .programName("jcommander")
            .addCommand("add", addOpts)
            .addCommand("rm", rmOpts)
            .build();
        try {
            jc.parse(args);
            switch (jc.getParsedCommand()) {
                case "add" -> {
                    System.out.println("add " + addOpts.files);
                    System.out.println("  Verbose: " + opts.verbose);
                    System.out.println("  Interactive: " + addOpts.interactive);
                }
                case "rm" -> {
                    System.out.println("rm " + rmOpts.files);
                    System.out.println("  Verbose: " + opts.verbose);
                    System.out.println("  Recursive: " + rmOpts.recursive);
                }
            }
        } catch (ParameterException e) {
            e.printStackTrace();
            jc.usage();
        }
    }
}
