package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.instruction.AddInstruction;

import java.io.IOException;

import static sml.Registers.Register.*;

public class MachineTest {
    private Machine machine;
    private Registers registers;
    private String baseTestFilePath;

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
        Instruction instruction1 = new MachineTest("test", EAX, EBX);
        Instruction instruction2 = new MachineTest("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new MachineTest(null, EAX, EBX);
        Instruction instruction2 = new MachineTest(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new MachineTest(null, EAX, EBX);
        Instruction instruction2 = new AddInstruction(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void executeMachineCorrectlyMixedLabels() throws IOException {
        Translator translator = new Translator(fileLocation, InstructionFactory.getInstance());
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(3, registers.get(EBX));
        Assertions.assertEquals(3, registers.get(EAX));
        Assertions.assertEquals(1, registers.get(ECX));
    }

}

