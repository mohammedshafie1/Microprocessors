package Reservation_Station;



public class Add_RS {
   public String id;
   public String opcode;
   public String vj;
   public String vk;
   public String qj;
   public String qk;
   public boolean busy;

   public Add_RS(String id, String opcode, String vj, String vk, String qj, String qk, boolean busy){
      this.id = id;
      this.opcode = opcode;
      this.vj = vj;
      this.vk = vk;
      this.qj = qj;
      this.qk = qk;
      this.busy = busy;
    }


}
