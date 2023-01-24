
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.Socket;


public class Conection implements Runnable {
    private Socket s;
    private ChatApp chatApp;
    private BufferedWriter bw;
    private BufferedReader br;

    public Conection(Socket s) {
        this.s = s;
        this.chatApp = new ChatApp();
    }

    @Override
    public void run() {
        chatApp.getSendButton().addActionListener(e -> {
            enviar();
        } );
       
        try {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        String mess = br.readLine();
                        chatApp.getMessageArea().append(mess+"\n");
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        });
        thread.start();
        } catch (Exception e) {
        // TODO: handle exception
        }
    

    }

    public void enviar() {
       try {
        bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String msg = chatApp.getMessageField().getText();
        chatApp.getMessageArea().append(msg+"\n");
        chatApp.getMessageField().setText("");
        bw.write(msg+"\n");
        bw.flush();
       } catch (Exception e) {
        // TODO: handle exception
       }

    }

    

}
