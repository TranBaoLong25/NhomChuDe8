package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.repositories.HomeStayRepository;
import uth.edu.homestay_campingbooking.repositories.IHomeStayRepository;

import java.util.List;
@Service
public class HomeStayService implements IHomeStayService {
    private IHomeStayRepository homeStayRepository;
    @Autowired
    public HomeStayService(IHomeStayRepository homeStayRepository) {
        this.homeStayRepository = homeStayRepository;
    }
    @Override
    public void createHomeStay(HomeStay hs) {
        homeStayRepository.createHomeStay(hs);
    }

    @Override
    public HomeStay findHomeStay(long id) {
        return homeStayRepository.findHomeStay(id);
    }

    @Override
    public void updateHomeStay(Long id, HomeStay hs) {
        homeStayRepository.updateHomeStay(id, hs);
    }

    @Override
    public void deleteHomeStay(long id) {
        homeStayRepository.deleteHomeStay(id);
    }

    @Override
    public List<HomeStay> findAllHomeStays() {
        return homeStayRepository.findAllHomeStays();
    }

    @Override
    public List<HomeStay> findByType(RoomType type) {
        return homeStayRepository.findByType(type);
    }

    @Override
    public List<HomeStay> findByPrice(double minPrice, double maxPrice) {
        return homeStayRepository.findByPrice(minPrice, maxPrice);
    }
}
