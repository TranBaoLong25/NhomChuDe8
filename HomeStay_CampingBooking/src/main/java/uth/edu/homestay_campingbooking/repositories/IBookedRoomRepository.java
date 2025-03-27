package uth.edu.homestay_campingbooking.repositories;

import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.HomeStay;

import java.time.LocalDate;
import java.util.List;

public interface IBookedRoomRepository {
    List<BookedRoom> info(String phone);
    List<BookedRoom> findAllBookedRoom();
    Boolean checkByDate(LocalDate check_in, LocalDate check_out);
    void saveBookedRoom(Long homeStayId, BookedRoom bookedRoom);
    void updateBookedRoom(String phone, BookedRoom bookedRoom);
    void deleteBookedRoom(String phone);
    List<BookedRoom> info2(Long userId);
    BookedRoom findBooked(String phone);
}
