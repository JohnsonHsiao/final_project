package edu.meu.mgen.notification;

import edu.meu.mgen.tracking.Tracker;
import edu.meu.mgen.user.User;

public class Notification {

    private double targetNetCalories;
    private double targetWeight;

    // Constructor
    public Notification(double targetNetCalories, double targetWeight) {
        this.targetNetCalories = targetNetCalories;
        this.targetWeight = targetWeight;
    }

    /**
     * Sends a notification based on the net calories for the day.
     * Uses Tracker's real-time data to avoid duplicate file operations.
     */
    public void sendNetCaloriesNotification(Tracker tracker, User user) {
        double netCalories = tracker.calculateNetCalories();

        if (netCalories > targetNetCalories) {
            System.out.println("Alert: " + user.getUsername() + 
                    ", your net calorie intake is " + netCalories + 
                    " kcal today, exceeding your target of " + targetNetCalories + 
                    " kcal. Consider adjusting your meals or increasing your exercise.");
        } else {
            System.out.println("Congratulations " + user.getUsername() + 
                    "! Your net calorie intake is " + netCalories + 
                    " kcal today, meeting your target of " + targetNetCalories + " kcal. Keep it up!");
        }
    }

    /**
     * Registers a listener for real-time net calorie changes.
     * When net calories exceed the target, it triggers a notification automatically.
     */
    public void registerNetCaloriesListener(Tracker tracker, User user) {
        tracker.setNetCaloriesListener(netCalories -> {
            if (netCalories > targetNetCalories) {
                System.out.println("Real-time Alert: " + user.getUsername() + 
                        ", your net calorie intake is " + netCalories + 
                        " kcal, exceeding your daily target of " + targetNetCalories + " kcal.");
            }
        });
    }

    /**
     * Sends a weekly progress update based on the user's current and target weight.
     */
    public void sendProgressUpdate(User user, double currentWeight) {
        double initialWeight = user.getWeight(); // Assuming initial weight is stored in User
        double progress = (initialWeight - currentWeight) / (initialWeight - targetWeight) * 100;

        System.out.println("Progress Update: " + user.getUsername() + 
                ", you have achieved " + String.format("%.2f", progress) + 
                "% of your weight loss goal. Keep going!");
    }

    /**
     * Sends a daily reminder to log food and exercise.
     */
    public void sendDailyReminder(User user) {
        System.out.println("Hi " + user.getUsername() + 
                "! Don't forget to log your meals and exercises today.");
    }

    /**
     * Sends a notification for daily exercise progress based on the user's target calories burned.
     */
    public void sendDailyExerciseNotification(Tracker tracker, User user, double targetCaloriesBurned) {
        double totalCaloriesBurned = tracker.getTrackingSummary().get("Total Calories Burned");

        if (totalCaloriesBurned >= targetCaloriesBurned) {
            System.out.println("Congratulations " + user.getUsername() + 
                    "! You've burned " + totalCaloriesBurned + 
                    " kcal today, exceeding your goal of " + targetCaloriesBurned + " kcal!");
        } else {
            System.out.println("Hey " + user.getUsername() + 
                    ", you've burned " + totalCaloriesBurned + 
                    " kcal today. Your goal is " + targetCaloriesBurned + " kcal. Keep going!");
        }
    }
}
