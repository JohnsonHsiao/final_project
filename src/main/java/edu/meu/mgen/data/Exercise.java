package edu.meu.mgen.data;

import java.util.HashMap;

public class Exercise {
    private String name;
    private double duration;
    private String intensity;
    // private double caloriesBurned;
    private static final HashMap<String, Double> calorieRates = new HashMap<>();
    
    static {
        calorieRates.put("running_low", 8.8);
        calorieRates.put("running_medium", 13.4);
        calorieRates.put("running_high", 16.1);
        calorieRates.put("cycling_low", 8.0);
        calorieRates.put("cycling_medium", 9.9);
        calorieRates.put("cycling_high", 11.8);
        calorieRates.put("jogging_low", 6.0);
        calorieRates.put("jogging_medium", 8.2);
        calorieRates.put("jogging_high", 9.78);
    }

    public Exercise(String name, double duration, String intensity) {
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
    }

    // Getter /Setter
    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
    
    public double calculateTotalCaloriesBurned() {
        String key = name.toLowerCase() + "_" + intensity.toLowerCase();
        double caloriesBurnedPerMinute = calorieRates.getOrDefault(key, 5.0); // 默認每分鐘5卡路里
        return duration * caloriesBurnedPerMinute;
    }

    @Override
    public String toString() {
        return "Exercise{name='" + name + "', duration=" + duration +
                " minutes, intensity='" + intensity + "', caloriesBurned=" + calculateTotalCaloriesBurned() + "}";
    }
}
