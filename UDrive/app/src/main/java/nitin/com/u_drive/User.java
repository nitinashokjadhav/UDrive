package nitin.com.u_drive;

public class User {
    private String name,phoneNo,email,type;
    public User()
    {}

    public User(String name,String phoneNo,String email,String type)
    {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email  =   email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
