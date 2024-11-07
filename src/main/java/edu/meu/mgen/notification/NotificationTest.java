package edu.meu.mgen.notification;


import edu.meu.mgen.tracking.Tracker;
import edu.meu.mgen.data.Food;
import edu.meu.mgen.data.Exercise;
import edu.meu.mgen.user.User;

import java.time.LocalDate;

public class NotificationTest {

    public static void main(String[] args) {
        // 创建测试用户
        User user = new User("JohnDoe", "password123", "johndoe@example.com", 30, "Male", 180, 185, 165);

        // 设置体重目标相关的参数：初始体重、目标体重、总周数、开始日期
        double initialWeight = 185;
        double targetWeight = 165;
        int totalWeeks = 20;
        LocalDate startDate = LocalDate.now().minusWeeks(10); // 假设目标从10周前开始

        // 创建 Tracker 和 Notification 实例
        Tracker tracker = new Tracker();
        Notification notification = new Notification();

        // 测试 1：发送每周体重进展通知
        System.out.println("Test 1: Weekly Weight Progress Notification");
        double currentWeight = 175; // 当前体重模拟为175磅
        notification.sendProgressNotification(user, currentWeight, initialWeight, targetWeight, totalWeeks, startDate);

        // 测试 2：发送卡路里超限通知
        System.out.println("\nTest 2: Calorie Limit Exceeded Notification");
        double dailyCalorieLimit = 2000;
        tracker.addFoodEntry(new Food("Pasta", 700, 15, "1 bowl")); // 添加食物记录，模拟摄入的卡路里
        tracker.addFoodEntry(new Food("Salad", 300, 5, "1 plate"));
        tracker.addFoodEntry(new Food("Soda", 1200, 0, "1 bottle")); // 总卡路里：2200
        notification.sendCalorieLimitExceededNotification(user, tracker, dailyCalorieLimit);

        // 测试 3：发送卡路里目标达成通知
        System.out.println("\nTest 3: Calorie Goal Reached Notification");
        tracker.addExerciseEntry(new Exercise("Running", 60, "High")); // 模拟运动消耗的卡路里
        double targetCalories = 1800;
        notification.sendCalorieGoalReachedNotification(user, tracker, targetCalories);

        // 测试 4：发送每日提醒通知
        System.out.println("\nTest 4: Daily Reminder Notification");
        notification.sendDailyReminder(user);

        // 测试 5：发送每周体重更新提醒（假设今天是提醒日）
        System.out.println("\nTest 5: Weekly Weight Update Reminder");
        notification.sendWeeklyWeightUpdateReminder(user);
    }
}
