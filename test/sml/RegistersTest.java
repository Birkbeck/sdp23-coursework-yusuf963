package sml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sml.instruction.AddInstruction;

import static sml.Registers.Register.*;

public class RegistersTest {

    @Test
    void equalsWithBothLabelsAsymmetryTest() {
        Instruction instruction1 = new RegistersTest("test", EAX, EBX);
        Instruction instruction2 = new RegistersTest("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new RegistersTest(null, EAX, EBX);
        Instruction instruction2 = new RegistersTest(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new RegistersTest(null, EAX, EBX);
        Instruction instruction2 = new AddInstruction(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithRegistersAsymmetryTest() {
        Registers registers1 = new Registers();
        Registers registers2 = new Registers();
        registers1.set(EAX, 4324);
        registers2.set(EBX, 4324);
        Assertions.assertFalse(registers1.equals(registers2));
    }

}
