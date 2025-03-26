package uth.edu.homestay_campingbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.services.BookedRoomService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/booked")
public class BookedRoomController {
    @Autowired
    private BookedRoomService bookedRoomService;
    @GetMapping
    public List<BookedRoom> getAllBookedRooms() {
        return bookedRoomService.findAllBookedRoom();
    }
    @GetMapping("/info")
    public List<BookedRoom> getBookedRoomInfo(@RequestParam("phone") String phone) {
        return bookedRoomService.info(phone);
    }
    @GetMapping("/check")
    public Boolean checkBookedRoom(@RequestParam("check_in") LocalDate check_in,
                                   @RequestParam("check_out") LocalDate check_out) {
        return bookedRoomService.checkByDate(check_in, check_out);
    }
    @PostMapping("/insert/{homeStayId}")
    public void insertBookedRoom(@PathVariable Long homeStayId, @RequestBody BookedRoom bookedRoom) {
            bookedRoomService.saveBookedRoom(homeStayId, bookedRoom);
    }
    @PutMapping("/update/{phone}")
    public void updateBookedRoom(@PathVariable String phone, @RequestBody BookedRoom bookedRoom) {
        bookedRoomService.updateBookedRoom(phone, bookedRoom);
    }
    @DeleteMapping("/delete/{phone}")
    public void deleteBookedRoom(@PathVariable String phone) {
        bookedRoomService.deleteBookedRoom(phone);
    }
}
