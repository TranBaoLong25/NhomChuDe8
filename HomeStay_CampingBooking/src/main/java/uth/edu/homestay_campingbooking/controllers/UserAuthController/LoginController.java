package uth.edu.homestay_campingbooking.controllers.UserAuthController;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    UserService userService;
    @PostMapping
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        User user = userService.findByUser(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        // Lưu user vào session
        session.setAttribute("loggedInUser", user);
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}