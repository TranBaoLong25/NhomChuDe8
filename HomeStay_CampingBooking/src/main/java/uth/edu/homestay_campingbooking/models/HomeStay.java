package uth.edu.homestay_campingbooking.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HomeStay")
public class HomeStay {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Min(value = 1, message = "PRICE_INVALID")
    private double roomPrice;
    private boolean booked = false;
    @ElementCollection
    private List<String> imageUrls = new ArrayList<>();
    public HomeStay() {}
    public HomeStay(Long id, RoomType roomType, double roomPrice, boolean booked, List<String> imageUrls) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.booked = booked;
        this.imageUrls = imageUrls;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public String toString() {
        return "id: "+id+" roomType: "+roomType+" roomPrice: "+roomPrice+" booked: "+booked;
    }
}
