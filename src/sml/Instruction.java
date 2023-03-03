package sml;

import java.util.Objects;

// TODO: write a JavaDoc for the class
/**
 * An abstract class that cannot be instantiated. you cannot create objects of an abstract class.
 * It serves as a base or template for other classes that inherit from it and can define abstract methods.
 */

/**
 * Represents an abstract instruction.
 *
 * @author ...
 */
public abstract class Instruction {

    public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;
    protected final String label;
    protected final String opcode;

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label optional label (can be null)
     * @param opcode operation name
     */
    public Instruction(String label, String opcode) {
        this.label = label;
        this.opcode = opcode;
    }

    public String getLabel() {
        return label;
    }

    public String getOpcode() {
        return opcode;
    }

    /**
     * Executes the instruction in the given machine.
     *
     * @param machine the machine the instruction runs on
     * @return the new program counter (for jump instructions)
     *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */

    public abstract int execute(Machine machine);

    protected String getLabelString() {
        return (getLabel() == null) ? "" : getLabel() + ": ";
    }

    // TODO: What does abstract in the declaration below mean?
    //     (Write a short explanation.)
    /* abstract method is defined only by its method signature and does not contain any implementation.
     *  Consequently, any class that implements an abstract class
     *  that contains this method is required to provide an implementation for it.
     */
    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(label, that.label) && opcode.equals(that.opcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, opcode);
    }
}
