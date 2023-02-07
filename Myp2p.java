
public class Myp2p {

    // Atributos
    private Serverconec serverconec;
    private Clientconec clientconec;
    private Conection conection;
    private ChatApp chatApp;

    public Myp2p() {
        this.chatApp = new ChatApp();
        chatApp.getBotonSetPuertos().addActionListener(e -> {
            this.setPuertos();
        });
        this.conection= new Conection(null, this);
        this.serverconec = new Serverconec(this);
        this.clientconec = new Clientconec(this);


        Thread tConection = new Thread(this.conection);
        tConection.start();
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

}
