package edu.meu.mgen.notification;

import edu.meu.mgen.tracking.Tracker;
import edu.meu.mgen.user.User;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Notification {

    // Dynamically calculate the target weight for the current week
    private double calculateWeeklyTargetWeight(double initialWeight, double targetWeight, int totalWeeks, int currentWeek) {
        double weightLossPerWeek = (initialWeight - targetWeight) / totalWeeks;
        return initialWeight - (weightLossPerWeek * currentWeek);
    }

    // Check weight progress and send appropriate notification
    public void sendProgressNotification(User user, double currentWeight, double initialWeight, double targetWeight, int totalWeeks, LocalDate startDate) {
        int currentWeek = (int) ChronoUnit.WEEKS.between(startDate, LocalDate.now());

        // Ensure current week does not exceed total weeks
        if (currentWeek > totalWeeks) {
            currentWeek = totalWeeks;
        }

        // Calculate the target weight for the current week
        double weeklyTargetWeight = calculateWeeklyTargetWeight(initialWeight, targetWeight, totalWeeks, currentWeek);

        // Determine if the current weight meets the target weight for the current week
        if (currentWeight <= weeklyTargetWeight) {
            sendGoalReachedNotification(user, currentWeek, totalWeeks);
        } else {
            sendMotivationNotification(user, weeklyTargetWeight);
        }
    }

    // Send goal reached notification (dynamic for each week)
    private void sendGoalReachedNotification(User user, int currentWeek, int totalWeeks) {
        System.out.println("Congratulations " + user.getUsername() + "! You've achieved your target for week " + currentWeek + " of " + totalWeeks + ". Fantastic job!");
    }

    // Send motivational notification (includes weekly target weight)
    private void sendMotivationNotification(User user, double weeklyTargetWeight) {
        System.out.println("Don't give up, " + user.getUsername() + "! Your goal for this week is " + weeklyTargetWeight + " lbs. You're closer to your goal than you think.");
    }

    // Notification for exceeding daily calorie limit
    public void sendCalorieLimitExceededNotification(User user, Tracker tracker, double dailyCalorieLimit) {
        if (tracker.isOverDailyIntake(dailyCalorieLimit)) {
            System.out.println("Alert: " + user.getUsername() + ", you've exceeded your daily calorie limit of " + dailyCalorieLimit + " kcal. Consider adjusting your intake or adding some exercise.");
        }
    }

    // Notification for reaching daily calorie goal
    public void sendCalorieGoalReachedNotification(User user, Tracker tracker, double targetCalories) {
        double netCalories = tracker.calculateNetCalories();
        if (netCalories <= targetCalories) {
            System.out.println("Congratulations " + user.getUsername() + "! You've reached your calorie goal for the day.");
        }
    }

    // Daily reminder for logging meals and exercises
    public void sendDailyReminder(User user) {
        System.out.println("Hi " + user.getUsername() + "! Don't forget to log your meals and exercises today.");
    }

    // Weekly weight update reminder
    public void sendWeeklyWeightUpdateReminder(User user) {
        // Check if today is the specified reminder day, e.g., every Sunday
        DayOfWeek reminderDay = DayOfWeek.SUNDAY; // Can be changed to any desired day
        if (LocalDate.now().getDayOfWeek() == reminderDay) {
            System.out.println("Weekly Reminder: " + user.getUsername() + ", please update your weight data to track your progress accurately.");
        }
    }
}
