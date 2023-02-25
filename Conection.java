
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class Conection implements Runnable {

    private Socket s;
    private InetAddress inetAddress;
    private int puerto;
    private Myp2p myp2p;
    private ObjectOutputStream ow;
    private ObjectInputStream or;
    private HCC hcc;
    private long lastTimeMessage;


    public Conection() {

    }

    public Conection( Myp2p myp2p) {
        this.myp2p = myp2p;
        this.hcc = new HCC(this);
        try {
            this.inetAddress=InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread hccThread = new Thread(hcc);
        hccThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                or = new ObjectInputStream(s.getInputStream());
                Frame mess = (Frame) or.readObject();
                lastTimeMessage = System.currentTimeMillis();
                myp2p.procesarMsg(mess);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public synchronized void enviar() {
        try {
            ow = new ObjectOutputStream(s.getOutputStream());
            String msg = myp2p.getChatApp().getEscribirMsg().getText();
            Frame mensage = new Frame(s.getLocalSocketAddress().toString(),myp2p.getAutor(),TipoMensaje.Mensage,msg);
            myp2p.getChatApp().getAreaMsg().append(mensage.getHeader().getNickname() + ": " + mensage.getPyload().getMsg() + "\n");
            myp2p.getChatApp().getEscribirMsg().setText("");
            ow.writeObject(mensage);
            ow.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public synchronized void reEnviar(Frame mensage) {
        try {
            ow = new ObjectOutputStream(s.getOutputStream());
            ow.writeObject(mensage);
            ow.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public synchronized boolean isOk() {
        if (s == null) {
            return false;
        }
        return true;
    }

    public synchronized void killSocket() {
        try {
            this.s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.s = null;
    }

    public synchronized void sendPing() {
        try {
            ow = new ObjectOutputStream(s.getOutputStream());
            Frame mensage = new Frame(s.getLocalSocketAddress().toString(),myp2p.getAutor(),TipoMensaje.Ping,null);
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
            Frame mensage = new Frame(s.getLocalSocketAddress().toString(),myp2p.getAutor(),TipoMensaje.Reping,null);
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

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }



}
