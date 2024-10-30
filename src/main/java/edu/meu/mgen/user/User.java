package edu.meu.mgen.user;

public class User {
    private int age;
    private String gender;
    private double height;
    private double weight;
    private double targetWeight;
    private String exerciseHabits;
    private double dailyCalorieIntakeGoal;
    private double bmr;

    public User(int age, String gender, double height, double weight, double targetWeight, String exerciseHabits) {
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.targetWeight = targetWeight;
        this.exerciseHabits = exerciseHabits;
        this.bmr = calculateBMR();
        this.dailyCalorieIntakeGoal = setCalorieIntakeGoal("maintenance");
    }

    public double calculateBMR() {
        if (gender.equalsIgnoreCase("male")) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    public double setCalorieIntakeGoal(String goalType) {
        if (goalType.equalsIgnoreCase("weight loss")) {
            return bmr - 500; // Example of calorie deficit for weight loss
        } else {
            return bmr;
        }
    }
}

