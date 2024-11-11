package edu.meu.mgen.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExerciseData {
    private List<Exercise> exerciseList;

    public ExerciseData() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Running", 30, "medium")); // 中等強度的跑步，30分鐘
        exerciseList.add(new Exercise("Cycling", 45, "low")); // 低強度的騎車，45分鐘
        exerciseList.add(new Exercise("Swimming", 60, "high")); // 高強度的游泳，60分鐘
        // 可以添加更多運動...
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    // 添加新的運動到列表中
    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }
}

