
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.Socket;

public class Conection implements Runnable {

    private Socket s;
    private Myp2p myp2p;
    private BufferedWriter bw;
    private BufferedReader br;

    public Conection() {
        
    }
    public Conection(Socket s,Myp2p myp2p) {
        this.s = s;
        this.myp2p=myp2p;
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
                myp2p.getChatApp().getAreaMsg().append("Ell: "+mess + "\n");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void enviar() {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            String msg = myp2p.getChatApp().getEscribirMsg().getText();
            myp2p.getChatApp().getAreaMsg().append("Yo: "+msg + "\n");
            myp2p.getChatApp().getEscribirMsg().setText("");
            bw.write(msg + "\n");
            bw.flush();
        } catch (Exception e) {
            // TODO: handle exception
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

}
