
import java.net.Socket;

public class Clientconec implements Runnable {

    // Atributos
    private Socket s;
    private Myp2p myp2p;
    private int puertoescucha;

    public Clientconec() {
        
    }

    public Clientconec(int puertoescucha,Myp2p myp2p) {
        this.puertoescucha = puertoescucha;
        this.myp2p=myp2p;
    }

    @Override
    public void run() {
        try {
            s = new Socket("127.0.0.1", puertoescucha);
            Conection c = new Conection(s,myp2p);
            Thread t = new Thread(c);
            t.start();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
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
