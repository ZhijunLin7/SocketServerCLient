import java.io.Serializable;

public class Frame implements Serializable  {
    
    private Header header;
    private Payload payload;
    
    public Frame(Header header, Payload payload) {
        this.header = header;
        this.payload = payload;
    }
    public Frame(String direccion, String nickname, TipoMensaje tipoMensaje,String msg) {
        this.header = new Header(direccion, nickname, tipoMensaje);
        this.payload = new Payload(msg);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Payload getPyload() {
        return payload;
    }

    public void setPyload(Payload payload) {
        this.payload = payload;
    }

    
}
