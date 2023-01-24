import java.util.Scanner;

public class Myp2p {

    // Atributos
    private Serverconec serverconec;
    private Clientconec clientconec;
    private Conection conection;

    public Myp2p() {
        Scanner s = new Scanner(System.in);
        System.out.println("Establecer puerto de escucha");
        int puertoescucha1 = s.nextInt();
        System.out.println("Establecer puerto de coneccion");
        int puertoabre1 = s.nextInt();
        this.serverconec = new Serverconec(puertoescucha1);
        this.clientconec = new Clientconec(puertoabre1);

        new Thread(serverconec).start();
        new Thread(clientconec).start();

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

}
