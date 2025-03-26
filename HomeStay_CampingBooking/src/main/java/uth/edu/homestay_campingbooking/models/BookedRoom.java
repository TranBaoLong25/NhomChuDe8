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
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @ManyToOne
    @JoinColumn(name = "homestay_id", nullable = false)
    @JsonIgnore
    private HomeStay homeStay;

    public BookedRoom(String guestName, String guestPhone, RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate, HomeStay homeStay) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.homeStay = homeStay;
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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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
}
