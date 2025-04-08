package uth.edu.homestay_campingbooking.controllers.ManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uth.edu.homestay_campingbooking.models.Service;
import uth.edu.homestay_campingbooking.services.ServiceService;

import java.util.List;

@Controller
@RequestMapping("/managerservice")
public class managerServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public String getAllServices(Model model) {
        List<Service> services = serviceService.findAllServices();
        model.addAttribute("services", services);
        return "admin/managerservice";
    }

    @PostMapping("/insert")
    public String addService(@ModelAttribute Service service) {
        serviceService.saveService(service);
        return "redirect:/managerservice";
    }

    @GetMapping("/edit/{id}")
    public String editService(@PathVariable Long id, Model model) {
        Service service = serviceService.findById(id);
        model.addAttribute("editService", service);
        model.addAttribute("services", serviceService.findAllServices());
        return "admin/managerservice";
    }

    @PostMapping("/update/{id}")
    public String updateService(@PathVariable Long id, @ModelAttribute Service newService) {
        serviceService.updateService(id, newService);
        return "redirect:/managerservice";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "redirect:/managerservice";
    }
    @GetMapping("/search")
    public String searchService(@RequestParam String keyword, Model model) {
        try {
            Service service = serviceService.findByName(keyword);
            model.addAttribute("services", List.of(service));
        } catch (Exception e) {
            model.addAttribute("services", null);
        }
        model.addAttribute("newService", new Service());
        return "admin/managerservice";
    }
}