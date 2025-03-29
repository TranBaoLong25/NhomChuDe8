package uth.edu.homestay_campingbooking.controllers.ManagerAccount;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/manageruser")
public class managerUserController {

    @Autowired
    private UserService userService;

    // Hiển thị danh sách người dùng
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User()); // Đối tượng để thêm mới
        return "admin/manageruser"; // Trả về trang Thymeleaf
    }

    // Thêm người dùng mới
    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") @Valid User user) {
        userService.save(user);
        return "redirect:/manageruser";
    }

    // Xóa người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/manageruser";
    }

    // Lấy thông tin người dùng cần chỉnh sửa
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("editUser", user);
        model.addAttribute("users", userService.findAll());
        return "admin/manageruser";
    }

    // Cập nhật thông tin người dùng
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("editUser") @Valid User user) {
        userService.update(id, user);
        return "redirect:/manageruser";
    }
}
