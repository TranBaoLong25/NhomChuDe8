package uth.edu.homestay_campingbooking.controllers.ManagerController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.BookedService;
import uth.edu.homestay_campingbooking.services.*;

import java.util.List;

@Controller
@RequestMapping("/managerbookedservice")
public class managerBookedServiceController {
    @Autowired
    private BookedServiceService bookedServiceService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private UserService userService;
    @GetMapping
    public String getAllBookedRooms(Model model) {
        List<BookedService> bookedServices = bookedServiceService.findAllBookedService();
        model.addAttribute("bookedServices", bookedServices);
        model.addAttribute("newBookedService", new BookedService());
        return "admin/managerbookedservice";
    }
    @PostMapping("/add")
    public String addBookedRoom(@ModelAttribute("newBookedService") @Valid BookedService bookedService,
                                @RequestParam Long serviceId,
                                @RequestParam Long userId) {
        bookedService.setService(serviceService.findById(serviceId));
        bookedService.setUser(userService.findById(userId));
        bookedServiceService.saveBookedService(serviceId, bookedService);
        return "redirect:/managerbookedservice";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookedRoom(@PathVariable Long id) {
        bookedServiceService.deleteBookedService(id);
        return "redirect:/managerbookedservice";
    }

    // Lấy thông tin phòng đã đặt cần chỉnh sửa
    @GetMapping("/edit/{id}")
    public String editBookedRoom(@PathVariable Long id, Model model) {
        BookedService bookedService = bookedServiceService.getBookedServiceById(id);
        model.addAttribute("editBookedService", bookedService);
        model.addAttribute("bookedServices", bookedServiceService.findAllBookedService());
        return "admin/managerbookedservice";
    }
    @PostMapping("/update/{id}")
    public String updateBookedRoom(@PathVariable Long id,
                                   @ModelAttribute("editBookedService") @Valid BookedService bookedService,
                                   @RequestParam Long serviceId,
                                   @RequestParam Long userId) {
        bookedService.setService(serviceService.findById(serviceId));
        bookedService.setUser(userService.findById(userId));
        bookedServiceService.updateBookedService(id, bookedService);
        return "redirect:/managerbookedservice";
    }

}