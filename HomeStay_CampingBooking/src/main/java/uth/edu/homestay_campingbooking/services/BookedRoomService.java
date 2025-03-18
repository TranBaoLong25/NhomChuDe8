package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.repositories.BookedRoomRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookedRoomService implements IBookedRoomService {
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
    public List<BookedRoom> findAllBookedRoom() {
        return bookedRoomRepository.findAllBookedRoom();
    }

    @Override
    public Boolean checkByDate(LocalDate check_in, LocalDate check_out) {
        return bookedRoomRepository.checkByDate(check_in, check_out);
    }
    @Override
    public void saveBookedRoom(BookedRoom bookedRoom) {
        bookedRoomRepository.saveBookedRoom(bookedRoom);
    }
    @Override
    public void deleteBookedRoom(String phone) {
        bookedRoomRepository.deleteBookedRoom(phone);
    }
    @Override
    public void updateBookedRoom(String phone, BookedRoom bookedRoom) {
        bookedRoomRepository.updateBookedRoom(phone, bookedRoom);
    }
}
