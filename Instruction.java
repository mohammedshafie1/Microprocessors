package Load_Instructions;

public class Instruction {

    public String loop = null;
    public String type;
    public String rs;
    public String rt;
    public String rd;

    public Instruction(String type, String rs, String rt, String rd){
        this.type = type;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
    }

    public Instruction(String loop, String type, String rs, String rt, String rd){
        this.loop = loop;
        this.type = type;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
    }

    public String toString (){
        return this.type + " " + this.rs + " " + this.rt + " " + (this.rd != null ? this.rd : "");
    }
}
