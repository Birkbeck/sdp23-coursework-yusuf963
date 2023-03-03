package sml.instruction;

import sml.*;

import java.util.Objects;


/**
 * Represents a multiplication instruction that multiplies the values in two specified registers and stores the result in the first register.
 * Inherits from the Instruction class and implements the FlowHandling interface.
 *
 * @author yusuf963
 */
public class MulInstruction extends Instruction implements FlowHandling {
    public static final String OP_CODE = "mul";
    private final RegisterName result;
    private final RegisterName source;

    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        int res = value1 * value2;
        FlowHelper helper = (a, b, c) -> ((long) a * b) != ((long) a * (long) b);
        handleFlow(value1, value2, res, result.toString(), source.toString(), opcode, helper);
        m.getRegisters().set(result, res);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    /**
     * Overrides the execute method to execute the multiplication instruction by retrieving the values from the specified
     * registers, multiplying them, and storing the result in the first register.
     * Handles overflow and underflow using a lambda function and the FlowHandling interface.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MulInstruction that = (MulInstruction) o;
        if (this.label != null) {
            return label.equals(that.label) && result.equals(that.result) && source.equals(that.source);
        } else {
            return result.equals(that.result) && source.equals(that.source);
        }
    }

    /**
     * Overrides the hashCode method to generate a hash code value for the MulInstruction
     * object based on the opcode, result register, and source register.
     * If the label is not null, it is also included in the hash code generation.
     */
    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, result, source);
        } else return Objects.hash(opcode, result, source);
    }
}