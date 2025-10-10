package UI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    Container cp ;
    JLabel register , username , password;
    JTextField t1;
    JPasswordField t2;
    JButton b1 , b2 ;

    public Login(){
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
        register = new JLabel("Log In");
        username = new JLabel("Username or email");
        t1 = new JTextField();
        password = new JLabel("Password");
        t2 = new JPasswordField();
        
        b1 = new JButton("Submit");
        b2 = new JButton("back");

        // กำหนดขนาดและตำแหน่ง
        register.setBounds(140,20,150,40);
        register.setFont(new Font("Arial",Font.BOLD,30));
        //Username
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
        this.setTitle("Log In"); // Name
        this.setSize(400,400); // Size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //can close
        this.setVisible(true); // see output
        this.setLocationRelativeTo(null);  // แสดงตรงกลาง
        this.setResizable(false); // ขยายไม่ได้
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){    //กดsummit
            String usernameText = t1.getText().trim();
            String passwordText = new String(t2.getPassword());

            if(usernameText.isEmpty() || passwordText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter username and password.", "Validation", JOptionPane.WARNING_MESSAGE); //pop up
                return;
            }

            boolean ok = authenticateUser(usernameText, passwordText); 
            if(ok == true){ 
                JOptionPane.showMessageDialog(this, "Login successful."); //pop up
                dispose();
                new User2();
            }else{
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE); //pop up
            }
        } else if(e.getSource() == b2){
            dispose(); //กดback
            new select();
        }
    }

    public boolean authenticateUser(String username, String password){
        Path csvPath = Paths.get("File", "User.csv"); //อ่านไฟล์จาก User.csv
        if(!Files.exists(csvPath)){ //ถ้าไฟล์ไม่มี
            return false; //return false
        }
        try{ //อ่านไฟล์จาก User.csv
            for(String line : Files.readAllLines(csvPath, StandardCharsets.UTF_8)){
                line = line.trim(); //ลบช่องว่าง
                if(line == null) continue; //ถ้าบรรทัดเป็น null ข้าม
                if(line.isEmpty()) continue; //ถ้าบรรทัดว่าง ข้าม
                String[] parts = line.split(",", 3); //แยกข้อมูลในบรรทัดเดียวเป็น 3 ชิ้นเก็บไว้ใน parts เป็น array
                if(parts.length < 2) continue;   //ถ้าข้อมูลใน parts น้อยกว่า 2 ชิ้น ข้าม
                String u = parts[0].trim(); //ลบช่องว่าง
                String p = parts[1].trim(); //ลบช่องว่าง
                if(u.equals(username) && p.equals(password)){
                    return true; //ถ้า username และ password ตรงกับข้อมูลในไฟล์ กลับ true
                }
            }
        }catch(IOException ex){
            ex.printStackTrace(); //ถ้าไม่สามารถอ่านไฟล์ได้ กลับ false
            return false; //return false
        }
        return false; //ถ้าไม่สามารถอ่านไฟล์ได้ กลับ false
    }
    }
    

