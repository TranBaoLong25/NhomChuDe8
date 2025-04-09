package uth.edu.homestay_campingbooking.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.BookedService;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.BookedRoomService;
<<<<<<< HEAD
import uth.edu.homestay_campingbooking.services.BookedServiceService;
=======
import uth.edu.homestay_campingbooking.services.HomeStayService; // Import HomeStayService
import uth.edu.homestay_campingbooking.models.HomeStay; // Import HomeStay model
>>>>>>> ec7f26462dfe91f3b493d619e2827c21d93613a3

import java.util.List;

@Controller
@RequestMapping("/") // Keep this to handle the root path
public class HomeController {
    @Autowired
    private BookedRoomService bookedRoomService;
    @Autowired
<<<<<<< HEAD
    private BookedServiceService bookedServiceService;

    @GetMapping("/")
    public String trangChu(){
=======
    private HomeStayService homeStayService; // Autowire HomeStayService

    @GetMapping("/") // Ensure this is just "/"
    public String trangChu(Model model){
        List<HomeStay> homeStays = homeStayService.findAllHomeStays(); // Fetch HomeStay data
        model.addAttribute("homeStays", homeStays); // Add data to the model
>>>>>>> ec7f26462dfe91f3b493d619e2827c21d93613a3
        return "index";
    }
    @GetMapping("/login")
    public String dangNhap(){
        return "login";
    }
    @GetMapping("/register")
    public String dangKy() {
        return "register";
    }
    @GetMapping("/About")
    public String gioiThieu(){
        return "About";
    }
    @GetMapping("/support")
    public String hoTro(){
        return "support";
    }
    @GetMapping("/bookedroom")
    public String datPhong(){
        return "bookedroom";
    }
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            List<BookedRoom> bookedRooms = bookedRoomService.info2(user.getId());
            if (bookedRooms != null) {
                model.addAttribute("bookedRooms", bookedRooms);
            }
            List<BookedService> bookedServices = bookedServiceService.info(user.getId());
            if (bookedServices != null) {
                model.addAttribute("bookedServices", bookedServices);
            }
        }
        return "myProfile";
    }
    @GetMapping("/payment/success")
    public String paymentSuccess(){
        return "payment-success";
    }
}