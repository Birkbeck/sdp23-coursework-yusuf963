package sml;

public interface FlowHandling {
    default void handleFlow(int firstValue, int secondValue, int response, String result, String source, String opcode, FlowHelper helper) {
        if (helper.makeOps(firstValue, secondValue, response)) {
            if (response < 0) {
                throw new ArithmeticException("this can not be completed");
        }
    }
}
}
