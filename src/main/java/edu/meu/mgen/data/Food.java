package edu.meu.mgen.data;

import java.util.HashMap;

public class Food {

        private String name;
        private double cal; // Calories per pound
        private double protein; // Protein per pound
        private double carbs;
        private double fat;
        private int portion;

        // Constructor
        public Food(String name, double cal, double protein, double carbs, double fat) {
            this.name = name;
            this.cal = cal;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
        }

        public String getName() {
            return name;
        }

        public double getCalories() {
            return cal * portion;
        }

        public double getProtien() {
            return protein * portion;
        }

        // Calculate carbs
        public double getCarbs() {
            return carbs * portion;
        }

        // Calculate fat
        public double getFat() {
            return fat * portion;
        }

        @Override
        public String toString() {
            return String.format("Food{name='%s', cal=%.2f, prot=%.2f g}", name, cal, protein);
        }
    }

//     // Mixed food class
//     static class MixedFood extends Food {
//         private List<Food> ingredients;

//         public MixedFood(String name, List<Food> ingredients, double cal, double prot) {
//             super(name, cal, prot);
//             this.ingredients = ingredients;
//         }

//         private static double sumCal(List<Food> ingredients) {
//             return ingredients.stream().mapToDouble(Food::getCal).sum();
//         }

//         private static double sumProt(List<Food> ingredients) {
//             return ingredients.stream().mapToDouble(Food::getProt).sum();
//         }

//         @Override
//         public String toString() {
//             return String.format("MixedFood{name='%s', cal=%.2f, prot=%.2f g, ingredients=%s}",
//                     // getName(), getCal(), getProt(), ingredients);
//         }
//     }

//     // Initialize food data
//     private static Map<String, Food> initFoods() {
//         Food potato = new Food("Potato", 680.0, 7.5);
//         Food latte = new Food("Latte", 146.0, 7.9);
//         Food pizza = new Food("Pizza", 484.0, 16.0);
//         Food broccoli = new Food("Broccoli", 15.0, 1.3);

//         MixedFood fries = new MixedFood("Fries", List.of(potato), 250.0, 4.0);
//         MixedFood americano = new MixedFood("Americano", List.of(latte), -146.0, -7.9);
//         MixedFood cheesePizza = new MixedFood("Cheese Pizza", List.of(pizza), 300.0, 12.0);
//         MixedFood stirFriedBroccoli = new MixedFood("Stir-fried Broccoli", List.of(broccoli), 75.0, 2.0);

//         Map<String, Food> foodMap = new HashMap<>();
//         for (Food food : List.of(potato, latte, pizza, broccoli, fries, americano, cheesePizza, stirFriedBroccoli)) {
//             foodMap.put(food.getName().toLowerCase(), food);
//         }

//         return foodMap;
//     }

//     // Query food information
//     private static void query(Map<String, Food> foodMap, String name, double weight) {
//         Food food = foodMap.get(name.toLowerCase());
//         if (food != null) {
//             double cal = food.calcCal(weight);
//             double prot = food.calcProt(weight);
//             System.out.printf("Food: %s (%.2f lbs)%n", food.getName(), weight);
//             System.out.printf("Calories: %.2f kcal, Protein: %.2f g%n", cal, prot);
//         } else {
//             System.out.println("Food not found: " + name);
//             System.out.println("Available foods:");
//             foodMap.keySet().forEach(System.out::println);
//         }
//     }
// }
