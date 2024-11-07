package edu.meu.mgen.tracking;

import edu.meu.mgen.notification.Notification;
import edu.meu.mgen.tracking.Tracker;
import edu.meu.mgen.data.Food;
import edu.meu.mgen.data.Exercise;
import edu.meu.mgen.user.User;

public class TrackerTest {

    public static void main(String[] args) {
        // 创建测试用户
        User user = new User("JohnDoe", "password123", "johndoe@example.com", 30, "Male", 180.0, 185.0, 165.0);

        // 设置体重目标相关的参数：初始体重、目标体重、总周数、开始日期
        double initialWeight = 185.0;
        double targetWeight = 165.0;
        int totalWeeks = 20;

        // 创建 Tracker 和 Notification 实例
        Tracker tracker = new Tracker();
        Notification notification = new Notification();

        // 测试 1：添加食物条目并检查总卡路里摄入
        System.out.println("Test 1: Add Food Entries and Check Total Calories Intake");
        tracker.addFoodEntry(new Food("Sandwich", 300, 12, "1 piece"));
        tracker.addFoodEntry(new Food("Apple", 100, 0.5, "1 piece"));
        tracker.addFoodEntry(new Food("Coffee", 50, 0, "1 cup"));
        System.out.println("Total Calories Intake: " + tracker.getTotalCaloriesIntake()); // 预期输出：450

        // 测试 2：添加运动条目并检查总卡路里消耗
        System.out.println("\nTest 2: Add Exercise Entries and Check Total Calories Burned");
        tracker.addExerciseEntry(new Exercise("Running", 60, "High"));
        tracker.addExerciseEntry(new Exercise("Cycling", 45, "Moderate"));
        System.out.println("Total Calories Burned: " + tracker.getTotalCaloriesBurned()); // 预期输出示例

        // 测试 3：计算净卡路里
        System.out.println("\nTest 3: Calculate Net Calories");
        System.out.println("Net Calories: " + tracker.calculateNetCalories());

        // 测试 4：检查是否超出每日卡路里摄入限额
        System.out.println("\nTest 4: Check if Over Daily Intake Limit");
        double dailyCalorieLimit = 400;
        System.out.println("Is Over Daily Intake? " + tracker.isOverDailyIntake(dailyCalorieLimit)); // 预期输出：true

        // 测试 5：获取所有食物条目
        System.out.println("\nTest 5: Get All Food Entries");
        tracker.getAllFoodEntries().forEach(food -> 
            System.out.println("Food: " + food.getName() + ", Calories: " + food.getCalories() + ", Protein: " + food.getProtein() + ", Portion: " + food.getPortionSize())
        );

        // 测试 6：获取所有运动条目
        System.out.println("\nTest 6: Get All Exercise Entries");
        tracker.getAllExerciseEntries().forEach(exercise -> 
            System.out.println("Exercise: " + exercise.getName() + ", Duration: " + exercise.getDuration() + ", Intensity: " + exercise.getIntensity())
        );

        // 测试 7：重置每日跟踪数据
        System.out.println("\nTest 7: Reset Daily Tracking");
        tracker.resetDailyTracking();
        System.out.println("Total Calories Intake After Reset: " + tracker.getTotalCaloriesIntake()); // 预期输出：0
        System.out.println("Total Calories Burned After Reset: " + tracker.getTotalCaloriesBurned()); // 预期输出：0
    }
}
