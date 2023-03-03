package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.instruction.AddInstruction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sml.Registers.Register.*;

public class InstructionFactoryTest {
    private final ArrayList<String> args = new ArrayList<>();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private InstructionFactory instructionFactory;

    @BeforeEach
    void setUp() {
        instructionFactory = InstructionFactory.getInstance();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        instructionFactory = null;
    }

    @Test
    void equalsWithBothLabelsAsymmetryTest() {
        Instruction instruction1 = new InstructionFactoryTest("test", EAX, EBX);
        Instruction instruction2 = new InstructionFactoryTest("dif", EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsWithDifferingRegisterAsymmetryTest() {
        Instruction instruction1 = new InstructionFactoryTest(null, EAX, EBX);
        Instruction instruction2 = new InstructionFactoryTest(null, EAX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void equalsOtherClassAsymmetryTest() {
        Instruction instruction1 = new InstructionFactoryTest(null, EAX, EBX);
        Instruction instruction2 = new AddInstruction(null, EAX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void incorrectNumberOfArgumentsAllOtherOne() {
        args.add("EAX");
        Exception exception = assertThrows(RuntimeException.class, () -> instructionFactory.create(null, "add", args));

        String expectedMessage = "throw some error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
