package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class
/**
 This class represents an Add instruction that extends the Instruction class.
 It adds two values from two different registers and stores the result in a specified register.
 It overrides the execute, toString, equals, and hashCode methods from the Instruction class.
 * @author yusuf963
 */

public class AddInstruction extends Instruction {
    public static final String OP_CODE = "add";
    private final RegisterName result;
    private final RegisterName source;

    public AddInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int firstValue = m.getRegisters().get(result);
        int secondValue = m.getRegisters().get(source);
        m.getRegisters().set(result, firstValue + secondValue);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        boolean condition = getClass() != o.getClass() || o == null;

        if (this == o) return true;
        if (condition) return false;
        AddInstruction that = (AddInstruction) o;
        if (this.label != null) {
            return label.equals(that.label) && result.equals(that.result) && source.equals(that.source);
        } else {
            return result.equals(that.result) && source.equals(that.source);
        }
    }

    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, result, source);
        } else {
            return Objects.hash(opcode, result, source);
        }
    }
}
