package uth.edu.homestay_campingbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String trangChu(){
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
    @GetMapping("/service")
    public String service(){
        return "service";
    }
    @GetMapping("/profile")
    public String profile(){
        return "myProfile";
    }
    @GetMapping("/edit-profile")
    public String editProfile(){
        return "edit-profile";
    }
    @GetMapping("/managerhomestay")
    public String managerHomestay(){
        return "admin/managerhomestay";
    }
    @GetMapping("/managerbookedroom")
    public String managerBookedRoom(){
        return "admin/managerbookedroom";
    }
}
