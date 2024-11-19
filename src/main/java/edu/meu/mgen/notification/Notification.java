package edu.meu.mgen.notification;

import edu.meu.mgen.tracking.Tracker;
import edu.meu.mgen.user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Notification {

    private static final String FOOD_RECORDS_FILENAME = "food_records.csv";
    private static final String EXERCISE_RECORDS_FILENAME = "exercise_records.csv";

    // Calculate total calories consumed for the current day from food_records.csv
    public double calculateDailyCaloriesConsumed(User user) {
        String userDirectoryPath = "data/" + user.getUsername();
        File foodFile = new File(userDirectoryPath, FOOD_RECORDS_FILENAME);
        if (!foodFile.exists()) {
            System.out.println("No food records found for user: " + user.getUsername());
            return 0;
        }

        double totalCaloriesConsumed = 0;
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(foodFile))) {
            String line = br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String timestamp = fields[8].split(" ")[0]; // Extract date part
                if (timestamp.equals(today.format(dateFormatter))) {
                    totalCaloriesConsumed += Double.parseDouble(fields[7]); // Total Calories
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalCaloriesConsumed;
    }

    // Calculate total calories burned for the current day from exercise_records.csv
    public double calculateDailyCaloriesBurned(User user) {
        String userDirectoryPath = "data/" + user.getUsername();
        File exerciseFile = new File(userDirectoryPath, EXERCISE_RECORDS_FILENAME);
        if (!exerciseFile.exists()) {
            System.out.println("No exercise records found for user: " + user.getUsername());
            return 0;
        }

        double totalCaloriesBurned = 0;
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(exerciseFile))) {
            String line = br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String timestamp = fields[5].split(" ")[0]; // Extract date part
                if (timestamp.equals(today.format(dateFormatter))) {
                    totalCaloriesBurned += Double.parseDouble(fields[4]); // Total Calories Burned
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalCaloriesBurned;
    }

    // Calculate net calories for the current day
    public double calculateNetCalories(User user) {
        double totalCaloriesConsumed = calculateDailyCaloriesConsumed(user);
        double totalCaloriesBurned = calculateDailyCaloriesBurned(user);
        return totalCaloriesConsumed - totalCaloriesBurned;
    }

    // Send net calories notification based on user's target
    public void sendNetCaloriesNotification(User user, Tracker tracker, double targetNetCalories) {
        double netCalories = tracker.calculateNetCalories();

        if (netCalories > targetNetCalories) {
            System.out.println("Alert: " + user.getUsername() + ", your net calorie intake is " +
                    netCalories + " kcal today, which exceeds your target of " + targetNetCalories +
                    " kcal. Consider adjusting your meals or increasing your exercise.");
        } else {
            System.out.println("Congratulations " + user.getUsername() +
                    "! Your net calorie intake is " + netCalories + " kcal today, meeting your target of " +
                    targetNetCalories + " kcal. Keep it up!");
        }
    }

    // Send daily calorie limit exceeded notification
    public void sendCalorieLimitExceededNotification(User user, Tracker tracker, double dailyCalorieLimit) {
        if (tracker.isOverDailyIntake(dailyCalorieLimit)) {
            System.out.println("Alert: " + user.getUsername() +
                    ", you've exceeded your daily calorie limit of " + dailyCalorieLimit + " kcal. Consider adjusting your intake.");
        }
    }

    // Send daily exercise notification
    public void sendDailyExerciseNotification(User user, double targetCaloriesBurned) {
        double totalCaloriesBurned = calculateDailyCaloriesBurned(user);

        if (totalCaloriesBurned >= targetCaloriesBurned) {
            System.out.println("Congratulations " + user.getUsername() +
                    "! You've burned " + totalCaloriesBurned + " kcal today, exceeding your goal of " +
                    targetCaloriesBurned + " kcal!");
        } else {
            System.out.println("Hey " + user.getUsername() +
                    ", you've burned " + totalCaloriesBurned + " kcal today. Your goal is " +
                    targetCaloriesBurned + " kcal. Keep going!");
        }
    }

    // Send daily food logging reminder
    public void sendDailyReminder(User user) {
        System.out.println("Hi " + user.getUsername() + "! Don't forget to log your meals and exercises today.");
    }

    // Send weekly weight update reminder
    public void sendWeeklyWeightUpdateReminder(User user) {
        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek().getValue() == 7) { // Assuming Sunday is the reminder day
            System.out.println("Weekly Reminder: " + user.getUsername() +
                    ", please update your weight data to track your progress accurately.");
        }
    }
}
