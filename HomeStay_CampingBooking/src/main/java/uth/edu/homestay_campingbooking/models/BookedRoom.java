package uth.edu.homestay_campingbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guestName;
    private String guestPhone;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @ManyToOne
    @JoinColumn(name = "homestay_id", nullable = false)
    private HomeStay homeStay;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // Không bắt buộc có user
    private User user;

    public BookedRoom(String guestName, String guestPhone, LocalDate checkInDate, LocalDate checkOutDate, HomeStay homeStay) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.homeStay = homeStay;
    }
    public BookedRoom(String guestName, String guestPhone, LocalDate checkInDate, LocalDate checkOutDate, HomeStay homeStay, User user) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.homeStay = homeStay;
        this.user = user;
    }
    public BookedRoom() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public String getGuestPhone() {
        return guestPhone;
    }
    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public HomeStay getHomeStay() {
        return homeStay;
    }
    public void setHomeStay(HomeStay homeStay) {
        this.homeStay = homeStay;
    }
    public Long getHomeStayId() {
        return homeStay != null ? homeStay.getId() : null;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
