package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class select extends JFrame implements ActionListener {
    Container cp ;
    JLabel Booking ;
    JButton b1 , b2, b3 ;

    public select(){
        Initial(); // ตั้งค่าเริ่มต้น
        setComponent(); // เพิ่ม Component
        Finally(); // ตั้งค่าขั้นสุดท้าย
    }

     private void Initial() {
        cp = this.getContentPane(); // สร้าง Container
        cp.setLayout(null); // ปิดการจัดการ Layout
        cp.setBackground(Color.white); // กำหนดสีพื้นหลัง
    }

    private void setComponent() {
        // เพิ่ม Component
        Booking = new JLabel("Booking Application");
        b1 = new JButton("Log-in (User)");
        b2 = new JButton("Register");
        b3 = new JButton("Admin");

        Booking.setBounds(45,30,500,40);
        Booking.setFont(new Font(null,Font.BOLD,30));

        //Admin
        b3.setFont(new Font(null,Font.BOLD,15));
        b3.setBounds(67,110,250,50);
        b3.setBackground(Color.pink);

        //LOgIN
        b1.setFont(new Font("Arial",Font.BOLD,15));
        b1.setBounds(67,185,250,50);
        b1.setBackground(Color.pink);

        //Register
        b2.setFont(new Font("Arial",Font.BOLD,15));
        b2.setBounds(67,255,250,50);
        b2.setBackground(Color.pink);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // add Component
        cp.add(Booking);
        cp.add(b3); cp.add(b1); cp.add(b2);
    }
    private void Finally() {
        this.setTitle("Booking Appication"); // name
        this.setSize(400,400); // size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // can close
        this.setVisible(true); // see output
        this.setLocationRelativeTo(null);  // แสดงตรงกลาง
        this.setResizable(false); // ขยายไม่ได้
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            dispose();
            new Login();
        }else if(e.getSource() == b2){
            dispose();
            new Register();
        }else if(e.getSource() == b3){
            dispose();
            new Admin();
        }
    }
    
}
