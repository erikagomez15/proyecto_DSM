package sv.edu.udb.dentalapp.Models;

public class User {
    private String key;
    private String name;
    private String lastname;
    private String phone;
    private String user;
    private String email;
    private String password;
    private String birthday;
    private String gender;
    private String type;

    public User(String name, String lastname, String phone, String user, String email, String password, String birthday, String gender, String type) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.user = user;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.type = type;
    }

    public User() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
