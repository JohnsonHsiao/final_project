package edu.meu.mgen.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExerciseData {
    private List<Exercise> exerciseList;

    public ExerciseData() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Running", 10.0));
        exerciseList.add(new Exercise("Swimming", 8.0));
        exerciseList.add(new Exercise("Cycling", 7.5));
        exerciseList.add(new Exercise("Walking", 3.5));
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public Exercise getExerciseByName(String name) {
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().equalsIgnoreCase(name)) {
                return exercise;
            }
        }
        return null;
    }
}
