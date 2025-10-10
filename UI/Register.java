package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Register extends JFrame implements ActionListener {
    Container cp ;
    JLabel register ,email, username , password1, password2 ;
    JTextField t1, t4;
    JPasswordField t2, t3 ;
    JButton b1 , b2 ;

    public Register(){
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
        register = new JLabel("Register");
        email = new JLabel("email (for contact)");
        t4 = new JTextField();
        username = new JLabel("Username");
        t1 = new JTextField();
        password1 = new JLabel("Password");
        t2 = new JPasswordField();
        password2 = new JLabel("Confirm Password");
        t3 = new JPasswordField();
        b1 = new JButton("Submit");
        b2 = new JButton("Back");

        // กำหนดขนาดและตำแหน่ง
        register.setBounds(140,20,150,40);
        register.setFont(new Font("Arial",Font.BOLD,30));

        //email
        email.setBounds(50, 90, 200, 30);
        email.setFont(new Font(null,Font.BOLD,15));
        t4.setBounds(45, 120, 300, 30);

        // Username
        username.setBounds(50,170,200,30);
        username.setFont(new Font("Arial",Font.BOLD,15));
        t1.setBounds(45,200,300,30);

        //Password
        password1.setBounds(50,250,300,30);
        password1.setFont(new Font(null,Font.BOLD,15));
        t2.setBounds(45,280,300,30);

        //comfirem password
        password2.setBounds(50,330,200,30);
        password2.setFont(new Font(null,Font.BOLD,15));
        t3.setBounds(45,360,300,30);

        //Submit
        b1.setFont(new Font(null,Font.BOLD,15));
        b1.setBounds(203,430,140,40);
        b1.setBackground(Color.pink);

        //Back
        b2.setFont(new Font(null,Font.BOLD,15));
        b2.setBounds(43,430,140,40);
        b2.setBackground(Color.pink);

        b1.addActionListener(this);
        b2.addActionListener(this);

        //add Component
        cp.add(register); 
        cp.add(email); cp.add(t4);
        cp.add(username); cp.add(t1);
        cp.add(password1); cp.add(t2);
        cp.add(password2); cp.add(t3);
        cp.add(b1); cp.add(b2);
    }
    private void Finally() {
        this.setTitle("Register"); // name
        this.setSize(400,600); // size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // can close
        this.setVisible(true); // can output
        this.setLocationRelativeTo(null);  // แสดงตรงกลาง
        this.setResizable(false); // ขยายไม่ได้
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){    //summit
            String emailText = t4.getText().trim();
            String usernameText = t1.getText().trim();
            String passwordText = new String(t2.getPassword());
            String confirmPasswordText = new String(t3.getPassword());

            if(usernameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(passwordText.length() < 8){
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(!passwordText.equals(confirmPasswordText)){
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean saved = saveUserToCsv(usernameText, passwordText, emailText);
            if(saved){
                JOptionPane.showMessageDialog(this, "Registration successful.");
                dispose();
                new Login();
            }else{
                JOptionPane.showMessageDialog(this, "Failed to save user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource() == b2){
            dispose();
            new select();
        }
    }
    
    private boolean saveUserToCsv(String username, String password, String email){
        // Save to UI/User.csv, append a new line: username,password,email
        try{
            Path csvPath = Paths.get("File", "User.csv");
            if(csvPath.getParent() != null){
                Files.createDirectories(csvPath.getParent());
            }
            String record = username + "," + password + "," + email + System.lineSeparator();
            Files.write(csvPath,
                record.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
    }
    

