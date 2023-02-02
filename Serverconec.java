
import java.net.ServerSocket;
import java.net.Socket;

public class Serverconec implements Runnable {

    // Atributos
    private Socket socket;
    private Myp2p myp2p;
    private int puertoescucha;

    public Serverconec() {

    }

    public Serverconec(int puertoescucha, Myp2p myp2p) {
        this.puertoescucha = puertoescucha;
        this.myp2p = myp2p;
    }

    @Override
    public void run() {

        try {
            ServerSocket ss = new ServerSocket(puertoescucha);
            System.out.println("Abriendo servidor....");

            while (true) {
                socket = ss.accept();
                socket.getInetAddress();
                System.out.println("conectado");

                if (socket.isConnected()) {
                    Conection c = myp2p.getConection();
                    c.setS(socket);
                    Thread t = new Thread(c);
                    t.start();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getPuertoescucha() {
        return puertoescucha;
    }

    public synchronized void setPuertoescucha(int puertoescucha) {
        this.puertoescucha = puertoescucha;
    }

    public Myp2p getMyp2p() {
        return myp2p;
    }

    public void setMyp2p(Myp2p myp2p) {
        this.myp2p = myp2p;
    }

}
