package uth.edu.homestay_campingbooking.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookedService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate time;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public BookedService() {
    }

    public BookedService(Long id, LocalDate time, Service service, User user) {
        this.id = id;
        this.time = time;
        this.service = service;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "id=" + id + ", time=" + time + ", service=" + service + ", user=" + user;
    }
}
