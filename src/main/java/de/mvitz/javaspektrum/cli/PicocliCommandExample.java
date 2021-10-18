package de.mvitz.javaspektrum.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Spec;

import java.util.List;

@Command(name = "picocli",
    subcommands = {
        PicocliCommandExample.AddCommand.class,
        PicocliCommandExample.RmCommand.class})
public class PicocliCommandExample {

    @Option(names = "--verbose")
    public boolean verbose;

    @Command(name = "add")
    static class AddCommand implements Runnable {

        @ParentCommand
        PicocliCommandExample parent;

        @Spec
        CommandSpec spec;

        @Option(names = "-i")
        public boolean interactive;

        @Parameters(arity = "1..*")
        public List<String> files;

        @Override
        public void run() {
            var out = spec.commandLine().getOut();
            out.println("add " + files);
            out.println("  Verbose: " + parent.verbose);
            out.println("  Interactive: " + interactive);
        }
    }

    @Command(name = "rm")
    static class RmCommand implements Runnable {

        @ParentCommand
        PicocliCommandExample parent;

        @Spec
        CommandSpec spec;

        @Option(names = "-r")
        public boolean recursive;

        @Parameters(arity = "1..*")
        public List<String> files;

        @Override
        public void run() {
            var out = spec.commandLine().getOut();
            out.println("rm " + files);
            out.println("  Verbose: " + parent.verbose);
            out.println("  Recursive: " + recursive);
        }
    }

    public static void main(String[] args) {
        args = new String[] { "--verbose", "add", "-i", "foo", "bar" };

        new CommandLine(new PicocliCommandExample()).execute(args);
    }
}
