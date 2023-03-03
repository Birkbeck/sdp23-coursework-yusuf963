package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.instruction.AddInstruction;

import static sml.Registers.Register.*;

public class TranslatorTest {
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
        Instruction instruction1 = new TranslatorTest("test", EAX, EBX);
        Instruction instruction2 = new TranslatorTest("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new TranslatorTest(null, EAX, EBX);
        Instruction instruction2 = new TranslatorTest(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new TranslatorTest(null, EAX, EBX);
        Instruction instruction2 = new AddInstruction(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void readAndTranslateSubInstructionNoLabel() throws Exception {
        Translator translator = new Translator(fileLocation, InstructionFactory.getInstance());
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Assertions.assertEquals("[sub EAX EBX]", machine.getProgram().toString());
    }

    @Test
    void readAndTranslateSubInstructionWithLabel() throws Exception {
        Translator translator = new Translator(fileLocation, InstructionFactory.getInstance());
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Assertions.assertEquals("[test: sub EAX EBX]", machine.getProgram().toString());
    }


    @Test
    void readAndTranslateMulInstructionWithLabel() throws Exception {
        Translator translator = new Translator(fileLocation, InstructionFactory.getInstance());
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Assertions.assertEquals("[test: mul EAX EBX]", machine.getProgram().toString());
    }

}