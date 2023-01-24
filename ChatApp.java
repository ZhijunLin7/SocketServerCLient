import java.awt.*;
import javax.swing.*;

public class ChatApp {

    private JTextField messageField;
    private JTextArea messageArea;
    private JButton sendButton;

    public ChatApp() {
     
        JFrame frame = new JFrame("Chat App");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
       
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
       
        this.messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        frame.add(scrollPane, constraints);
        
      
        this.messageField = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        frame.add(messageField, constraints);
        
      
        this.sendButton = new JButton("Send");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        frame.add(sendButton, constraints);
      
        
        frame.pack();
        frame.setVisible(true);
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public void setMessageField(JTextField messageField) {
        this.messageField = messageField;
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public void setMessageArea(JTextArea messageArea) {
        this.messageArea = messageArea;
    }
    
    public JButton getSendButton() {
        return sendButton;
    }

    public void setSendButton(JButton sendButton) {
        this.sendButton = sendButton;
    }
    public static void main(String[] args) {
        new ChatApp();
    }

    
}