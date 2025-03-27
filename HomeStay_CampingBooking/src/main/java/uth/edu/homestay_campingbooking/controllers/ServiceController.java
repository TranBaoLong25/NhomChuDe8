package uth.edu.homestay_campingbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.Service;
import uth.edu.homestay_campingbooking.services.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<Service> findAllServices() {
        return serviceService.findAllServices();
    }

    @GetMapping("/check")
    public List<Service> checkService(@RequestParam Long serviceId) {
        return serviceService.checkService(serviceId);
    }

    @PostMapping("/insert")
    public void insertService(@RequestBody Service service) {
        serviceService.saveService(service);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }

    @PutMapping("/update/{id}")
    public void updateService(@PathVariable Long id, @RequestBody Service service) {
        serviceService.updateService(id, service);
    }

    @GetMapping("/id/{id}")
    public Service findById(@PathVariable Long id) {
        return serviceService.findById(id);
    }

    @GetMapping("/servicename/{servicename}")
    public Service findByServiceName(@PathVariable String servicename) {
        return serviceService.findByName(servicename);
    }
}
