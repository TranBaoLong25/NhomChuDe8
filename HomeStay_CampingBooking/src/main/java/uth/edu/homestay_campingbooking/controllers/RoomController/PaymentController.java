package uth.edu.homestay_campingbooking.controllers.RoomController;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @GetMapping
    public String showPaymentPage(
            @RequestParam("service") String serviceName,
            @RequestParam("price") double price,
            Model model) {
        model.addAttribute("serviceName", serviceName);
        model.addAttribute("price", price);
        return "payment";
    }

    @PostMapping("/process")
    public String processPayment(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("service") String serviceName,
            @RequestParam("price") double price,
            Model model) {

        // Xử lý thanh toán (giả lập)
        model.addAttribute("message", "Thanh toán thành công cho " + name + " - " + serviceName);
        return "success"; // Trang xác nhận thanh toán thành công
    }
}

