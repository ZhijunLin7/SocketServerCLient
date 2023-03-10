
import java.net.ServerSocket;
import java.net.Socket;

public class Serverconec implements Runnable {

    // Atributos
    private Socket socket;
    private Myp2p myp2p;
    private int puertoescucha;

    public Serverconec(Myp2p myp2p) {
        this.myp2p = myp2p;
    }

    public Serverconec(int puertoescucha, Myp2p myp2p) {
        this.puertoescucha = puertoescucha;
        this.myp2p = myp2p;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(this.getPuertoescucha());
            System.out.println("Abriendo servidor....");
            while (true) {
                socket = ss.accept();
                System.out.println("Aceptado");
                if (!myp2p.getConections().get(0).isOk() && myp2p.getConections().get(0).getPuerto()==0) {
                    Conection c1 =myp2p.getConections().get(0);
                    c1.setS(socket);
                }else if(!myp2p.getConections().get(1).isOk() && myp2p.getConections().get(1).getPuerto()==0){
                    Conection c2 =myp2p.getConections().get(1);
                    c2.setS(socket);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public synchronized int getPuertoescucha() {
        if (puertoescucha == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return puertoescucha;
    }

    public synchronized void setPuertoescucha(int puertoescucha) {
        this.puertoescucha = puertoescucha;
        notifyAll();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Myp2p getMyp2p() {
        return myp2p;
    }

    public void setMyp2p(Myp2p myp2p) {
        this.myp2p = myp2p;
    }

}
