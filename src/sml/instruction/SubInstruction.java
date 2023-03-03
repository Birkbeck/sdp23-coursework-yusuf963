package sml.instruction;

import sml.*;

import java.util.Objects;

/**
 * This is the implementation of the SubInstruction class which subtracts
 * the value stored in one register from another register and stores the result
 * in the first register. It inherits from the Instruction class and implements
 * the FlowHandling interface. The class has a constructor, an execute method,
 * a toString method, an equals method, and a hashCode method.
 * @author  yusuf963
 */

public class SubInstruction extends Instruction implements FlowHandling {
    public static final String OP_CODE = "sub";
    private final RegisterName result;
    private final RegisterName source;

    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int firstValue = m.getRegisters().get(result);
        int secondValue = m.getRegisters().get(source);
        int res = firstValue - secondValue;
        FlowHelper helper = (a, b, c) -> {
            boolean b1 = ((a ^ b) & (a ^ c)) < 0;
            return b1;
        };
        handleFlow(firstValue, secondValue, res, result.toString(), source.toString(), opcode, helper);
        m.getRegisters().set(result, res);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }


    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    /**
     Checks if this SubInstruction object is equal to another object.
     Two SubInstruction objects are considered equal if they have the same label, opcode, result and source registers.
     @param o the object to compare this SubInstruction object against
     @return true if the given object is equal to this SubInstruction object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubInstruction that = (SubInstruction) o;
        if (this.label != null) {
            return label.equals(that.label) && result.equals(that.result) && source.equals(that.source);
        } else return result.equals(that.result) && source.equals(that.source);
    }

    /**
     * This method overrides the default hashCode() implementation to calculate a hash code
     * for SubInstruction objects based on their label, opcode, result register
     * and source register. If the label is null, the hash code is calculated without it.
     */
    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, result, source);
        } else return Objects.hash(opcode, result, source);
    }
}
