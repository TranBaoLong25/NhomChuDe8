package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class BookedServiceRepository implements IBookedServiceRepository {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    EntityManager em;
    @Override
    public List<BookedService> info(Long userId) {
        List<BookedService> bookedService = em.createQuery("from BookedService ", BookedService.class).getResultList();
        List<BookedService> filteredServices = bookedService.stream()
                .filter(s -> s.getUser() != null && s.getUser().getId().equals(userId))
                .collect(Collectors.toList());

        if (filteredServices.isEmpty()) {
            return null;
        }
        return filteredServices;
    }

    @Override
    public List<BookedService> findAllBookedService() {
        List<BookedService> bookedService = em.createQuery("from BookedService ", BookedService.class).getResultList();
        return bookedService.isEmpty() ? new ArrayList<>() : bookedService;
    }

    @Override
    public void saveBookedService(Long serviceId, BookedService bookedService) {
        if (bookedService.getTime().isBefore(LocalDate.now())) {
            throw new AppException(ErrorCode.INVALID_DAY);
        }
        Service service = serviceRepository.findById(serviceId);
        bookedService.setService(service);
        if (bookedService.getUser() != null) {
            User user = userRepository.findById(bookedService.getUser().getId());
            bookedService.setUser(user);
        }
        em.persist(bookedService);

    }

    @Override
    public void updateBookedService(Long userId, BookedService bookedService) {
        BookedService existingService = em.find(BookedService.class, bookedService.getId());
        if (bookedService.getTime().isBefore(LocalDate.now())) {
            throw new AppException(ErrorCode.INVALID_DAY);
        }
        existingService.setTime(bookedService.getTime());
        if (bookedService.getService() != null) {
            Service service = serviceRepository.findById(bookedService.getService().getId());
            existingService.setService(service);
        }
        if (bookedService.getUser() != null) {
            User user = userRepository.findById(bookedService.getUser().getId());
            existingService.setUser(user);
        }
        em.merge(existingService);

    }

    @Override
    public void deleteBookedService(Long id) {
        em.remove(em.find(BookedService.class, id));
    }
}
