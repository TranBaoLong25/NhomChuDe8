package uth.edu.homestay_campingbooking.controllers.RoomController;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.*;
import uth.edu.homestay_campingbooking.services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private HomeStayService homeStayService;
    @Autowired
    private BookedRoomService bookedRoomService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookedServiceService bookedServiceService;

    @GetMapping("/service/{id}")
    public String showPaymentService(@PathVariable Long id, Model model) {
        Service service = serviceService.findById(id);
        model.addAttribute("service", service);
        return "payment";
    }

    @GetMapping("/homestay/{id}")
    public String showPaymentHomeStay(@PathVariable Long id, Model model) {
        HomeStay homeStay = homeStayService.findHomeStay(id);
        model.addAttribute("homeStay", homeStay);
        return "payment";
    }

    @PostMapping("/processService/{id}")
    public String processPaymentService(@PathVariable Long id,
                                        @RequestParam("time") String time,
                                        @ModelAttribute("service") Service service,
                                        HttpSession session,
                                        Model model) {
        Service s = serviceService.findById(id);
        BookedService bookedService = new BookedService();
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            bookedService.setService(s);
            bookedService.setUser(userService.findById(user.getId()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate bookingTime = LocalDate.parse(time, formatter);
            bookedService.setTime(bookingTime);
            bookedServiceService.saveBookedService(id, bookedService);
        }
        model.addAttribute("message", "Thanh toán dịch vụ thành công!");
        return "payment-success";
    }
    @PostMapping("/processHomeStay/{id}")
    public String processPaymentHomeStay(@PathVariable Long id,
                                         @RequestParam("checkinDate") String checkinDateStr,
                                         @RequestParam("checkoutDate") String checkoutDateStr,
                                         @RequestParam("name") String guestName,
                                         @RequestParam("phone") String guestPhone,
                                         HttpSession session,
                                         Model model) {
        HomeStay h = homeStayService.findHomeStay(id);
        BookedRoom bookedRoom = new BookedRoom();
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate checkinDate = LocalDate.parse(checkinDateStr, formatter);
            LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, formatter);

            bookedRoom.setHomeStay(h);
            bookedRoom.setUser(userService.findById(user.getId()));
            bookedRoom.setCheckInDate(checkinDate);
            bookedRoom.setCheckOutDate(checkoutDate);
            bookedRoom.setGuestName(guestName);
            bookedRoom.setGuestPhone(guestPhone);

            bookedRoomService.saveBookedRoom(id, bookedRoom);
        }

        model.addAttribute("message", "Thanh toán homestay thành công!");
        return "payment-success";
    }
}
