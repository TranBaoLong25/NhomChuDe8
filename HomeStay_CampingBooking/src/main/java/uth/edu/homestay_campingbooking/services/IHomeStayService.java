package uth.edu.homestay_campingbooking.services;

import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;

import java.util.List;

public interface IHomeStayService {
    void createHomeStay(HomeStay hs);
    HomeStay findHomeStay(Long id);
    void updateHomeStay(Long id, HomeStay hs);
    void deleteHomeStay(Long id);
    List<HomeStay> findAllHomeStays();
    List<HomeStay> findByType(RoomType type);
    List<HomeStay> findByLocation(Location location);
    List<HomeStay> findByPrice(double minPrice, double maxPrice);
}
