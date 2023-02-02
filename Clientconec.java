
import java.net.Socket;

public class Clientconec implements Runnable {

    // Atributos
    private Socket socket;
    private Myp2p myp2p;
    private int puertoescucha;

    public Clientconec() {

    }

    public Clientconec(int puertoescucha, Myp2p myp2p) {
        this.puertoescucha = puertoescucha;
        this.myp2p = myp2p;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("127.0.0.1", puertoescucha);
            while (true) {
                Conection c = myp2p.getConection();
                if (socket.isConnected() && c.getS() != socket) {
                    c.setS(socket);
                    Thread t = new Thread(c);
                    t.start();
                    System.out.println("Conectado al servidor");
                }
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
