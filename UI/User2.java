package UI;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import Lib.Room;

public class User2 extends JFrame implements ActionListener {
    Container cp ;
    JLabel name , id, dormLabel, dormSelectorLabel  ;
    JTextField t1 ,t2 ;
    JButton b1 ;
    JComboBox<String> dormCombo, dormSelectorCombo;
    JPanel imagePanel;
    private String selectedDorm;

    public User2(){
        this.selectedDorm = "Dorm A"; // ค่าเริ่มต้น
        Initial();
        setComponent();
        Finally();
    }
    public void Initial(){
    cp = this.getContentPane();
    cp.setLayout(null);
    
    
    // สร้าง ComboBox สำหรับเลือกหอพัก
    dormSelectorCombo = new JComboBox<>();
    dormSelectorCombo.addItem("Dorm A");
    dormSelectorCombo.addItem("Dorm B");
    dormSelectorCombo.addItem("Dorm C");
    dormSelectorCombo.addItem("Dorm D");
    dormSelectorCombo.setSelectedItem(selectedDorm);
    
    // ดูห้องทั้งหมด เรียกจาก Room
    dormCombo = loadRoomsFromRoomClass(selectedDorm); 
}
    
    // ตรวจสอบสถานะห้องที่เลือก  เรียกใช้จาก Room
    private void checkRoomAvailability() {
        String selectedRoom = (String) dormCombo.getSelectedItem();
        if (selectedRoom != null) {
            boolean isAvailable = Room.isRoomAvailableStatic(selectedRoom);
            
            b1.setEnabled(isAvailable);
            
            if (!isAvailable) {
                b1.setBackground(Color.GRAY);
                b1.setForeground(Color.WHITE);
            } else {
                b1.setBackground(Color.pink);
                b1.setForeground(Color.black);
            }
        }
    }

    public void setComponent(){


        dormSelectorLabel = new JLabel("Select Dorm");
        dormSelectorLabel.setBounds(140,20,100,30);
        dormSelectorLabel.setFont(new Font("Bold",Font.BOLD,16));
        dormSelectorLabel.setForeground(Color.black);    
        cp.add(dormSelectorLabel);


        dormSelectorCombo.setBounds(100,50,200,35); 
        dormSelectorCombo.setPreferredSize(new Dimension(200, 35)); 
        dormSelectorCombo.setFont(new Font("Arial", Font.BOLD, 14));        
        cp.add(dormSelectorCombo);


        dormLabel = new JLabel("Room List");
        dormLabel.setBounds(140,100,100,30);
        dormLabel.setFont(new Font("Bold",Font.BOLD,16));
        dormLabel.setForeground(Color.black);    
        cp.add(dormLabel);


        dormCombo.setBounds(100,140,200,35); 
        dormCombo.setPreferredSize(new Dimension(200, 35)); 
        dormCombo.setFont(new Font("Arial", Font.BOLD, 14));        
        cp.add(dormCombo);

        b1 = new JButton("reserve"); 
        b1.setBounds(150,200,100,30);
        b1.setFont(new Font("Arial",Font.BOLD,14));
        b1.setForeground(Color.black);
        b1.setBackground(Color.pink );
        b1.addActionListener(this);
        cp.add(b1);

        dormCombo.addActionListener(this);
        dormSelectorCombo.addActionListener(this);
        

        checkRoomAvailability(); 

    }

    public void Finally(){
        this.setTitle("User");
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // เปลี่ยนหอพักที่เลือก  
    private void changeDorm() {
        String newDorm = (String) dormSelectorCombo.getSelectedItem();
        if (newDorm != null && !newDorm.equals(selectedDorm)) {
            selectedDorm = newDorm;
            
            // เปลี่ยนตรง Room ให้ตรงกับ Dorm ที่เลือก  
            dormCombo.removeAllItems();
            List<String> rooms = Room.getRoomsByDormStatic(selectedDorm);
            for (String room : rooms) {
                dormCombo.addItem(room);
            }
            
            checkRoomAvailability();
        }
    }
    
    // เรียกใช้ Room 
    private JComboBox<String> loadRoomsFromRoomClass(String dormName) {
        JComboBox<String> tmp = new JComboBox<>();
        List<String> rooms = Room.getRoomsByDormStatic(dormName);
        
        for (String room : rooms) {
            tmp.addItem(room);
        }
        return tmp;
    }
    
    // ระบบจองห้อง 
    private void reserveRoom() {
        String selectedRoom = (String) dormCombo.getSelectedItem();
        if (selectedRoom != null && Room.isRoomAvailableStatic(selectedRoom)) {
            boolean success = Room.reserveRoomStatic(selectedDorm, selectedRoom);
            
            if (success) {
                // รีเฟรช ComboBox จากไฟล์ที่ถูกต้อง 
                dormCombo.removeAllItems();
                List<String> rooms = Room.getRoomsByDormStatic(selectedDorm);
                for (String room : rooms) {
                    dormCombo.addItem(room);
                }
                
                // เลือกห้องที่เพิ่งจอง
                dormCombo.setSelectedItem(selectedRoom + " Currently booking");
                
                // ตรวจสอบสถานะห้องอีกครั้ง 
                checkRoomAvailability();
                
                JOptionPane.showMessageDialog(this, "Booking " + selectedRoom + " is currently booking. Waiting to contact you.", "Currently booking", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // กันห้องที่ไม่สามารถจองได้
            String selectedRoom = (String) dormCombo.getSelectedItem();
            if (selectedRoom != null) {
                if (Room.isRoomAvailableStatic(selectedRoom)) {
                    reserveRoom();
                    new Userinfo();
                } else {
                    JOptionPane.showMessageDialog(this, "This room is not available for booking!", 
                                                "Booking Not Available", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (e.getSource() == dormCombo) {
            // เปลี่ยนห้องที่เลือก
            checkRoomAvailability();
        } else if (e.getSource() == dormSelectorCombo) {
            // เปลี่ยนหอพักที่เลือก
            changeDorm();
        }
    }
}
