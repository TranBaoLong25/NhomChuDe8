package uth.edu.homestay_campingbooking.controllers.ManagerController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.services.BookedRoomService;

import org.springframework.ui.Model;
import uth.edu.homestay_campingbooking.services.HomeStayService;
import uth.edu.homestay_campingbooking.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/managerbookedroom")
public class managerBookedRoomController {
    @Autowired
    private BookedRoomService bookedRoomService;
    @Autowired
    private HomeStayService homeStayService;
    @Autowired
    private UserService userService;
    // Hiển thị danh sách các phòng đã đặt
    @GetMapping
    public String getAllBookedRooms(Model model) {
        List<BookedRoom> bookedRooms = bookedRoomService.findAllBookedRoom();
        model.addAttribute("bookedRooms", bookedRooms);
        model.addAttribute("newBookedRoom", new BookedRoom());
        return "admin/managerbookedroom";
    }
    @PostMapping("/add")
    public String addBookedRoom(@ModelAttribute("newBookedRoom") @Valid BookedRoom bookedRoom,
                                @RequestParam Long homeStayId,
                                @RequestParam Long userId) {
        bookedRoom.setHomeStay(homeStayService.findHomeStay(homeStayId));
        bookedRoom.setUser(userService.findById(userId));
        bookedRoomService.saveBookedRoom(homeStayId, bookedRoom);
        return "redirect:/managerbookedroom";
    }

    @GetMapping("/delete/{phone}")
    public String deleteBookedRoom(@PathVariable String phone) {
        bookedRoomService.deleteBookedRoom(phone);
        return "redirect:/managerbookedroom";
    }

    // Lấy thông tin phòng đã đặt cần chỉnh sửa
    @GetMapping("/edit/{phone}")
    public String editBookedRoom(@PathVariable String phone, Model model) {
        BookedRoom bookedRoom = bookedRoomService.findBooked(phone);
        model.addAttribute("editBookedRoom", bookedRoom);
        model.addAttribute("bookedRooms", bookedRoomService.findAllBookedRoom());
        return "admin/managerbookedroom";
    }
    @PostMapping("/update/{phone}")
    public String updateBookedRoom(@PathVariable String phone,
                                   @ModelAttribute("editBookedRoom") @Valid BookedRoom bookedRoom,
                                   @RequestParam Long homeStayId,
                                   @RequestParam Long userId) {
        bookedRoom.setHomeStay(homeStayService.findHomeStay(homeStayId));
        bookedRoom.setUser(userService.findById(userId));
        bookedRoomService.updateBookedRoom(phone, bookedRoom);
        return "redirect:/managerbookedroom";
    }

}
