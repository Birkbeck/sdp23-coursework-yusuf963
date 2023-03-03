package sml.instruction;
import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class DivInstruction extends Instruction {
    private final RegisterName result;
    private  final RegisterName source;
    public  static final String OP_CODE = "div";
    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }
    @Override
    public int execute(Machine m){
        int firstValue = m.getRegisters().get(result);
        int secondValue = m.getRegisters().get(source);
        if(secondValue == 0){
            throw new RuntimeException(source+"is not set correctly");
        }
        m.getRegisters().set(result, firstValue / secondValue);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString(){
//        return  getLabelString() + getOpcode() + " " + result + ""+ source;
        return String.format("%s%s %s %s", getLabelString(), getOpcode(), result, source);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivInstruction)) return false;
        DivInstruction that = (DivInstruction) o;
        if (this.label != null) {
            return label.equals(that.label) && result.equals(that.result) && source.equals(that.source);
        } else {
            return result.equals(that.result) && source.equals(that.source);
        }
    }

    @Override
    public  int hashCode(){
        if (label != null ){
            return Object.hash(label, opcode, result, source);
        }else {
            return Object.hash(opcode, result, source);
        }
    }

}
