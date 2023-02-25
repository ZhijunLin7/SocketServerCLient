
import java.net.Socket;
import java.util.ArrayList;

public class Clientconec implements Runnable {

    // Atributos
    private Socket socket;
    private Myp2p myp2p;

    public Clientconec(Myp2p myp2p) {
        this.myp2p = myp2p;
    }


    @Override
    public void run() {
        while (true) {
            try {
                ArrayList<Conection> rConections= myp2p.autoConectar();
                
                for (Conection conection : rConections) {
                    socket = new Socket("127.0.0.1", conection.getPuerto());
                
                    if (!myp2p.getConections().get(0).isOk()) {
                        myp2p.getConections().get(0).setS(socket);
                        myp2p.getConections().get(0).setInetAddress(socket.getInetAddress());
                        myp2p.getConections().get(0).setPuerto(socket.getPort());
                    }else{
                        myp2p.getConections().get(1).setS(socket);
                        myp2p.getConections().get(1).setInetAddress(socket.getInetAddress());
                        myp2p.getConections().get(1).setPuerto(socket.getPort());
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }

    }

    public Myp2p getMyp2p() {
        return myp2p;
    }

    public void setMyp2p(Myp2p myp2p) {
        this.myp2p = myp2p;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
