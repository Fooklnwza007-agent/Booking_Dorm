package Lib;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dorm {
    public static List<String> getRoomsByDorm(String dormName) {
        List<String> rooms = new ArrayList<>();
        try {
            String roomFileName = dormName.replace("Dorm ", "") + "Room.csv";
            String filePath = "./File/" + roomFileName;
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

    public static boolean isRoomAvailable(String selectedRoom) {
        if (selectedRoom != null) {
            String roomLower = selectedRoom.toLowerCase();
            boolean isNotAvailable = roomLower.contains("not available") ||
                                     roomLower.contains("currently booking") ||
                                     roomLower.contains("booking") ||
                                     roomLower.contains("unavailable");
            return !isNotAvailable;
        }
        return false;
    }

    public static boolean reserveRoom(String dormName, String selectedRoom) {
        if (selectedRoom != null && isRoomAvailable(selectedRoom)) {
            try {
                String roomFileName = dormName.replace("Dorm ", "") + "Room.csv";
                String filePath = "./File/" + roomFileName;
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
}
