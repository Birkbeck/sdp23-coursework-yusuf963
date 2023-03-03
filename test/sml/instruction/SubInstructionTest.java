package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class SubInstructionTest {
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
        Instruction instruction1 = new SubInstructionTest("test", EAX, EBX);
        Instruction instruction2 = new SubInstructionTest("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new SubInstructionTest(null, EAX, EBX);
        Instruction instruction2 = new SubInstructionTest(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void executeValidNegativeSubPositive() {
        registers.set(EAX, 3);
        registers.set(EBX, 5);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(8, machine.getRegisters().get(EAX));
    }

}
