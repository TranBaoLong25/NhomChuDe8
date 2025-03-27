package uth.edu.homestay_campingbooking.services;

import uth.edu.homestay_campingbooking.models.BookedRoom;

import java.time.LocalDate;
import java.util.List;

public interface IBookedRoomService {

    List<BookedRoom> info(String phone);
    List<BookedRoom> info2(Long userId);
    List<BookedRoom> findAllBookedRoom();
    Boolean checkByDate(LocalDate check_in, LocalDate check_out);
    void saveBookedRoom(Long homeStayId, BookedRoom bookedRoom);
    void updateBookedRoom(String phone, BookedRoom bookedRoom);
    void deleteBookedRoom(String phone);
    BookedRoom findBooked(String phone);
}
