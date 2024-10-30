package edu.meu.mgen.data;

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

    public double calculateCaloriesBurned() {
        // This is a simple placeholder calculation.
        return duration * (intensity.equals("high") ? 10 : 5);
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }
}
