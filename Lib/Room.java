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
        csvFilePath = "./Roomlist.csv";
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
        List<String> rooms = new ArrayList<>();
        try {
            String roomFileName = dormName.replace("Dorm ", "") + "Room.csv";
            String filePath = "./" + roomFileName;
            
            File f = new File(filePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rooms.add(line.trim());
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error loading rooms for " + dormName + ": " + e);
        }
        return rooms;
    }
    
    public static boolean isRoomAvailableStatic(String selectedRoom) {
        if (selectedRoom != null) {
            String roomLower = selectedRoom.toLowerCase();
            
            // ตรวจสอบหลายรูปแบบของสถานะที่ไม่สามารถจองได้
            boolean isNotAvailable = roomLower.contains("not available") || 
                                   roomLower.contains("currently booking") ||
                                   roomLower.contains("booking") ||
                                   roomLower.contains("unavailable");
            
            return !isNotAvailable;
        }
        return false;
    }
    
    public static boolean reserveRoomStatic(String dormName, String selectedRoom) {
        if (selectedRoom != null && isRoomAvailableStatic(selectedRoom)) {
            try {

                String roomFileName = dormName.replace("Dorm ", "") + "Room.csv";
                String filePath = "./" + roomFileName;
                
                List<String> allLines = new ArrayList<>();
                File f = new File(filePath);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                
                while ((line = br.readLine()) != null) {
                    allLines.add(line.trim());
                }
                br.close();
                fr.close();
                
                // หาและอัปเดตห้องที่เลือก
                for (int i = 0; i < allLines.size(); i++) {
                    if (allLines.get(i).equals(selectedRoom)) {
                        allLines.set(i, selectedRoom + " Currently booking");
                        
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (String roomLine : allLines) {
                            bw.write(roomLine);
                            bw.newLine();
                        }
                        bw.close();
                        fw.close();
                        return true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reserving room: " + e);
            }
        }
        return false;
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
        if (selectedRoom != null) {
            String roomLower = selectedRoom.toLowerCase();
            
            // ตรวจสอบหลายรูปแบบของสถานะที่ไม่สามารถจองได้
            boolean isNotAvailable = roomLower.contains("not available") || 
                                   roomLower.contains("currently booking") ||
                                   roomLower.contains("booking") ||
                                   roomLower.contains("unavailable");
            
            return !isNotAvailable;
        }
        return false;
    }
    public List<String> getRoomsByDorm(String dormName) {
    List<String> rooms = new ArrayList<>();
    try {
        // แปลง "Dorm A" เป็น "ARoom.csv"
        String roomFileName = dormName.replace("Dorm ", "") + "Room.csv";
        String filePath = "./" + roomFileName;
        
        File f = new File(filePath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                rooms.add(line.trim());
            }
        }
        br.close();
        fr.close();
    } catch (Exception e) {
        System.out.println("Error loading rooms for " + dormName + ": " + e);
    }
    return rooms;
}
    // จองห้อง
    public boolean reserveRoom(String dormName, String selectedRoom) {
    if (selectedRoom != null && isRoomAvailable(selectedRoom)) {
        try {
            // แปลง "Dorm A" เป็น "ARoom.csv"
            String roomFileName = dormName.replace("Dorm ", "") + "Room.csv";
            String filePath = "./" + roomFileName;
            
            // อ่านไฟล์ทั้งหมด
            List<String> allLines = new ArrayList<>();
            File f = new File(filePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                allLines.add(line.trim());
            }
            br.close();
            fr.close();
            
            // หาและอัปเดตห้องที่เลือก
            for (int i = 0; i < allLines.size(); i++) {
                if (allLines.get(i).equals(selectedRoom)) {
                    allLines.set(i, selectedRoom + " Currently booking");
                    
                    // บันทึกลงไฟล์
                    FileWriter fw = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (String roomLine : allLines) {
                        bw.write(roomLine);
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reserving room: " + e);
        }
    }
    return false;
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