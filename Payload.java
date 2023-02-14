import java.io.Serializable;

public class Payload implements Serializable{
    
    private String msg;

    public Payload (String msg){
        this.msg=msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
