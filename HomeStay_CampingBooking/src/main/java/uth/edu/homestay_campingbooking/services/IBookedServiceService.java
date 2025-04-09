package uth.edu.homestay_campingbooking.services;

import uth.edu.homestay_campingbooking.models.BookedService;

import java.util.List;

public interface IBookedServiceService {
    BookedService getBookedServiceById(Long id);
    List<BookedService> info(Long userId);
    List<BookedService> findAllBookedService();
    void saveBookedService(Long serviceId, BookedService bookedService);
    void updateBookedService(Long userId, BookedService bookedService);
    void deleteBookedService(Long id);
}
