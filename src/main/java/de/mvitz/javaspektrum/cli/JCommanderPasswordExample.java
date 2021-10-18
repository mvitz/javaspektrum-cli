package de.mvitz.javaspektrum.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

class JCommanderPasswordExample {

    static class Options {

        @Parameter(names = "--password", password = true,
            required = true, description = "The password to use")
        public String password;
    }

    public static void main(String[] args) {
        args = new String[] { "--password" };
        //args = new String[] { "--password", "Geheim!" };

        var opts = new Options();

        var jc = JCommander.newBuilder()
            .addObject(opts)
            .programName("jcommander")
            .build();
        try {
            jc.parse(args);
            System.out.println("Das Passwort ist: " + opts.password);
        } catch (ParameterException e) {
            e.printStackTrace();
            jc.usage();
        }
    }
}
