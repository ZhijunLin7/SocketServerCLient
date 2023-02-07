
import java.net.Socket;

public class Clientconec implements Runnable {

    // Atributos
    private Socket socket;
    private Myp2p myp2p;
    private int puertoconectar;

    public Clientconec(Myp2p myp2p) {
        this.myp2p = myp2p;
    }

    public Clientconec(int puertoconectar, Myp2p myp2p) {
        this.puertoconectar = puertoconectar;
        this.myp2p = myp2p;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.puertoconectar = 0;
                socket = new Socket("127.0.0.1", this.getPuertoconectar());
                myp2p.getConection().setS(socket);
                
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

    }

    public synchronized int getPuertoconectar() {
        if (puertoconectar == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return puertoconectar;
    }

    public synchronized void setPuertoconectar(int puertoconectar) {
        this.puertoconectar = puertoconectar;
        notifyAll();
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
