package de.mvitz.javaspektrum.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;

@Command(name = "picocli")
class PicocliDashDashExample implements Runnable {

    @Option(names = "recursive")
    private boolean recursive;

    @Parameters
    private List<String> parameters;

    @Override
    public void run() {
        System.out.println("Recursive: " + recursive);
        System.out.println("Parameters: " + parameters);
    }

    public static void main(String[] args) {
        args = new String[] { "--", "--recursive", "foo" };

        new CommandLine(new PicocliDashDashExample()).execute(args);
    }
}
