package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class JnzInstructionTest {
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
    void executeValidZeroRegister() {
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "someTest");
        int programCounterInstruction = instruction.execute(machine);
        Assertions.assertEquals(-1, programCounterInstruction);
    }

    @Test
    void equalsWithBothLabelsAsymmetryTest() {
        Instruction instruction1 = new DivInstruction("test", EAX, EBX);
        Instruction instruction2 = new DivInstruction("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new DivInstruction(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new AddInstruction(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void hashCodeWithIdenticalLabelsSymmetryTest() {
        Instruction instruction1 = new JnzInstruction("someTest", EAX, "someTest");
        Instruction instruction2 = new JnzInstruction("someTest", EAX, "someTest");
        Assertions.assertEquals(instruction1.hashCode(), instruction2.hashCode());
    }

    @Test
    void hashCodeWithOneLabelsAsymmetryTest() {
        Instruction instruction1 = new JnzInstruction("someTest", EAX, "someTest");
        Instruction instruction2 = new JnzInstruction(null, EAX, "someTest");
        Assertions.assertNotEquals(instruction1.hashCode(), instruction2.hashCode());
    }

}
