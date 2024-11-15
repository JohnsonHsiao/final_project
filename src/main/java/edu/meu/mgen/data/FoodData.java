package edu.meu.mgen.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FoodData {
    private List<Food> foodList;

    public FoodData() {
        foodList = new ArrayList<>();
        foodList.add(new Food("Apple", 100, 52, 0.3, 0.2, 14));
        foodList.add(new Food("Banana", 118, 105, 1.3, 0.3, 27));
        foodList.add(new Food("Noodles", 150, 284, 8.3, 0.7, 61.1));
        foodList.add(new Food("Rice", 150, 116, 2.6, 0.8, 77.2));
        foodList.add(new Food("Cornmeal", 150, 340, 8, 4.5, 66.9));
        foodList.add(new Food("Potatoes", 150, 76, 2, 0.2, 16.5));
        foodList.add(new Food("Soybeans", 100, 359, 35, 16, 18.7));
        foodList.add(new Food("Carrots", 200, 20, 1, 0.1, 3.8));
        foodList.add(new Food("Radishes", 200, 20, 0.8, 0, 4.1));
        foodList.add(new Food("Green Beans", 200, 30, 2.5, 0.2, 4.6));
        foodList.add(new Food("Eggplant", 200, 21, 1.1, 0.2, 3.6));
        foodList.add(new Food("Tomatoes", 200, 19, 0.9, 0.2, 3.5));
        foodList.add(new Food("Peppers", 200, 212, 15, 12, 11));
        foodList.add(new Food("Cucumbers", 200, 15, 0.8, 0.2, 2.4));
        foodList.add(new Food("Pumpkin", 200, 22, 0.7, 0.1, 4.5));
        foodList.add(new Food("Garlic", 200, 126, 4.5, 0.2, 26.5));
        foodList.add(new Food("Onions", 200, 39, 1.1, 0.2, 8.1));
        foodList.add(new Food("Cabbage", 200, 22, 1.5, 0.2, 3.6));
        foodList.add(new Food("Broccoli", 200, 33, 4.1, 0.6, 2.7));
        foodList.add(new Food("Spinach", 200, 24, 2.6, 0.3, 2.8));
        foodList.add(new Food("Lettuce", 200, 13, 1.3, 0.3, 1.3));
        foodList.add(new Food("Mushrooms", 70, 19, 2.2, 0.3, 1.9));
        foodList.add(new Food("Grapes", 150, 43, 0.5, 0.2, 9.9));
        foodList.add(new Food("Strawberries", 150, 30, 1, 0.2, 6));
        foodList.add(new Food("Pineapple", 150, 41, 0.5, 0.1, 9.5));
        foodList.add(new Food("Watermelon", 150, 25, 0.6, 0.1, 5.5));
        foodList.add(new Food("Peanuts", 30, 589, 21.7, 48, 17.5));
        foodList.add(new Food("Pork", 125, 395, 13.2, 37, 2.4));
        foodList.add(new Food("Bacon", 125, 181, 22.3, 9, 2.6));
        foodList.add(new Food("Sausage", 125, 508, 24.1, 40.7, 11.2));
        foodList.add(new Food("Beef", 125, 125, 19.9, 4.2, 2));
        foodList.add(new Food("Lamb", 125, 203, 19, 14.1, 0));
        foodList.add(new Food("Chicken", 125, 167, 19.3, 9.4, 1.3));
        foodList.add(new Food("Milk", 240, 54, 3, 3.4, 3.2));
        foodList.add(new Food("Yogurt", 200, 72, 2.5, 2.7, 9.3));
        foodList.add(new Food("Butter", 5, 888, 1.4, 98, 0));
        foodList.add(new Food("Eggs", 125, 144, 13.3, 8.8, 2.8));
        foodList.add(new Food("Shrimp", 125, 87, 16.4, 2.4, 0));
        foodList.add(new Food("Fish", 125, 113, 16.6, 5.2, 0));
        foodList.add(new Food("Cake", 30, 347, 8.6, 5.1, 66.7));
        foodList.add(new Food("Bread", 60, 312, 8.3, 5.1, 58.1));
        foodList.add(new Food("Biscuits", 40, 433, 9, 12.7, 70.6));
        foodList.add(new Food("Cola", 240, 45, 0, 0, 11.2));
        foodList.add(new Food("Juice", 240, 30, 0.1, 0, 5.1));
        foodList.add(new Food("Tea", 240, 294, 26.7, 1.1, 44.4));
        foodList.add(new Food("Beer", 335, 16, 0.4, 0, 3.7));
        foodList.add(new Food("Sugar", 25, 400, 0, 0, 99.9));
        foodList.add(new Food("Chocolate", 20, 586, 4.3, 40.1, 51.9));
        foodList.add(new Food("Oil", 15, 899, 0, 99.9, 0));
        foodList.add(new Food("Soy Sauce", 15, 63, 5.6, 0.1, 9.9));
        foodList.add(new Food("Vinegar", 25, 31, 2.1, 0.3, 4.9));
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    // 添加新的食物到列表中
    public void addFood(Food food) {
        foodList.add(food);
    }
}
