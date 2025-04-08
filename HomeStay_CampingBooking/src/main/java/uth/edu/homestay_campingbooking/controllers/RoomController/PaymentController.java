package uth.edu.homestay_campingbooking.controllers.RoomController;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Service;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.BookedRoomService;
import uth.edu.homestay_campingbooking.services.HomeStayService;
import uth.edu.homestay_campingbooking.services.ServiceService;
import uth.edu.homestay_campingbooking.services.UserService;

import java.time.LocalDate;

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
                                        @ModelAttribute("service") Service service,
                                        HttpSession session,
                                        Model model) {
        model.addAttribute("message", "Thanh toán dịch vụ thành công!");
        return "payment-success";
    }
    @PostMapping("/processHomeStay/{id}")
    public String processPaymentHomeStay(@PathVariable Long id,
                                         @ModelAttribute("homeStay") HomeStay homeStay,
                                         HttpSession session,
                                         Model model) {
        HomeStay h = homeStayService.findHomeStay(id);
        BookedRoom bookedRoom = new BookedRoom();
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            bookedRoom.setHomeStay(homeStayService.findHomeStay(id));
            bookedRoom.setUser(userService.findById(user.getId()));
            bookedRoomService.saveBookedRoom(id, bookedRoom);
        }
        model.addAttribute("message", "Thanh toán homestay thành công!");
        return "payment-success";
    }
}
