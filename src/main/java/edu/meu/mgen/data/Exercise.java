package edu.meu.mgen.data;

import java.util.HashMap;

public class Exercise {
    private String name;
    private double duration;
    private String intensity;
    private double caloriesBurned;

    public Exercise(String name, double duration, String intensity) {
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
        this.caloriesBurned = calculateCaloriesBurned();
    }
    private static final HashMap<String, Double> calorieRates = new HashMap<>();
    static {
        calorieRates.put("running_low", 8.8);
        calorieRates.put("running_medium", 13.4);
        calorieRates.put("running_high", 16.1);

        calorieRates.put("bicycling_low", 8.0);
        calorieRates.put("bicycling_medium", 9.9);
        calorieRates.put("bicycling_high", 11.8);

        calorieRates.put("Jogging_low", 6.0);
        calorieRates.put("Jogging_medium", 8.2);
        calorieRates.put("Jogging_high", 9.78);
        //可以继续加入常见运动种类
    }
    public double calculateCaloriesBurned() {
        // 将用户输入的运动种类和强度表示为卡路里map中的key类型
        String typeAndIntensity = name.toLowerCase() + "_" + intensity.toLowerCase();

        // 找到对应的每分钟消耗的卡路里 找不到为默认值为所有运动类型中的最低卡路里
        double caloriesBurnedPerMinute = calorieRates.getOrDefault(typeAndIntensity,6.0);

        return duration * caloriesBurnedPerMinute;
    }

    // Getter /Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.caloriesBurned = calculateCaloriesBurned(); // 更新卡路里消耗
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
        this.caloriesBurned = calculateCaloriesBurned(); // 更新卡路里消耗
    }

    // public String getIntensity() {
    //     return intensity;
    // }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
        this.caloriesBurned = calculateCaloriesBurned(); // 更新卡路里消耗
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }
    @Override
    public String toString() {
        return "Exercise{name='" + name + "', duration=" + duration +
                " minutes, intensity='" + intensity + "', caloriesBurned=" + caloriesBurned + "}";
    }
}
