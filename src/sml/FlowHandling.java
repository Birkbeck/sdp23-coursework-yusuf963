package sml;

public interface FlowHandling {
    default void handleOverUnderFlow(int firstValue, int secondValue, int response, String result, String source, String opcode, FlowHelper helper) {
        if (helper.doOperation(firstValue, secondValue, response)) {
            if (response < 0) {
                throw new ArithmeticException("The combination of values " + firstValue + " and " + secondValue + " stored in the " +
                        "registers " + result + " and " + source + " using the opcode '" + opcode + "' cannot be performed.\nThis will lead to a value " +
                        "overflow in the " + result + " register.\nThe maximum value which can be stored is 2,147,483,647"
                );
            } else {
                throw new ArithmeticException("The combination of values " + firstValue + " and " + secondValue + " stored in the " +
                        "registers " + result + " and " + source + " using the opcode '" + opcode + "' cannot be performed.\nThis will lead to a value " +
                        "underflow in the " + result + " register.\nThe minimum value which can be stored is -2,147,483,648"
                );
            }
        }
    }
}
