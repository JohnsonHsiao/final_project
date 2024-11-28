package edu.meu.mgen.notification;

import edu.meu.mgen.tracking.Tracker;
import edu.meu.mgen.user.User;
import java.util.List;

public class Notification {

    private double targetNetCalories;
    private double targetWeight;
    private List<String> notificationMessages;

    // Constructor
    public Notification(double targetNetCalories, double targetWeight, List<String> notificationMessages) {
        this.targetNetCalories = targetNetCalories;
        this.targetWeight = targetWeight;
        this.notificationMessages = notificationMessages;
    }

    /**
     * Sends a notification based on the net calories for the day.
     * Uses Tracker's real-time data to avoid duplicate file operations.
     */
    public void sendNetCaloriesNotification(Tracker tracker, User user) {
        double netCalories = tracker.calculateNetCalories();

        if (netCalories > targetNetCalories) {
            String message = "Alert: " + user.getUsername() +
                    ", your net calorie intake is " + netCalories +
                    " kcal today, exceeding your target of " + targetNetCalories +
                    " kcal. Consider adjusting your meals or increasing your exercise.";
            System.out.println(message);
            notificationMessages.add(message);
        } else {
            String message = "Congratulations " + user.getUsername() +
                    "! Your net calorie intake is " + netCalories +
                    " kcal today, meeting your target of " + targetNetCalories + " kcal. Keep it up!";
            System.out.println(message);
            notificationMessages.add(message);
        }
    }

    /**
     * Registers a listener for real-time net calorie changes.
     * When net calories exceed the target, it triggers a notification automatically.
     */
    public void registerNetCaloriesListener(Tracker tracker, User user) {
        tracker.setNetCaloriesListener(netCalories -> {
            if (netCalories > targetNetCalories) {
                String message = "Real-time Alert: " + user.getUsername() +
                        ", your net calorie intake is " + netCalories +
                        " kcal, exceeding your daily target of " + targetNetCalories + " kcal.";
                System.out.println(message);
                notificationMessages.add(message);
            }
        });
    }

    /**
     * Sends a weekly progress update based on the user's current and target weight.
     */
    public void sendProgressUpdate(User user, double currentWeight) {
        double initialWeight = user.getWeight(); // Assuming initial weight is stored in User
        double progress = (initialWeight - currentWeight) / (initialWeight - targetWeight) * 100;

        String message = "Progress Update: " + user.getUsername() +
                ", you have achieved " + String.format("%.2f", progress) +
                "% of your weight loss goal. Keep going!";
        System.out.println(message);
        notificationMessages.add(message);
    }

    /**
     * Sends a daily reminder to log food and exercise.
     */
    public void sendDailyReminder(User user) {
        String message = "Hi " + user.getUsername() +
                "! Don't forget to log your meals and exercises today.";
        System.out.println(message);
        notificationMessages.add(message);
    }

    /**
     * Sends a notification for daily exercise progress based on the user's target calories burned.
     */
    public void sendDailyExerciseNotification(Tracker tracker, User user, double targetCaloriesBurned) {
        double totalCaloriesBurned = tracker.getTrackingSummary().get("Total Calories Burned");

        if (totalCaloriesBurned >= targetCaloriesBurned) {
            String message = "Congratulations " + user.getUsername() +
                    "! You've burned " + totalCaloriesBurned +
                    " kcal today, exceeding your goal of " + targetCaloriesBurned + " kcal!";
            System.out.println(message);
            notificationMessages.add(message);
        } else {
            String message = "Hey " + user.getUsername() +
                    ", you've burned " + totalCaloriesBurned +
                    " kcal today. Your goal is " + targetCaloriesBurned + " kcal. Keep going!";
            System.out.println(message);
            notificationMessages.add(message);
        }
    }
}
