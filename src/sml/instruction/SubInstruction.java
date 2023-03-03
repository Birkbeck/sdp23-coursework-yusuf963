package sml.instruction;

import sml.*;

import java.util.Objects;

public class SubInstruction extends Instruction implements FlowHandling {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";

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
        handleOverUnderFlow(firstValue, secondValue, res, result.toString(), source.toString(), opcode, helper);
        m.getRegisters().set(result, res);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }


    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubInstruction that = (SubInstruction) o;
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
