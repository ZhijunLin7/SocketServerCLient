
import java.net.ServerSocket;
import java.net.Socket;

public class Serverconec implements Runnable {

    // Atributos
    private Socket socket;
    private int puertoescucha;

    public Serverconec(int puertoescucha) {
        this.puertoescucha = puertoescucha;
    }

    @Override
    public void run() {

        try {
            ServerSocket ss = new ServerSocket(puertoescucha);
            System.out.println("Abriendo servidor....");
            socket = ss.accept();
            socket.getInetAddress();
            System.out.println("conectado");

            Conection c = new Conection(socket);
            Thread t = new Thread(c);
            t.start();
            ss.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
    }

}
