package uth.edu.homestay_campingbooking.controllers.ManagerBookedRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.services.BookedRoomService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/manager/booked")
public class managerBookedRoomController {
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

    @GetMapping("/info2")
    public List<BookedRoom> getBookedRoomInfo2(@RequestParam("userId") Long userId) {
        return bookedRoomService.info2(userId);
    }

    @GetMapping("/check")
    public Boolean checkBookedRoom(@RequestParam("check_in") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate check_in,
                                   @RequestParam("check_out") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate check_out) {
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

    @GetMapping("/phone/{phone}")
    public BookedRoom findBookedRoomByPhone(@PathVariable String phone) {
        return bookedRoomService.findBooked(phone);
    }
}