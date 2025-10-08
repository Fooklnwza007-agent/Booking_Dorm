package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Admin extends JFrame implements ActionListener {
    Container cp ;
    JLabel register ,email ,username , password;
    JTextField t1, t3;
    JPasswordField t2;
    JButton b1 , b2 ;

    public Admin(){
        Initial(); 
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
        register = new JLabel("ADMIN");
        username = new JLabel("Admin_Username");
        t1 = new JTextField();
        password = new JLabel("Password");
        t2 = new JPasswordField();
        
        b1 = new JButton("Submit");
        b2 = new JButton("back");

        // กำหนดขนาดและตำแหน่ง
        register.setBounds(140,20,150,40);
        register.setFont(new Font("Arial",Font.BOLD,30));

        //Admin_Username
        username.setBounds(50,90,200,30);
        username.setFont(new Font("Arial",Font.BOLD,15));
        t1.setBounds(45,120,300,30);

        //Password
        password.setBounds(50,170,300,30);
        password.setFont(new Font("Arial",Font.BOLD,15));
        t2.setBounds(45,200,300,30);

        //Submit
        b1.setFont(new Font("Arial",Font.BOLD,15));
        b1.setBounds(205,275,140,40);
        b1.setBackground(Color.pink);

        // back
        b2.setFont(new Font("Arial",Font.BOLD,15));
        b2.setBounds(45,275,140,40);
        b2.setBackground(Color.pink);

        // add listeners กูลืมไปได้ไง
        b1.addActionListener(this);
        b2.addActionListener(this);


        //add Component
        cp.add(register); 
        cp.add(username); cp.add(t1);
        cp.add(password); cp.add(t2);
        cp.add(b1); cp.add(b2);

    }
    private void Finally() {
        this.setTitle("Admin"); // name
        this.setSize(400,400); // size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // can close 
        this.setVisible(true); // see output
        this.setLocationRelativeTo(null);  //แสดงตรงกลาง
        this.setResizable(false); // ขยายไม่ได้
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){    //summit
            dispose();
        } else if(e.getSource() == b2){
            dispose();
            new select();
        }
    }
    
}
