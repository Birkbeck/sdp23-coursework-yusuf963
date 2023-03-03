package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

/**
 * Represents a mov instruction that sets the value of a specified register to a given value.
 * Inherits from the Instruction class.
 *
 * @author yusuf963
 */
public class MovInstruction extends Instruction {

    public static final String OP_CODE = "mov";
    private final RegisterName registerName;
    private final int value;

    public MovInstruction(String label, RegisterName registerName, int value) {
        super(label, OP_CODE);
        this.registerName = registerName;
        this.value = value;
    }

    @Override
    public int execute(Machine m) {
        Registers registers = m.getRegisters();
        registers.set(registerName, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + registerName + " " + value;
    }

    /**
     * Overrides the equals method to compare two MovInstructions for equality.
     * Two instructions are considered equal if they have the same label, register to set, and value.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovInstruction that = (MovInstruction) o;
        if (label != null) {
            return label.equals(that.label) && value == that.value && registerName.equals(that.registerName);
        } else {
            return value == that.value && registerName.equals(that.registerName);
        }
    }

    /**
     * Overrides the hashCode method to generate a unique hash code for each MovInstruction object.
     * The hash code is based on the label, opcode, register to set, and value of the instruction.
     */
    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, registerName, value);
        } else {
            return Objects.hash(opcode, registerName, value);
        }
    }
}

