package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * a public class which is extended from Instruction class responsible for divides the passed values.
 *
 * @author yusuf963
 */
public class DivInstruction extends Instruction {
    public static final String OP_CODE = "div";
    private final RegisterName result;
    private final RegisterName source;

    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int firstValue = m.getRegisters().get(result);
        int secondValue = m.getRegisters().get(source);
        if (secondValue == 0) {
            throw new RuntimeException(source + "is not set correctly");
        }
        m.getRegisters().set(result, firstValue / secondValue);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s %s", getLabelString(), getOpcode(), result, source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivInstruction that)) return false;
        if (this.label != null) {
            return label.equals(that.label) && result.equals(that.result) && source.equals(that.source);
        } else {
            return result.equals(that.result) && source.equals(that.source);
        }
    }

    /**
     * @return hashed value for {@code DivInstruction} object
     */
    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, result, source);
        } else return Objects.hash(opcode, result, source);
    }

}
