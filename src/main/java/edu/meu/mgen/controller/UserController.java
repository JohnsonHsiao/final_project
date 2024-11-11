package edu.meu.mgen.controller;

import edu.meu.mgen.data.Exercise;
import edu.meu.mgen.data.ExerciseData;
import edu.meu.mgen.data.Food;
import edu.meu.mgen.data.FoodData;
import edu.meu.mgen.registration.UserRegistration;
import edu.meu.mgen.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    private FoodData foodData;

    @Autowired
    private ExerciseData exerciseData;

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

    @GetMapping("/selectFood")
    public String selectFood(Model model) {
        model.addAttribute("foodList", foodData.getFoodList());
        return "select_food";
    }

    @PostMapping("/addFoodRecord")
    public String addFoodRecord(@RequestParam String food, @RequestParam double servingCount) {
        Food selectedFood = foodData.getFoodList().stream()
                .filter(f -> f.getName().equals(food))
                .findFirst()
                .orElse(null);
        if (selectedFood != null && currentUser != null) {
            currentUser.writeFoodToCsv(selectedFood, servingCount);
        }
        return "redirect:/";
    }

    @GetMapping("/selectExercise")
    public String selectExercise(Model model) {
        model.addAttribute("exerciseList", exerciseData.getExerciseList());
        return "select_exercise";
    }

    @PostMapping("/addExerciseRecord")
    public String addExerciseRecord(@RequestParam String exercise, @RequestParam String intensity, @RequestParam double duration) {
        Exercise selectedExercise = exerciseData.getExerciseList().stream()
                .filter(e -> e.getName().equals(exercise))
                .findFirst()
                .orElse(null);
        if (selectedExercise != null && currentUser != null) {
            selectedExercise.setIntensity(intensity);
            currentUser.writeExerciseToCsv(selectedExercise, duration);
        }
        return "redirect:/";
    }

    @GetMapping("/userRecord")
    public String userRecord(Model model) {
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            List<Map<String, String>> foodRecords = readCsvRecords(currentUser.getUsername(), "food_records.csv");
            List<Map<String, String>> exerciseRecords = readCsvRecords(currentUser.getUsername(), "exercise_records.csv");
            model.addAttribute("foodRecords", foodRecords);
            model.addAttribute("exerciseRecords", exerciseRecords);
            System.out.println("Food Records: " + foodRecords);
            System.out.println("Exercise Records: " + exerciseRecords);
        }
        return "user_record";
    }

    private List<Map<String, String>> readCsvRecords(String username, String fileName) {
        List<Map<String, String>> records = new ArrayList<>();
        String filePath = "data/" + username + "/" + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(",");
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    Map<String, String> record = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        record.put(headers[i], values[i]);
                    }
                    records.add(record);
                    System.out.println("Read record: " + record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    @GetMapping("/logout")
    public String logout() {
        currentUser = null;
        return "redirect:/login";
    }
}
