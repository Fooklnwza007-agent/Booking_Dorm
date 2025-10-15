package Lib;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<String> roomData;
    private String csvFilePath;
    
    private static Room instance = null;
    
    public Room() {
        roomData = new ArrayList<>();
        csvFilePath = "./FIle/Roomlist.csv";
        loadRoomData();
    }
    
    public Room(String filePath) {
        roomData = new ArrayList<>();
        csvFilePath = filePath;
        loadRoomData();
    }
    
    public static Room getInstance() {
        if (instance == null) {
            instance = new Room();
        }
        return instance;
    }
    

    public static List<String> getRoomsByDormStatic(String dormName) {
        return Dorm.getRoomsByDorm(dormName);
    }
    
    public static boolean isRoomAvailableStatic(String selectedRoom) {
        return Dorm.isRoomAvailable(selectedRoom);
    }
    
    public static boolean reserveRoomStatic(String dormName, String selectedRoom) {
        return Dorm.reserveRoom(dormName, selectedRoom);
    }
    
    // โหลดข้อมูลห้องจากไฟล์ CSV
    public void loadRoomData() {
        try {
            File f = new File(csvFilePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;
            roomData.clear();
            while ((s = br.readLine()) != null) {
                if (!s.trim().isEmpty()) {
                    roomData.add(s.trim());
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error loading room data: " + e);
        }
    }
    
    // อัปเดตไฟล์ CSV
    public void updateRoomData() {
        try {
            File f = new File(csvFilePath);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (String room : roomData) {
                bw.write(room);
                bw.newLine();
            }
            
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error updating room data: " + e);
        }
    }
    
    // ตรวจสอบสถานะห้องที่เลือก
    public boolean isRoomAvailable(String selectedRoom) {
        return Dorm.isRoomAvailable(selectedRoom);
    }
    public List<String> getRoomsByDorm(String dormName) {
        return Dorm.getRoomsByDorm(dormName);
    }
    // จองห้อง
    public boolean reserveRoom(String dormName, String selectedRoom) {
        return Dorm.reserveRoom(dormName, selectedRoom);
    }
    // Getter methods
    public List<String> getRoomData() {
        return roomData;
    }
    
    public String getCsvFilePath() {
        return csvFilePath;
    }
    
    // Setter methods
    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        loadRoomData();
    }
    
} 