
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.Socket;

public class Conection implements Runnable {

    private Socket s;
    private Myp2p myp2p;
    private BufferedWriter bw;
    private BufferedReader br;
    private HCC hcc;
    private long lastTimeMessage;

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
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String mess = br.readLine();
                if (mess.equals("ping")) {
                    lastTimeMessage = System.currentTimeMillis();
                    bw.write("reping" + "\n");
                    bw.flush();
                    System.out.println(mess);
                } else if (mess.equals("reping")) {
                    lastTimeMessage = System.currentTimeMillis();
                    System.out.println(mess);
                }else {
                    myp2p.getChatApp().getAreaMsg().append("Ell: " + mess + "\n");
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void enviar() {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            String msg = myp2p.getChatApp().getEscribirMsg().getText();
            myp2p.getChatApp().getAreaMsg().append("Yo: " + msg + "\n");
            myp2p.getChatApp().getEscribirMsg().setText("");
            bw.write(msg + "\n");
            bw.flush();
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
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("ping" + "\n");
            bw.flush();
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

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public synchronized long getLastTimeMessage() {
        return lastTimeMessage;
    }

    public synchronized void setLastTimeMessage(long lastTimeMessage) {
        this.lastTimeMessage = lastTimeMessage;
    }

}
