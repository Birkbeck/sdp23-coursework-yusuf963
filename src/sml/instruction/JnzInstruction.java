package sml.instruction;

import sml.*;

import java.util.Objects;

/**
 * Represents a 'jnz' instruction in the program.
 * When executed, it will check if the value in a specific register is 0 or not, and
 * depending on the result, it will either update the program counter normally or
 * jump to the address associated with the given label.
 *
 * @author yusuf963
 */
public class JnzInstruction extends Instruction {

    public static final String OP_CODE = "jnz";
    private final RegisterName registerName;
    private final String goToLabel;

    public JnzInstruction(String label, RegisterName registerName, String goToLabel) {
        super(label, OP_CODE);
        this.registerName = registerName;
        this.goToLabel = goToLabel;
    }

    /**
     * Executes the JNZ instruction by getting the value of the register specified by registerName.
     * If the value is zero, returns NORMAL_PROGRAM_COUNTER_UPDATE, otherwise, looks up the address of the
     * label specified by goToLabel in the Labels object contained in the Machine object m, and returns that address.
     *
     * @param m
     */
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

    /**
     * Checks whether this JnzInstruction is equal to another object. Two JnzInstructions are considered equal if they have
     * the same label (if not null), registerName, and goToLabel.
     *
     * @param o object to compare to
     * @return true if the JnzInstructions are equal, false otherwise
     */
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

    /**
     * This method overrides the default hashCode() implementation to compute and
     * return the hash code value of this object. The hash code is based on the values
     * of the object's fields. If the label field is null, the hashCode() is calculated using opcode,
     * registerName, and goToLabel fields.
     *
     * @return hashed values
     * @author yusuf963
     */
    @Override
    public int hashCode() {
        if (label != null) {
            return Objects.hash(label, opcode, registerName, goToLabel);
        } else return Objects.hash(opcode, registerName, goToLabel);
    }
}