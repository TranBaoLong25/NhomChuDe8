package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class BookedRoomRepository implements IBookedRoomRepository {
    @Autowired
    private HomeStayRepository homeStayRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    EntityManager em;
    @Override
    public List<BookedRoom> info(String phone) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        for (BookedRoom room : bookedRooms) {
            if (room.getGuestPhone().equals(phone)) {
                return bookedRooms;
            }
        }
        throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
    }
    @Override
    public List<BookedRoom> findAllBookedRoom() {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        return bookedRooms.isEmpty() ? new ArrayList<>() : bookedRooms;
    }

    @Override
    public Boolean checkByDate(LocalDate check_in, LocalDate check_out) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        for (BookedRoom room : bookedRooms) {
            if (!(room.getCheckInDate().isAfter(check_out) || room.getCheckOutDate().isBefore(check_in))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void saveBookedRoom(Long homeStayId, BookedRoom bookedRoom) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        for (BookedRoom room : bookedRooms) {
            if (!(room.getCheckInDate().isAfter(bookedRoom.getCheckOutDate()) || room.getCheckOutDate().isBefore(bookedRoom.getCheckInDate()))) {
                throw new AppException(ErrorCode.EXISTED);
            }
        }
        HomeStay homeStay = homeStayRepository.findHomeStay(homeStayId);
        for (BookedRoom room : bookedRooms) {
            if (homeStayId.equals(room.getHomeStayId())) {
                throw new AppException(ErrorCode.EXISTED);
            }
        }
        if (bookedRoom.getUser() != null) {
            User user = userRepository.findById(bookedRoom.getUser().getId());
            bookedRoom.setUser(user);
        }
        bookedRoom.setHomeStay(homeStay);
        em.persist(bookedRoom);
    }

    @Override
    public void deleteBookedRoom(String phone) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        boolean found = false;
        for (BookedRoom room : bookedRooms) {
            if (room.getGuestPhone().equals(phone)) {
                em.remove(room);
                found = true;
            }
        }
        if (!found) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
    }

    @Override
    public List<BookedRoom> info2(Long userId) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        List<BookedRoom> filteredRooms = bookedRooms.stream()
                .filter(room -> room.getUser() != null && room.getUser().getId().equals(userId))
                .collect(Collectors.toList());

        if (filteredRooms.isEmpty()) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return filteredRooms;
    }
    @Override
    public BookedRoom findBooked(String phone) {
        try {
            return em.createQuery("SELECT b FROM BookedRoom b WHERE b.guestPhone = :phone", BookedRoom.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
    }
    @Override
    public void updateBookedRoom(String phone, BookedRoom bookedRoom) {
        BookedRoom existingRoom = findBooked(phone);
        if (existingRoom == null) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        for (BookedRoom room : bookedRooms) {
            if (!(room.getCheckInDate().isAfter(bookedRoom.getCheckOutDate()) || room.getCheckOutDate().isBefore(bookedRoom.getCheckInDate()))) {
                throw new AppException(ErrorCode.EXISTED);
            }
        }
        for (BookedRoom room : bookedRooms) {
            if (room.getHomeStayId().equals(bookedRoom.getHomeStay())) {
                throw new AppException(ErrorCode.EXISTED);
            }
        }
        existingRoom.setGuestName(bookedRoom.getGuestName());
        existingRoom.setGuestPhone(bookedRoom.getGuestPhone());
        existingRoom.setCheckInDate(bookedRoom.getCheckInDate());
        existingRoom.setCheckOutDate(bookedRoom.getCheckOutDate());
        if (bookedRoom.getHomeStay() != null) {
            HomeStay homeStay = homeStayRepository.findHomeStay(bookedRoom.getHomeStay().getId());
            existingRoom.setHomeStay(homeStay);
        }
        if (bookedRoom.getUser() != null) {
            User user = userRepository.findById(bookedRoom.getUser().getId());
            existingRoom.setUser(user);
        }

        em.merge(existingRoom);
    }

}
