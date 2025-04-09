package uth.edu.homestay_campingbooking.controllers.RoomController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.Service;
import uth.edu.homestay_campingbooking.services.ServiceService;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public String findAllServices(Model model) {
        List<Service> services = serviceService.findAllServices();
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/search")
    public String search (@RequestParam String name, Model model) {
        try{
            Service service = serviceService.findByName(name);
            model.addAttribute("services", List.of(service));
        }catch (Exception e){
            model.addAttribute("service", null);
        }
        model.addAttribute("newService", new Service());
        return "services";
    }
}