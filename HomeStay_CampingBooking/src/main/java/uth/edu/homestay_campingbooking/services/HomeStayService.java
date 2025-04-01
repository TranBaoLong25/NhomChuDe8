package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.repositories.HomeStayRepository;

import java.util.List;

@Service
public class HomeStayService {

    @Autowired
    HomeStayRepository homeStayRepository;

    public String insertHomeStay(RoomType roomType, Location location, double roomPrice, boolean booked, List<MultipartFile> images) {
        return homeStayRepository.insertHomeStay(roomType, location, roomPrice, booked, images);
    }

    public HomeStay findHomeStay(Long id) {
        return homeStayRepository.findHomeStay(id);
    }

    public String updateHomeStay(Long id, RoomType roomType, Location location, double roomPrice, boolean booked, List<MultipartFile> images) {
        return homeStayRepository.updateHomeStay(id, roomType, location, roomPrice, booked, images);
    }

    public void deleteHomeStay(Long id) {
        homeStayRepository.deleteHomeStay(id);
    }

    public List<HomeStay> findHomeStayByPrice(double min, double max) {
        return homeStayRepository.findByPrice(min, max);
    }

    public List<HomeStay> findHomeStayByType(RoomType type) {
        return homeStayRepository.findByType(type);
    }

    public List<HomeStay> findHomeStayByLocation(Location location) {
        return homeStayRepository.findByLocation(location);
    }

    public List<HomeStay> findAllHomeStays() {
        return homeStayRepository.findAllHomeStays();
    }
}
