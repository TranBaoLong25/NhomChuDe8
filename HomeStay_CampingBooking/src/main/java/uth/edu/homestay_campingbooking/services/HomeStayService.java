package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.repositories.IHomeStayRepository;
import uth.edu.homestay_campingbooking.repositories.BookedRoomRepository;

import java.util.List;

@Service
public class HomeStayService implements IHomeStayService {
    private IHomeStayRepository homeStayRepository;
    private BookedRoomRepository bookedRoomRepository;

    @Autowired
    public HomeStayService(IHomeStayRepository homeStayRepository, BookedRoomRepository bookedRoomRepository) {
        this.homeStayRepository = homeStayRepository;
        this.bookedRoomRepository = bookedRoomRepository;
    }

    @Override
    public void createHomeStay(HomeStay hs) {
        homeStayRepository.createHomeStay(hs);
    }

    @Override
    public HomeStay findHomeStay(Long id) {
        return homeStayRepository.findHomeStay(id);
    }

    @Override
    public void updateHomeStay(Long id, HomeStay hs) {
        homeStayRepository.updateHomeStay(id, hs);
    }

    @Override
    public void deleteHomeStay(Long id) {
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
    public List<HomeStay> findByLocation(Location location) {
        return homeStayRepository.findByLocation(location);
    }

    @Override
    public List<HomeStay> findByPrice(double minPrice, double maxPrice) {
        return homeStayRepository.findByPrice(minPrice, maxPrice);
    }

    public void updateHomeStayBookingStatus() {
        List<HomeStay> homeStays = findAllHomeStays();
        List<BookedRoom> bookedRooms = bookedRoomRepository.findAllBookedRoom();

        for (HomeStay homeStay : homeStays) {
            boolean isBooked = bookedRooms.stream()
                    .anyMatch(bookedRoom -> bookedRoom.getHomeStay().getId().equals(homeStay.getId()));
            homeStay.setBooked(isBooked);
            homeStayRepository.updateHomeStay(homeStay.getId(), homeStay);
        }
    }
}