package uth.edu.homestay_campingbooking.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.services.HomeStayService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        String uploadDir = "src/main/resources/static/uploads";
        Path uploadPath = Paths.get(uploadDir);

        try {
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        } catch (IOException e) {
            return "Lỗi khi tạo thư mục lưu ảnh!";
        }

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                Path imagePath = uploadPath.resolve(fileName);
                try {
                    Files.copy(image.getInputStream(), imagePath);
                    imageUrls.add("/uploads/" + fileName);
                } catch (IOException e) {
                    return "Lỗi khi lưu ảnh!";
                }
            }
        }

        homeStayService.createHomeStay(new HomeStay(null, roomType, location, roomPrice, booked, imageUrls));
        return "Thêm Homestay thành công!";
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

        HomeStay existingHomeStay = homeStayService.findHomeStay(id);
        if (existingHomeStay == null) return "Homestay không tồn tại!";

        List<String> imageUrls = new ArrayList<>(existingHomeStay.getImageUrls()); // Giữ ảnh cũ nếu không upload ảnh mới

        if (images != null && !images.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads";
            Path uploadPath = Paths.get(uploadDir);

            try {
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
            } catch (IOException e) {
                return "Lỗi khi tạo thư mục lưu ảnh!";
            }

            imageUrls.clear(); // Xóa ảnh cũ nếu có ảnh mới
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                    Path imagePath = uploadPath.resolve(fileName);
                    try {
                        Files.copy(image.getInputStream(), imagePath);
                        imageUrls.add("/uploads/" + fileName);
                    } catch (IOException e) {
                        return "Lỗi khi lưu ảnh!";
                    }
                }
            }
        }

        existingHomeStay.setRoomType(roomType);
        existingHomeStay.setLocation(location);
        existingHomeStay.setRoomPrice(roomPrice);
        existingHomeStay.setBooked(booked);
        existingHomeStay.setImageUrls(imageUrls);
        homeStayService.updateHomeStay(id, existingHomeStay);
        return "Cập nhật Homestay thành công!";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHomeStay(@PathVariable Long id) {
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
    @GetMapping("/byLocation/{location}")
    public List<HomeStay> findHomeStayByLocation(@PathVariable Location location) {
        return homeStayService.findByLocation(location);
    }
    @GetMapping
    public List<HomeStay> findAllHomeStay() {
        return homeStayService.findAllHomeStays();
    }
}
