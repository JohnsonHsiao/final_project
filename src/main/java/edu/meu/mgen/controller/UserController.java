package edu.meu.mgen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.meu.mgen.user.User; // 確保 User 的導入正確


@Controller
public class UserController {

    @GetMapping("/")
    public String home() {
        return "index"; // 返回 templates/index.html
    }

    // BMR 計算路徑
    @GetMapping("/user/bmr")
    public String getUserBMR(
        @RequestParam int age,
        @RequestParam String gender,
        @RequestParam double height,
        @RequestParam double weight,
        @RequestParam double targetWeight,
        Model model) {

        User user = new User(age, gender, height, weight, targetWeight, "moderate");
        double bmr = user.calculateBMR();

        model.addAttribute("age", age);
        model.addAttribute("gender", gender);
        model.addAttribute("height", height);
        model.addAttribute("weight", weight);
        model.addAttribute("targetWeight", targetWeight);
        model.addAttribute("bmr", bmr);
        return "result"; // 返回 result.html
    }
}
