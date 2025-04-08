package uth.edu.homestay_campingbooking.repositories;

import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.BookedService;

import java.time.LocalDate;
import java.util.List;

public interface IBookedServiceRepository {
    List<BookedService> info(Long userId);
    List<BookedService> findAllBookedService();
    void saveBookedService(Long serviceId, BookedService bookedService);
    void updateBookedService(Long userId, BookedService bookedService);
    void deleteBookedService(Long id);
}
