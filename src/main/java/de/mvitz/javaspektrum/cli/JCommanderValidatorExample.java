package de.mvitz.javaspektrum.cli;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

class JCommanderValidatorExample {

    static class Options {

        @Parameter(names = "--password", password = true,
            required = true, description = "The password to use",
            validateValueWith = PasswordLengthValidator.class)
        public String password;
    }

    public static class PasswordLengthValidator implements IValueValidator<String> {

        @Override
        public void validate(String name, String value) throws ParameterException {
            if (value.length() < 8) {
                throw new ParameterException("Parameter " + name + " must have at least 8 characters");
            }
        }
    }

    public static void main(String[] args) {
        args = new String[] { "--password", "Geheim!" };

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
