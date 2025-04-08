package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.models.BookedService;
import uth.edu.homestay_campingbooking.repositories.BookedServiceRepository;

import java.util.List;

@Service
public class BookedServiceService implements IBookedServiceService {
    @Autowired
    private BookedServiceRepository bookedServiceRepository;
    @Override
    public List<BookedService> info(Long userId) {
        return bookedServiceRepository.info(userId);
    }

    @Override
    public List<BookedService> findAllBookedService() {
        return bookedServiceRepository.findAllBookedService();
    }

    @Override
    public void saveBookedService(Long serviceId, BookedService bookedService) {
        bookedServiceRepository.saveBookedService(serviceId, bookedService);
    }

    @Override
    public void updateBookedService(Long userId, BookedService bookedService) {
        bookedServiceRepository.updateBookedService(userId, bookedService);
    }

    @Override
    public void deleteBookedService(Long id) {
        bookedServiceRepository.deleteBookedService(id);
    }
}
