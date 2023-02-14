public class Header {
    
    private String direccion;
    private String nickname;
    private TipoMensaje tipoMensaje;

    public Header(String direccion, String nickname, TipoMensaje tipoMensaje) {
        this.direccion = direccion;
        this.nickname = nickname;
        this.tipoMensaje = tipoMensaje;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }
    
    
    
}
