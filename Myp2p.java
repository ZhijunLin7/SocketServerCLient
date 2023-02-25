import java.util.ArrayList;
public class Myp2p {

    // Atributos
    private Serverconec serverconec;
    private Clientconec clientconec;
    private ArrayList <Conection> conections;
    private ChatApp chatApp;
    private String autor;

    public Myp2p() {
        this.chatApp = new ChatApp();
        chatApp.getBotonSetPuertos().addActionListener(e -> {
            this.setPuertos();
        });
        chatApp.getBotonEnviar().addActionListener(e -> {
            conections.get(0).enviar();
        });

        this.conections = new ArrayList<>();
        this.conections.add(new Conection( this));
        this.conections.add(new Conection( this));
        this.serverconec = new Serverconec(this);
        this.clientconec = new Clientconec(this);

        for (Conection conection : conections) {
            Thread tConection = new Thread(conection);
            tConection.start();
        }
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
            if (!conections.get(0).isOk()) {
                conections.get(0).setPuerto(Integer.parseInt(puertoConectar));
            }else{
                conections.get(1).setPuerto(Integer.parseInt(puertoConectar));
            }
        }
        String autorMsg = this.chatApp.getAutormsg().getText();
        if (!autorMsg.equals("")) {
            this.autor=autorMsg;
        }
    }

    public void procesarMsg(Frame mess) {
        if (mess.getHeader().getTipoMensaje().equals(TipoMensaje.Ping)) {
            if (conections.get(0).getS().getRemoteSocketAddress().toString().equals(mess.getHeader().getDireccion())) {
                conections.get(0).sendReping();
            }else{
                conections.get(1).sendReping();
            }
        } else if (mess.getHeader().getTipoMensaje().equals(TipoMensaje.Reping))  {
        } 
        
        if (mess.getHeader().getTipoMensaje().equals(TipoMensaje.Mensage) &&  !mess.getHeader().getNickname().equals(this.autor)) {
            chatApp.getAreaMsg().append(mess.getHeader().getNickname() + ": " + mess.getPyload().getMsg() + "\n");

            if (conections.get(0).getS().getRemoteSocketAddress().toString().equals(mess.getHeader().getDireccion())) {
                Frame ms=mess;
                ms.getHeader().setDireccion(conections.get(1).getS().getLocalSocketAddress().toString());
                conections.get(1).reEnviar(ms);
            }else{
                Frame ms=mess;
                ms.getHeader().setDireccion(conections.get(0).getS().getLocalSocketAddress().toString());
                conections.get(0).reEnviar(ms);
            }
        }
    }

    public ArrayList<Conection> autoConectar() {
        ArrayList<Conection> rConections= new ArrayList<>();
        for (Conection conection : conections) {
            if (!conection.isOk() && conection.getPuerto()!=0) {
                rConections.add(conection);
            }
        }
        return rConections;
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

    public ChatApp getChatApp() {
        return chatApp;
    }

    public void setChatApp(ChatApp chatApp) {
        this.chatApp = chatApp;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ArrayList<Conection> getConections() {
        return conections;
    }

    public void setConections(ArrayList<Conection> conections) {
        this.conections = conections;
    }
    

}
