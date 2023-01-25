
import java.net.Socket;

public class Clientconec implements Runnable {

    // Atributos
    private Socket s;
    private int puertoescucha;

    public Clientconec(int puertoescucha) {
        this.puertoescucha = puertoescucha;
    }

    @Override
    public void run() {
        try {
            s = new Socket("127.0.0.1", puertoescucha);

            Conection c = new Conection(s);
            Thread t = new Thread(c);
            t.start();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

}
