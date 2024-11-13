package edu.meu.mgen.data;

public class Food {
    private String name;
    private double servingSize; // g or ml per serving
    private double caloriesPerServing; // calories per serving
    private double protein; // protein per serving in grams
    private double fat; // fat per serving in grams
    private double carbohydrates; // carbohydrates per serving in grams

    // Constructor
    public Food(String name, double servingSize, double caloriesPerServing, double protein, double fat, double carbohydrates) {
        this.name = name;
        this.servingSize = servingSize;
        this.caloriesPerServing = caloriesPerServing;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getServingSize() {
        return servingSize;
    }

    public double getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    // 計算總共攝取的卡路里，根據所吃的份量
    public double calculateTotalCalories(double servingCount) {
        return servingCount * caloriesPerServing;
    }

    @Override
    public String toString() {
        return String.format("Food{name='%s', cal=%.2f, prot=%.2f g}", name, caloriesPerServing, protein);
    }
}

