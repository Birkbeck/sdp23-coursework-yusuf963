package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Registers class is a final class that represents a collection of registers,
 * Represents a collection with names as enum values.
 * Provides methods to clear all registers, set a register value, and get a register value.
 * Overrides equals, hashCode, and toString methods,and its registers are stored in a HashMap.
 *
 * @author ysusf 963
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public Registers() {
        clear(); // the class is final
    }

    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value    new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register) register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register) register);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers r) {
            return registers.equals(r.registers);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI
    }
}
