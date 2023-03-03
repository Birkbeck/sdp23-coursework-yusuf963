package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class MulInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void equalsWithBothLabelsAsymmetryTest() {
        Instruction instruction1 = new MulInstructionTest("test", EAX, EBX);
        Instruction instruction2 = new MulInstructionTest("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new MulInstructionTest(null, EAX, EBX);
        Instruction instruction2 = new MulInstructionTest(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new MulInstructionTest(null, EAX, EBX);
        Instruction instruction2 = new MulInstructionTest(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new MulInstructionTest(null, EAX, EBX);
        Instruction instruction2 = new MulInstructionTest(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }
}
