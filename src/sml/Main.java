package sml;

import java.io.IOException;

public class Main {
    /**
     * Initialises the system and executes the program.
     * The Main class is the entry point for the SML interpreter.
     * It reads in a program file, translates it, and then executes it.
     * it expects a single command line argument that specifies the name
     * of the file to be executed. If this argument is not provided,
     * an error message is printed and the program exits
     *
     * @param args name of the file containing the program text.
     * @author yusuf963
     */
    public static void main(String... args) {
        if (args.length != 1) {
            System.err.println("Incorrect number of arguments - Machine <file> - required");
            System.exit(-1);
        }

        try {
            Translator t = new Translator(args[0], InstructionFactory.getInstance());
            Machine m = new Machine(new Registers());
            t.readAndTranslate(m.getLabels(), m.getProgram());

            System.out.println("Here is the program; it has " + m.getProgram().size() + " instructions.");
            System.out.println(m);

            System.out.println("Beginning program execution.");
            m.execute();
            System.out.println("Ending program execution.");

            System.out.println("Values of registers at program termination:" + m.getRegisters() + ".");
        } catch (IOException e) {
            System.out.println("Error reading the program from " + args[0]);
        }
    }
}
