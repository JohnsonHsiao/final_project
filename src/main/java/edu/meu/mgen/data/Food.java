package edu.meu.mgen.data;

public class Food {
    private String name;
    private double calories;
    private double protein;
    private String portionSize;

    public Food(String name, double calories, double protein, String portionSize) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.portionSize = portionSize;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }
}
