package uth.edu.homestay_campingbooking.models;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "HomeStay")
public class HomeStay {
    @Id
    private Long id;
    private String roomType;
    private double roomPrice;
    private boolean booked = false;
    @Lob
    private Blob image;
    public HomeStay() {}
    public HomeStay(String roomType, double roomPrice, boolean booked) {
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.booked = booked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "id: "+id+" roomType: "+roomType+" roomPrice: "+roomPrice+" booked: "+booked;
    }
}
