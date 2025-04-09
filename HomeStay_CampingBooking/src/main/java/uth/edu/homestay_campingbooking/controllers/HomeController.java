package uth.edu.homestay_campingbooking.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.BookedRoomService;
import uth.edu.homestay_campingbooking.services.HomeStayService; // Import HomeStayService
import uth.edu.homestay_campingbooking.models.HomeStay; // Import HomeStay model

import java.util.List;

@Controller
@RequestMapping("/") // Keep this to handle the root path
public class HomeController {
    @Autowired
    private BookedRoomService bookedRoomService;
    @Autowired
    private HomeStayService homeStayService; // Autowire HomeStayService

    @GetMapping("/") // Ensure this is just "/"
    public String trangChu(Model model){
        List<HomeStay> homeStays = homeStayService.findAllHomeStays(); // Fetch HomeStay data
        model.addAttribute("homeStays", homeStays); // Add data to the model
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
        }

        return "myProfile";
    }
    @GetMapping("/edit-profile")
    public String editProfile(){
        return "edit-profile";
    }
    @GetMapping("/payment/success")
    public String paymentSuccess(){
        return "payment-success";
    }
}