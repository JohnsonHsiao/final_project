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

    public void addFoodEntry(Food food) {
        foodEntries.add(food);
        totalCaloriesIntake += food.getCalories();
    }

    public void addExerciseEntry(Exercise exercise) {
        exerciseEntries.add(exercise);
        totalCaloriesBurned += exercise.getCaloriesBurned();
    }

    public double calculateNetCalories() {
        return totalCaloriesIntake - totalCaloriesBurned;
    }
}
