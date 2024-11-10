package edu.meu.mgen.controller;

import edu.meu.mgen.registration.UserRegistration;
import edu.meu.mgen.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private User currentUser = null;
    private final UserRegistration userRegistration = new UserRegistration();

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) {
            model.addAttribute("username", currentUser.getUsername());
            model.addAttribute("age", currentUser.getAge());
            model.addAttribute("gender", currentUser.getGender());
            model.addAttribute("height", currentUser.getHeight());
            model.addAttribute("weight", currentUser.getWeight());
            model.addAttribute("targetWeight", currentUser.getTargetWeight());
            
            double bmr = currentUser.calculateBMR(); // 直接計算 BMR
            model.addAttribute("user", currentUser);
            model.addAttribute("bmr", bmr); // 將 BMR 添加到模型中
            return "index"; // 返回主頁面
        }
        return "login"; // 未登入時返回首頁，顯示註冊和登入選項
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // 返回註冊頁面
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam int age,
            @RequestParam String gender,
            @RequestParam double height,
            @RequestParam double weight,
            @RequestParam double targetWeight,
            Model model) {

        boolean isRegistered = userRegistration.registerUser(username, password, email, age, gender, height, weight, targetWeight);

        if (isRegistered) {
            model.addAttribute("message", "Registration successful! Please log in.");
            return "login";
        } else {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 返回登入頁面
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        boolean isAuthenticated = userRegistration.authenticateUser(username, password);

        if (isAuthenticated) {
            currentUser = userRegistration.getUserDetails(username);
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", currentUser.getUsername());
            session.setAttribute("age", currentUser.getAge());
            session.setAttribute("gender", currentUser.getGender());
            session.setAttribute("height", currentUser.getHeight());
            session.setAttribute("weight", currentUser.getWeight());
            session.setAttribute("targetWeight", currentUser.getTargetWeight());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/user/bmr")
    public String getUserBMR(Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
            return "redirect:/login";
        }
        double bmr = currentUser.calculateBMR();
        model.addAttribute("age", currentUser.getAge());
        model.addAttribute("gender", currentUser.getGender());
        model.addAttribute("height", currentUser.getHeight());
        model.addAttribute("weight", currentUser.getWeight());
        model.addAttribute("targetWeight", currentUser.getTargetWeight());
        model.addAttribute("bmr", bmr);
        return "result"; // 返回結果頁面
    }
}
