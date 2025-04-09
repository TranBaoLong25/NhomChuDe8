package uth.edu.homestay_campingbooking.controllers.RoomController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Đã thay đổi @RestController thành @Controller
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.services.HomeStayService;
import org.springframework.ui.Model;
import java.util.List;

@RequestMapping("/homestay")
@Controller // Đã thay đổi @RestController thành @Controller
public class HomeStayController {
    @Autowired
    HomeStayService homeStayService;

    @GetMapping
    public String findAllHomeStay(Model model) { // Changed return type to String
        List<HomeStay> homeStays = homeStayService.findAllHomeStays();
        model.addAttribute("homeStays", homeStays);
        return "index"; // Return the name of your index.html template
    }

    @GetMapping("/id/{id}")
    public HomeStay findHomeStay(@PathVariable Long id) {
        return homeStayService.findHomeStay(id);
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

    @GetMapping("/search") // Matches th:action="@{/homestay/search}" in HTML
    public String searchHomeStays(
            @RequestParam(value = "roomType", required = false) RoomType roomType,
            @RequestParam(value = "price", required = false) String priceRange,
            @RequestParam(value = "location", required = false) Location location,
            Model model) {

        Double minPrice = null;
        Double maxPrice = null;

        if (priceRange != null && !priceRange.isEmpty()) {
            String[] prices = priceRange.split("-");
            try {
                minPrice = Double.parseDouble(prices[0]);
                if (prices.length > 1) {
                    maxPrice = Double.parseDouble(prices[1]);
                } else if (priceRange.endsWith("+")) {
                    maxPrice = Double.MAX_VALUE;
                }
            } catch (NumberFormatException e) {
                // Handle the exception (e.g., log it) or set default values
                System.err.println("Invalid price range format: " + priceRange);
            }
        }

        List<HomeStay> searchResults = homeStayService.searchHomeStays(roomType, location, minPrice, maxPrice);
        model.addAttribute("searchResults", searchResults);
        return "bookedroom"; // Returns the name of the template
    }
}

