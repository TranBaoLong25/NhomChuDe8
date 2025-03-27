package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.repositories.BookedRoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class BookedRoomService implements IBookedRoomService {
    @Autowired
    private HomeStayService homeStayService;
    @Autowired
    private UserService userService;
    private BookedRoomRepository bookedRoomRepository;
    @Autowired
    public BookedRoomService(BookedRoomRepository bookedRoomRepository) {
        this.bookedRoomRepository = bookedRoomRepository;
    }
    @Override
    public List<BookedRoom> info(String phone) {
        return bookedRoomRepository.info(phone);
    }

    @Override
    public List<BookedRoom> info2(Long userId) {
        return bookedRoomRepository.info2(userId);
    }

    @Override
    public List<BookedRoom> findAllBookedRoom() {
        return bookedRoomRepository.findAllBookedRoom();
    }

    @Override
    public Boolean checkByDate(LocalDate check_in, LocalDate check_out) {
        return bookedRoomRepository.checkByDate(check_in, check_out);
    }
    @Override
    public void saveBookedRoom(Long homeStayId, BookedRoom bookedRoom) {
        bookedRoomRepository.saveBookedRoom(homeStayId, bookedRoom);
    }
    @Override
    public void deleteBookedRoom(String phone) {
        bookedRoomRepository.deleteBookedRoom(phone);
    }

    @Override
    public BookedRoom findBooked(String phone) {
        return bookedRoomRepository.findBooked(phone);
    }

    @Override
    public void updateBookedRoom(String phone, BookedRoom bookedRoom) {
        bookedRoomRepository.updateBookedRoom(phone, bookedRoom);
    }
}
