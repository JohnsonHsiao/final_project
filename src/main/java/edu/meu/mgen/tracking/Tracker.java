package edu.meu.mgen.tracking;

import edu.meu.mgen.data.Food;
import edu.meu.mgen.data.Exercise;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private List<Food> foodEntries;
    private List<Exercise> exerciseEntries;
    private double totalCaloriesIntake;
    private double totalCaloriesBurned;

    public Tracker() {
        foodEntries = new ArrayList<>();
        exerciseEntries = new ArrayList<>();
        totalCaloriesIntake = 0;
        totalCaloriesBurned = 0;
    }

    // Add a food entry and update the total calories intake
    public void addFoodEntry(Food food) {
        foodEntries.add(food);
        totalCaloriesIntake += food.getCalories();
    }

    // Add an exercise entry and update the total calories burned
    public void addExerciseEntry(Exercise exercise) {
        exerciseEntries.add(exercise);
        totalCaloriesBurned += exercise.getCaloriesBurned();
    }

    // Calculate net calories (intake minus burned)
    public double calculateNetCalories() {
        return totalCaloriesIntake - totalCaloriesBurned;
    }

    // Get all food entries
    public List<Food> getAllFoodEntries() {
        return new ArrayList<>(foodEntries);
    }

    // Get all exercise entries
    public List<Exercise> getAllExerciseEntries() {
        return new ArrayList<>(exerciseEntries);
    }

    // Check if the user has exceeded the daily recommended intake
    public boolean isOverDailyIntake(double dailyCalorieLimit) {
        return totalCaloriesIntake > dailyCalorieLimit;
    }

    // Get total calories intake
    public double getTotalCaloriesIntake() {
        return totalCaloriesIntake;
    }

    // Get total calories burned
    public double getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    // Reset daily tracking data (can be called at the beginning of each day)
    public void resetDailyTracking() {
        foodEntries.clear();
        exerciseEntries.clear();
        totalCaloriesIntake = 0;
        totalCaloriesBurned = 0;
    }
}
