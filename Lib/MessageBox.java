package Lib;
import javax.swing.*;

public class MessageBox {
    private JTextArea textArea;

    public MessageBox() {
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
    }

    public void addMessage(String message) {
        textArea.append(message + "\n");
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}