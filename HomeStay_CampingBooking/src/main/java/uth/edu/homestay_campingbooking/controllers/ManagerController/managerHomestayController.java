package uth.edu.homestay_campingbooking.controllers.ManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.services.HomeStayService;

import java.util.List;

@Controller
@RequestMapping("/managerhomestay")
public class managerHomestayController {

    @Autowired
    private HomeStayService homeStayService;

    @GetMapping
    public String getAllHomeStays(Model model) {
        List<HomeStay> homeStays = homeStayService.findAllHomeStays();
        model.addAttribute("homeStays", homeStays);
        model.addAttribute("newHomeStay", new HomeStay());
        model.addAttribute("roomTypes", RoomType.values());
        model.addAttribute("locations", Location.values());
        return "admin/managerhomestay";
    }

    @PostMapping("/add")
    public String addHomeStay(@ModelAttribute("newHomeStay") HomeStay homeStay,
                              @RequestParam("images") List<MultipartFile> images,
                              @RequestParam("roomType") RoomType roomType,
                              @RequestParam("location") Location location,
                              @RequestParam("roomPrice") double roomPrice,
                              @RequestParam(name = "booked", defaultValue = "false") boolean booked) {
        homeStayService.insertHomeStay(roomType, location, roomPrice, booked, images);
        return "redirect:/managerhomestay";
    }

    @GetMapping("/delete/{id}")
    public String deleteHomeStay(@PathVariable Long id) {
        homeStayService.deleteHomeStay(id);
        return "redirect:/managerhomestay";
    }

    @GetMapping("/edit/{id}")
    public String editHomeStay(@PathVariable Long id, Model model) {
        HomeStay homeStay = homeStayService.findHomeStay(id);
        model.addAttribute("editHomeStay", homeStay);
        model.addAttribute("roomTypes", RoomType.values());
        model.addAttribute("locations", Location.values());
        return "admin/managerhomestay";
    }

    @PostMapping("/update/{id}")
    public String updateHomeStay(@PathVariable Long id,
                                 @ModelAttribute("editHomeStay") HomeStay homeStay,
                                 @RequestParam("images") List<MultipartFile> images,
                                 @RequestParam("roomType") RoomType roomType,
                                 @RequestParam("location") Location location,
                                 @RequestParam("roomPrice") double roomPrice,
                                 @RequestParam(name = "booked", defaultValue = "false") boolean booked) {
        homeStayService.updateHomeStay(id, roomType, location, roomPrice, booked, images);
        return "redirect:/managerhomestay";
    }
}
