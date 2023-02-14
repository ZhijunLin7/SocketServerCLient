import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatApp {

    private JTextArea areaMsg;
    private JTextField escribirMsg,puertoEscucha,puertoConnectar,autormsg;
    private JButton botonEnviar,botonSetPuertos;

    public ChatApp() {

        JFrame frame = new JFrame("Chat App");
        frame.setPreferredSize(new Dimension(300,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        this.puertoEscucha = new JTextField();
        this.puertoConnectar = new JTextField();
        this.autormsg = new JTextField();
        this.botonSetPuertos= new JButton("Set Puerto");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(this.autormsg, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(this.puertoEscucha, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(this.puertoConnectar, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(this.botonSetPuertos, constraints);




        this.areaMsg = new JTextArea();
        areaMsg.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMsg);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        frame.add(scrollPane, constraints);

        this.escribirMsg = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(escribirMsg, constraints);

        this.botonEnviar = new JButton("Send");
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(botonEnviar, constraints);

        frame.pack();
        frame.setVisible(true);
    }

    public JTextArea getAreaMsg() {
        return areaMsg;
    }

    public void setAreaMsg(JTextArea areaMsg) {
        this.areaMsg = areaMsg;
    }

    public JTextField getEscribirMsg() {
        return escribirMsg;
    }

    public void setEscribirMsg(JTextField escribirMsg) {
        this.escribirMsg = escribirMsg;
    }

    public JButton getBotonEnviar() {
        return botonEnviar;
    }

    public void setBotonEnviar(JButton botonEnviar) {
        this.botonEnviar = botonEnviar;
    }

    public JTextField getPuertoEscucha() {
        return puertoEscucha;
    }

    public void setPuertoEscucha(JTextField puertoEscucha) {
        this.puertoEscucha = puertoEscucha;
    }


    public JTextField getPuertoConnectar() {
        return puertoConnectar;
    }

    public JTextField getAutormsg() {
        return autormsg;
    }

    public void setAutormsg(JTextField autormsg) {
        this.autormsg = autormsg;
    }

    public void setPuertoConnectar(JTextField puertoConnectar) {
        this.puertoConnectar = puertoConnectar;
    }

    public JButton getBotonSetPuertos() {
        return botonSetPuertos;
    }

    public void setBotonSetPuertos(JButton botonSetPuertos) {
        this.botonSetPuertos = botonSetPuertos;
    }

    
}