package uth.edu.homestay_campingbooking.services;

import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.RoomType;

import java.util.List;

public interface IHomeStayService {
    void createHomeStay(HomeStay hs);
    HomeStay findHomeStay(long id);
    void updateHomeStay(Long id, HomeStay hs);
    void deleteHomeStay(long id);
    List<HomeStay> findAllHomeStays();
    List<HomeStay> findByType(RoomType type);
    List<HomeStay> findByPrice(double minPrice, double maxPrice);
}
