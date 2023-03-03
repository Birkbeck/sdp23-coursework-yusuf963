package sml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Translator class is responsible for reading and translating a SML program from a file.
 * It takes a file name and an InstructionFactory object as input and generates a list of labels and a list of instructions.
 * The getInstruction() method converts each line of the program into an instruction object with a given label.
 * The getLabel() method extracts the label of each instruction.
 * The readAndTranslate() method reads the SML program line by line and adds the instructions and their labels to the program.
 *
 * @author yusuf963
 */
public final class Translator {

    private final String fileName; // source file of SML code
    private final InstructionFactory instructionFactory;
    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName, InstructionFactory instructionFactory) {
        this.fileName = fileName;
        this.instructionFactory = instructionFactory;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();

        ArrayList<String> wordDic = new ArrayList<>();

        while (line.length() > 0) {
            String word = scan();
            wordDic.add(word);
        }
        return instructionFactory.create(label, opcode, wordDic);
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();
        ArrayList<String> splitLine = new ArrayList<>(Arrays.asList(line.split(" ")));
        if (splitLine.size() > 0) {
            String word = splitLine.remove(0);
            line = String.join(" ", splitLine);
            return word;
        }
        return line;
    }
}