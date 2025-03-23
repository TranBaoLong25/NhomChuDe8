package uth.edu.homestay_campingbooking.controllers.UserAuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.models.Role;
import uth.edu.homestay_campingbooking.services.UserService;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               @RequestParam Role role,
                               Model model) {
        for (User user: userService.findAll()) {
            if (user.getUsername().equals(username)) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
        }
        if (password.length() < 8) {
            throw new AppException(ErrorCode.PASSWORD_INVALID);
        }
        if (!password.equals(confirmPassword)) {
            throw new AppException(ErrorCode.PASSWORD_ERROR);
        }
        User newUser = new User(username, password, role);
        userService.save(newUser);
        return "redirect:/login";
    }
}