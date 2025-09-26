package UI;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

public class User1 extends JFrame implements ActionListener {
    Container cp ;
    JLabel name , id, dormLabel ;
    JTextField t1 ,t2 ;
    JButton b1 ;
    JComboBox<String> dormCombo;
    JPanel imagePanel;

    public User1(){
        Initial();
        setComponent();
        Finally();
    }
    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
        dormCombo = province_to_combobox(); 
    }

    public void setComponent(){

        // Label หัวข้อ   

        dormLabel = new JLabel("search dorm");
        dormLabel.setBounds(140,50,100,30);
        dormLabel.setFont(new Font("Bold",Font.BOLD,16));
        dormLabel.setForeground(Color.black);    
        cp.add(dormLabel);

        // ComboBox ที่เลือกหอพัก   

        dormCombo.setBounds(100,100,200,35); 
        dormCombo.setPreferredSize(new Dimension(200, 35)); 
        dormCombo.setFont(new Font("Arial", Font.BOLD, 14));        
        cp.add(dormCombo);

        // Button  
        b1 = new JButton("Confirm"); 
        b1.setBounds(150,160,100,30);
        b1.setFont(new Font("Arial",Font.BOLD,14));
        b1.setForeground(Color.black);
        b1.setBackground(Color.pink );
        cp.add(b1); 

        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(10, 10, 120, 80);
                g.setColor(Color.BLACK);
                g.drawRect(10, 10, 120, 80);
                g.drawString("wait for picture", 30, 55);
            }
        };
        imagePanel.setBounds(120, 260, 140, 100);
        cp.add(imagePanel);
    }

    // JFrame หน้าต่าง
    public void Finally(){
        this.setTitle("User");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static JComboBox<String> province_to_combobox(){
        JComboBox<String> tmp = new JComboBox<>();
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            f = new File("./Dormlist.csv"); 
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                if (!s.trim().isEmpty()) {
                    tmp.addItem(s.trim());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            if (br != null) br.close();
            if (fr != null) fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tmp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
