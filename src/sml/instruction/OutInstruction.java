package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

/**
 * This class represents the "out" instruction in the SML
 * It prints the value stored in the specified register to the console.
 * The register's value is obtained from the Machine's registers, and then printed
 * using the System.out.println method.
 *
 * @author yusuf963
 */
public class OutInstruction extends Instruction {

    public static final String OP_CODE = "out";
    private final RegisterName source;

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    /**
     * Executes the OutInstruction by getting the value of the source register from the given machine
     * printing the value to the console, and returning a constant value to update the program counter.
     *
     * @param m the Machine that contains the Registers to access.
     */
    @Override
    public int execute(Machine m) {
        Registers registers = m.getRegisters();
        int registerValue = registers.get(source);
        System.out.println(registerValue);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    /**
     * Checks if this OutInstruction is equal to another Object.
     *
     * @param o the Object to compare with
     * @return true if the Objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutInstruction that = (OutInstruction) o;
        if (label != null) {
            return label.equals(that.label) && source.equals(that.source);
        } else {
            return source.equals(that.source);
        }
    }

    /**
     * Computes the hash code value of this OutInstruction object based on its opcode and source register name.
     * If the instruction has a label, the hash code includes the label as well.
     *
     * @return the computed hash code value of this OutInstruction object
     */
    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, source);
        } else return Objects.hash(opcode, source);

    }
}

