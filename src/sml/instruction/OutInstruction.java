package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

public class OutInstruction extends Instruction {

    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

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

    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, source);
        } else {
            return Objects.hash(opcode, source);
        }
    }
}

