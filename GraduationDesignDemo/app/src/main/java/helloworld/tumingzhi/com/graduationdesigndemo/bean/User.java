package helloworld.tumingzhi.com.graduationdesigndemo.bean;

/**
 * Created by DELL on 2018/3/4.
 */

public class User {
    private int id;
    private String password;
    private String username;
    private int icon;

    public User(){

    }
    public User(int id, String password, String username, int icon) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", icon=" + icon +
                '}';
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
