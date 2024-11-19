package edu.meu.mgen.tracking;

import edu.meu.mgen.data.Food;
import edu.meu.mgen.data.Exercise;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Tracker {
    private double totalCaloriesIntake;
    private double totalCaloriesBurned;
    private String userDirectoryPath;

    public Tracker(String username) {
        totalCaloriesIntake = 0;
        totalCaloriesBurned = 0;
        userDirectoryPath = "data/" + username;

        ensureDirectoryExists();
        loadExistingData(); 
    }

    // Ensure user directory exists
    private void ensureDirectoryExists() {
        File userDir = new File(userDirectoryPath);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    // Load existing data from food_records.csv and exercise_records.csv
    private void loadExistingData() {
        loadFoodData();
        loadExerciseData();
    }

    private void loadFoodData() {
        String filePath = userDirectoryPath + "/food_records.csv";
        File foodFile = new File(filePath);
        if (!foodFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(foodFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                double totalCalories = Double.parseDouble(fields[7]); // Total Calories
                totalCaloriesIntake += totalCalories;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadExerciseData() {
        String filePath = userDirectoryPath + "/exercise_records.csv";
        File exerciseFile = new File(filePath);
        if (!exerciseFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(exerciseFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                double totalCaloriesBurned = Double.parseDouble(fields[4]); // Total Calories Burned
                this.totalCaloriesBurned += totalCaloriesBurned;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Track food: update totalCaloriesIntake and write to CSV
    public void trackFood(Food food, double servingCount) {
        double calories = food.getCaloriesPerServing() * servingCount;
        totalCaloriesIntake += calories;
        writeFoodToCsv(food, servingCount, calories);
    }

    // Track exercise: update totalCaloriesBurned and write to CSV
    public void trackExercise(Exercise exercise) {
        double caloriesBurned = exercise.calculateCaloriesBurned();
        totalCaloriesBurned += caloriesBurned;
        writeExerciseToCsv(exercise, caloriesBurned);
    }

    // Write food entry to CSV
    private void writeFoodToCsv(Food food, double servingCount, double totalCalories) {
        String filePath = userDirectoryPath + "/food_records.csv";
        boolean fileExists = new File(filePath).exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (!fileExists) {
                writer.write("Name,Calories,Protein,Carbs,Fat,Serving Size,Serving Count,Total Calories,Timestamp\n");
            }
            String timestamp = LocalDate.now() + " 00:00:00"; // Current date
            writer.write(String.format("%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s\n",
                    food.getName(), food.getCaloriesPerServing(), food.getProtein(),
                    food.getCarbohydrates(), food.getFat(), food.getServingSize(),
                    servingCount, totalCalories, timestamp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write exercise entry to CSV
    private void writeExerciseToCsv(Exercise exercise, double totalCaloriesBurned) {
        String filePath = userDirectoryPath + "/exercise_records.csv";
        boolean fileExists = new File(filePath).exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (!fileExists) {
                writer.write("Name,Intensity,Calories Burned Per Minute,Duration,Total Calories Burned,Timestamp\n");
            }
            String timestamp = LocalDate.now() + " 00:00:00"; // Current date
            writer.write(String.format("%s,%s,%.2f,%.2f,%.2f,%s\n",
                    exercise.getName(), exercise.getIntensity(), exercise.getCaloriesBurnedPerMinute(),
                    exercise.getDuration(), totalCaloriesBurned, timestamp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Calculate net calories (intake minus burned)
    public double calculateNetCalories() {
        return totalCaloriesIntake - totalCaloriesBurned;
    }

    // Get tracking summary for front-end display
    public Map<String, Double> getTrackingSummary() {
        Map<String, Double> summary = new HashMap<>();
        summary.put("Total Calories Intake", totalCaloriesIntake);
        summary.put("Total Calories Burned", totalCaloriesBurned);
        summary.put("Net Calories", calculateNetCalories());
        return summary;
    }
}
