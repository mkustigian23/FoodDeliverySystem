import java.util.Scanner;

public class Login {
    private int phoneNum;
    private String email;
    private String username;
    private String password;
    private String accType;

    public void loginInfo(){
        System.out.println("Insert User Info");
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    public int getPhoneNum() {
        return phoneNum;
    }
    public void setAccType(String accType) {
        this.accType = accType;
    }
    public String getAccType() {
        return accType;
    }
}
