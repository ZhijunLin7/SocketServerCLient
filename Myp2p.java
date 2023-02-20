
public class Myp2p {

    // Atributos
    private Serverconec serverconec;
    private Clientconec clientconec;
    private Conection conection;
    private Conection conection2;
    private ChatApp chatApp;
    private String autor;

    public Myp2p() {
        this.chatApp = new ChatApp();
        chatApp.getBotonSetPuertos().addActionListener(e -> {
            this.setPuertos();
        });
        this.conection= new Conection( this);
        this.conection2= new Conection( this);
        this.serverconec = new Serverconec(this);
        this.clientconec = new Clientconec(this);

        chatApp.getBotonEnviar().addActionListener(e -> {
            conection.enviar();
        });

        Thread tConection = new Thread(this.conection);
        tConection.start();
        Thread tConection2 = new Thread(this.conection2);
        tConection2.start();
        Thread tClient = new Thread(this.clientconec);
        tClient.start();
        Thread tServer = new Thread(this.serverconec);
        tServer.start();
    }

    public void setPuertos() {
        String puertoEscucha = this.chatApp.getPuertoEscucha().getText();
        if (!puertoEscucha.equals("")) {
            serverconec.setPuertoescucha(Integer.parseInt(puertoEscucha));
        }
        String puertoConectar = this.chatApp.getPuertoConnectar().getText();
        if (!puertoConectar.equals("")) {
            clientconec.setPuertoconectar(Integer.parseInt(puertoConectar));
        }
        String autorMsg = this.chatApp.getAutormsg().getText();
        if (!autorMsg.equals("")) {
            this.autor=autorMsg;
        }
    }

    public void procesarMsg(Frame mess) {
        if (mess.getHeader().getTipoMensaje().equals(TipoMensaje.Ping)) {
            if (conection.getS().getRemoteSocketAddress().toString().equals(mess.getHeader().getDireccion())) {
                conection.sendReping();
            }else{
                conection2.sendReping();
            }
        } else if (mess.getHeader().getTipoMensaje().equals(TipoMensaje.Reping))  {
        } 
        
        if (mess.getHeader().getTipoMensaje().equals(TipoMensaje.Mensage) &&  !mess.getHeader().getNickname().equals(this.autor)) {
            if (conection.getS().getRemoteSocketAddress().toString().equals(mess.getHeader().getDireccion())) {
                Frame ms=mess;
                ms.getHeader().setDireccion(conection.getS().getLocalSocketAddress().toString());
                conection2.reEnviar(ms);
            }else{
                Frame ms=mess;
                ms.getHeader().setDireccion(conection.getS().getLocalSocketAddress().toString());
                conection.reEnviar(ms);
            }
            System.out.println("pepe");
            chatApp.getAreaMsg().append(mess.getHeader().getNickname() + ": " + mess.getPyload().getMsg() + "\n");
        }
    }
    public static void main(String[] args) {
        Myp2p pMyp2p = new Myp2p();
        
    }

    public Serverconec getServerconec() {
        return serverconec;
    }

    public void setServerconec(Serverconec serverconec) {
        this.serverconec = serverconec;
    }

    public Clientconec getClientconec() {
        return clientconec;
    }

    public void setClientconec(Clientconec clientconec) {
        this.clientconec = clientconec;
    }

    public Conection getConection() {
        return conection;
    }

    public void setConection(Conection conection) {
        this.conection = conection;
    }

    public ChatApp getChatApp() {
        return chatApp;
    }

    public void setChatApp(ChatApp chatApp) {
        this.chatApp = chatApp;
    }

    public Conection getConection2() {
        return conection2;
    }

    public void setConection2(Conection conection2) {
        this.conection2 = conection2;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    

}
