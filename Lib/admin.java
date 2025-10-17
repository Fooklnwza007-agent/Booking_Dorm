package Lib;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class admin extends JPanel {
    private JButton sendButton;
    private MessageBox messageBox;

    public admin(MessageBox box) {
        this.messageBox = box;
        setLayout(new FlowLayout());

        sendButton = new JButton("ส่งข้อความถึงผู้ใช้");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageBox.addMessage("Admin: สวัสดีผู้ใช้!");
            }
        });

        add(sendButton);
    }
}
