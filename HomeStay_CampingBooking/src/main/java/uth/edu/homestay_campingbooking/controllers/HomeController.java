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

}
