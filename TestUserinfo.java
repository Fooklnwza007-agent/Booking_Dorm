/**
 * ตัวอย่างการใช้งาน Userinfo form
 */
public class TestUserinfo {
    public static void main(String[] args) {
        // สร้าง Userinfo form พร้อมกับห้อง A121
        GUI.Userinfo userForm = new GUI.Userinfo("A121");
        userForm.setVisible(true);
        
        // หรือสามารถตั้งค่าห้องภายหลังได้
        // userForm.setSelectedRoom("B122");
    }
}