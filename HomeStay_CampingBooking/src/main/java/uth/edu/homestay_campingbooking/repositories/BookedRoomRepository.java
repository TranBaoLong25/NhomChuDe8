package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.BookedRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class BookedRoomRepository implements IBookedRoomRepository {
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
    public void saveBookedRoom(BookedRoom bookedRoom) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        for (BookedRoom room : bookedRooms) {
            if (room.getGuestPhone().equals(bookedRoom.getGuestPhone())) {
                throw new AppException(ErrorCode.EXISTED);
            }
        }
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
    public void updateBookedRoom(String phone, BookedRoom bookedRoom) {
        List<BookedRoom> bookedRooms = em.createQuery("from BookedRoom", BookedRoom.class).getResultList();
        boolean found = false;
        for (BookedRoom room : bookedRooms) {
            if (room.getGuestPhone().equals(phone)) {
                room.setGuestName(bookedRoom.getGuestName());
                room.setGuestPhone(bookedRoom.getGuestPhone());
                room.setCheckInDate(bookedRoom.getCheckInDate());
                room.setCheckInDate(bookedRoom.getCheckInDate());
                room.setCheckOutDate(bookedRoom.getCheckOutDate());
                em.merge(room);
                found = true;
            }
        }
        if (!found) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
    }
}
