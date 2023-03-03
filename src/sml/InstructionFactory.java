package sml;

import sml.instruction.AddInstruction;
import sml.instruction.DivInstruction;

import java.util.ArrayList;
import java.util.Arrays;

import static sml.Registers.Register;

public class InstructionFactory {
    private static InstructionFactory instance = null;

    private InstructionFactory() {

    }

    public static InstructionFactory getInstance() {
        if (instance == null) {
            instance = new InstructionFactory();
        }
        return instance;
    }

    public Instruction create(String label, String opcode, ArrayList<String> args) {
        try {
            if (opcode.equals("out")) {
                if (args.size() != 1) {
                    throw new RuntimeException("on more that one argument is accepted after the opcode" + opcode);
                }
            } else {
                if (args.size() > 2) {
                    throw new RuntimeException("no more that 2 args should be present, please fix! hit, reduce number of args to 2");
                }
                if (args.size() < 2) {
                    throw new RuntimeException("no less that 2 args should be present, please fix! hit, add more args");
                }
            }
            return switch (opcode) {
                case "add" -> new AddInstruction(label, Register.valueOf(args.get(0)), Register.valueOf(args.get(1)));
                case "sub" -> new SubInstruction(label, Register.valueOf(args.get(0)), Register.valueOf(args.get(1)));
                case "mul" -> new MulInstruction(label, Register.valueOf(args.get(0)), Register.valueOf(args.get(1)));
                case "div" -> new DivInstruction(label, Register.valueOf(args.get(0)), Register.valueOf(args.get(1)));
                case "out" -> new OutInstruction(label, Register.valueOf(args.get(0)));
                case "mov" -> new MovInstruction(label, Register.valueOf(args.get(0)), Integer.parseInt(args.get(1)));
                case "jnz" -> new JnzInstruction(label, Register.valueOf(args.get(0)), args.get(1));
                default -> throw new IllegalArgumentException("Command not found " + opcode);
            };
        } catch (IllegalArgumentException e) {
            String eString = e.toString();
            String errorMessage = "unaccepted  args passed for " + opcode + "\n";
            if (eString.contains("No enum constant")) {
                errorMessage = errorMessage + "Permitted register values are: \n" + Arrays.toString(Register.values());
            } else if (eString.contains("command not found")) {
                errorMessage = errorMessage + "command not found" + opcode;
            } else if (eString.contains("NumberFormatException")) {
                errorMessage = errorMessage + "The passed value is not valid";
            }
            throw new RuntimeException(errorMessage);
        }
    }
}
