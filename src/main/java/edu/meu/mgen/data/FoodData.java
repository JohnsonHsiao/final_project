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
        foodList.add(new Food("Chicken Breast", 100, 165, 31, 3.6, 0));
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    // 添加新的食物到列表中
    public void addFood(Food food) {
        foodList.add(food);
    }
}
