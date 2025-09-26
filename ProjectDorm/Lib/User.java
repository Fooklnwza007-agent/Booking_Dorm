package Lib;

public class User {


    private final String username;
    private final String password;

    

    public User(String username,String password){
        this.password = password;   
        this.username = username;
    } 

     public String toString(){
        return username + "," + password;
    }

    public static User fromString(String User){
        String[] acc_info = User.split(",");
        return new User(acc_info[0],acc_info[1]);
    }

    public void checkAvailableRooms(){

    }

    public void makeBooking(Room r,int u){

    }
    
}
