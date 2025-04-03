package uth.edu.homestay_campingbooking.controllers.RoomController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.services.HomeStayService;

import java.util.List;

@RequestMapping("/homestay")
@RestController
public class HomeStayController {
    @Autowired
    HomeStayService homeStayService;

    @PostMapping("/insert")
    public String insertHomeStay(
            @RequestParam("roomType") RoomType roomType,
            @RequestParam("location") Location location,
            @RequestParam("roomPrice") double roomPrice,
            @RequestParam("booked") boolean booked,
            @RequestParam("images") List<MultipartFile> images) {

        return homeStayService.insertHomeStay(roomType, location, roomPrice, booked, images);
    }

    @GetMapping("/id/{id}")
    public HomeStay findHomeStay(@PathVariable Long id) {
        return homeStayService.findHomeStay(id);
    }

    @PutMapping("/update/{id}")
    public String updateHomeStay(
            @PathVariable Long id,
            @RequestParam("roomType") RoomType roomType,
            @RequestParam("location") Location location,
            @RequestParam("roomPrice") double roomPrice,
            @RequestParam("booked") boolean booked,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) {

        return homeStayService.updateHomeStay(id, roomType, location, roomPrice, booked, images);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHomeStay(@PathVariable Long id) {
        homeStayService.deleteHomeStay(id);
    }

    @GetMapping("/byPrice/{min}-{max}")
    public List<HomeStay> findHomeStayByPrice(@PathVariable double min, @PathVariable double max) {
        return homeStayService.findHomeStayByPrice(min, max);
    }

    @GetMapping("/byType/{type}")
    public List<HomeStay> findHomeStayByType(@PathVariable RoomType type) {
        return homeStayService.findHomeStayByType(type);
    }

    @GetMapping("/byLocation/{location}")
    public List<HomeStay> findHomeStayByLocation(@PathVariable Location location) {
        return homeStayService.findHomeStayByLocation(location);
    }

    @GetMapping
    public List<HomeStay> findAllHomeStay() {
        return homeStayService.findAllHomeStays();
    }
}
