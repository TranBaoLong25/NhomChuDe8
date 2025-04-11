package uth.edu.homestay_campingbooking.controllers.UserAuthController;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.BookedRoom;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.UserService;

@Controller
@RequestMapping(value = "/edit-profile")
public class updateController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String updateProfile(@RequestParam String username,@RequestParam String password,
                                @RequestParam String confirmPassword, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            for (User user : userService.findAll()) {
                if (user.getUsername().equals(username)) {
                    throw new AppException(ErrorCode.USER_EXISTED);
                }
            }
            if (username.length() < 4) {
                throw new AppException(ErrorCode.USERNAME_INVALID);
            }
            if (password.length()<8) {
                throw new AppException(ErrorCode.PASSWORD_INVALID);
            }
            if (!password.equals(confirmPassword)) {
                throw new AppException(ErrorCode.PASSWORD_ERROR2);
            }
            loggedInUser.setUsername(username);
            loggedInUser.setPassword(password);
            userService.update(loggedInUser.getId(), loggedInUser);
            session.setAttribute("loggedInUser", loggedInUser);
        }
        return "redirect:/profile";
    }
}
