package sml.instruction;

import sml.*;

import java.util.Objects;

public class JnzInstruction extends Instruction {

    public static final String OP_CODE = "jnz";
    private final RegisterName registerName;
    private final String goToLabel;

    public JnzInstruction(String label, RegisterName registerName, String goToLabel) {
        super(label, OP_CODE);
        this.registerName = registerName;
        this.goToLabel = goToLable;
    }

    @Override
    public int execute(Machine m) {
        Registers registers = m.getRegisters();
        int registerValue = registers.get(registerName);
        if (registerValue == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        } else {
            Labels labels = m.getLabels();
            return labels.getAddress(goToLabel);
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + registerName + " " + goToLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JnzInstruction that = (JnzInstruction) o;
        if (label != null) {
            return label.equals(that.label) && registerName.equals(that.registerName) && goToLabel.equals(that.goToLabel);
        } else {
            return registerName.equals(that.registerName) && goToLabel.equals(that.goToLabel);
        }
    }

    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, registerName, goToLabel);
        } else {
            return Objects.hash(opcode, registerName, goToLabel);
        }
    }
}
