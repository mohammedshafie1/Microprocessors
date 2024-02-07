package Reservation_Station;

public class Load_RS {
    public String id;
    public String opcode;
    public int address;
    public boolean busy;

    public Load_RS(String id, String opcode, int address, boolean busy){
        this.id = id;
        this.opcode = opcode;
        this.address = address;
        this.busy = busy;
    }

}
