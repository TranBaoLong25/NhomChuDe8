package uth.edu.homestay_campingbooking.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.services.HomeStayService;

import java.util.List;

@RequestMapping("/homestay")
@RestController
public class HomeStayController {
    @Autowired
    HomeStayService homeStayService;
    @PostMapping("/insert")
    public void insertHomeStay(@RequestBody @Valid HomeStay homeStay) {
        homeStayService.createHomeStay(homeStay);
    }
    @GetMapping("/id/{id}")
    public HomeStay findHomeStay(@PathVariable long id) {
        return homeStayService.findHomeStay(id);
    }
    @PutMapping("/update/{id}")
    public void updateHomeStay(@PathVariable Long id, @RequestBody HomeStay homeStay) {
        homeStayService.updateHomeStay(id, homeStay);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteHomeStay(@PathVariable long id) {
        homeStayService.deleteHomeStay(id);
    }
    @GetMapping("/byPrice/{min}-{max}")
    public List<HomeStay> findHomeStayByPrice(@PathVariable double min, @PathVariable double max) {
        return homeStayService.findByPrice(min, max);
    }
    @GetMapping("/byType/{type}")
    public List<HomeStay> findHomeStayByType(@PathVariable RoomType type) {
        return homeStayService.findByType(type);
    }
    @GetMapping
    public List<HomeStay> findAllHomeStay() {
        return homeStayService.findAllHomeStays();
    }
}
