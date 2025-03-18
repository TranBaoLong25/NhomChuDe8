package uth.edu.homestay_campingbooking.services;

import uth.edu.homestay_campingbooking.models.BookedRoom;

import java.time.LocalDate;
import java.util.List;

public interface IBookedRoomService {

    List<BookedRoom> info(String phone);
    List<BookedRoom> findAllBookedRoom();
    Boolean checkByDate(LocalDate check_in, LocalDate check_out);
    void saveBookedRoom(BookedRoom bookedRoom);
    void updateBookedRoom(String phone, BookedRoom bookedRoom);
    void deleteBookedRoom(String phone);
}
