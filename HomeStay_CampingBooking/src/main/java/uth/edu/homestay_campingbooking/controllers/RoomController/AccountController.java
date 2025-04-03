package uth.edu.homestay_campingbooking.controllers.RoomController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.services.UserService;

import java.util.List;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @PostMapping("/insert")
    public void saveUser(@RequestBody @Valid User user){
        userService.save(user);
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id)  ;
    }
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUser(username);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }
    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.update(id, user);
    }

}