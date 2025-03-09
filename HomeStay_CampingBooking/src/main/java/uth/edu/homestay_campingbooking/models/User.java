package uth.edu.homestay_campingbooking.models;
import jakarta.persistence.*;
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User() {}
    public long getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public void setId(long id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}

}
