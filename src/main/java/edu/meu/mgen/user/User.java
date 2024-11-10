package edu.meu.mgen.user;

public class User {
    private String username;
    private String password; // 存儲加密後的密碼
    private String email;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private double targetWeight;

    public User(String username, String password, String email, int age, String gender, double height, double weight, double targetWeight) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.targetWeight = targetWeight;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getTargetWeight() {
        return targetWeight;
    }
    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }
    public double calculateBMR() {
        if (gender.equalsIgnoreCase("male")) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }
}
