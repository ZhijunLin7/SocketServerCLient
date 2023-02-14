
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Conection implements Runnable {

    private Socket s;
    private Myp2p myp2p;
    private ObjectOutputStream ow;
    private ObjectInputStream or;
    private HCC hcc;
    private long lastTimeMessage;
    private String autor;

    public Conection() {

    }

    public Conection(Socket s, Myp2p myp2p) {
        this.s = s;
        this.myp2p = myp2p;
        this.hcc = new HCC(this);
        Thread thcc = new Thread(hcc);
        thcc.start();

    }

    @Override
    public void run() {
        myp2p.getChatApp().getBotonEnviar().addActionListener(e -> {
            enviar();
        });

        while (true) {
            try {
                or = new ObjectInputStream((s.getInputStream()));
                Mensage mess = (Mensage) or.readObject();
                lastTimeMessage = System.currentTimeMillis();
                if (mess.getMsg().equals("ping")) {
                    sendReping();
                    System.out.println(mess.getMsg());
                } else if (mess.getMsg().equals("reping")) {
                    System.out.println(mess.getMsg());
                } else {
                    myp2p.getChatApp().getAreaMsg().append(mess.getAutor() + ": " + mess.getMsg() + "\n");
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void enviar() {
        try {
            ow = new ObjectOutputStream((s.getOutputStream()));
            String msg = myp2p.getChatApp().getEscribirMsg().getText();
            Mensage mensage = new Mensage(autor, msg);
            myp2p.getChatApp().getAreaMsg().append(mensage.getAutor() + ": " + mensage.getMsg() + "\n");
            myp2p.getChatApp().getEscribirMsg().setText("");
            ow.writeObject(mensage);
            ow.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public boolean isOk() {
        if (s == null) {
            return false;
        }
        return true;

    }

    public synchronized void killSocket() {
        this.s = null;
        try {
            this.s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void sendPing() {
        try {
            ow = new ObjectOutputStream((s.getOutputStream()));
            Mensage mensage = new Mensage(autor, "ping");
            ow.writeObject(mensage);
            ow.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void sendReping() {
        try {
            ow = new ObjectOutputStream((s.getOutputStream()));
            Mensage mensage = new Mensage(autor, "reping");
            ow.writeObject(mensage);
            ow.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public Myp2p getMyp2p() {
        return myp2p;
    }

    public void setMyp2p(Myp2p myp2p) {
        this.myp2p = myp2p;
    }

    public synchronized long getLastTimeMessage() {
        return lastTimeMessage;
    }

    public synchronized void setLastTimeMessage(long lastTimeMessage) {
        this.lastTimeMessage = lastTimeMessage;
    }

    public HCC getHcc() {
        return hcc;
    }

    public void setHcc(HCC hcc) {
        this.hcc = hcc;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ObjectOutputStream getOw() {
        return ow;
    }

    public void setOw(ObjectOutputStream ow) {
        this.ow = ow;
    }

    public ObjectInputStream getOr() {
        return or;
    }

    public void setOr(ObjectInputStream or) {
        this.or = or;
    }

}
