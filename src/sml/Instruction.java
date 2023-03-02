package sml;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents an abstract instruction.
 *
 * @author ...
 */
public abstract class Instruction {

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

  public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

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
  public boolean equal(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Instruction that = (Instruction) obj;
    return Objects.equal(label, that.label) && opcode.equal(that.opcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, opcode);
  }
}
