public class Frame {
    
    private Header header;
    private Payload payload;
    
    public Frame(Header header, Payload payload) {
        this.header = header;
        this.payload = payload;
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
