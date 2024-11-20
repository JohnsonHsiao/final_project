package edu.meu.mgen.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExerciseData {
    private List<Exercise> exerciseList;

    public ExerciseData() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Jump Rope", 12.0));
        exerciseList.add(new Exercise("Climbing", 10.0)); 
        exerciseList.add(new Exercise("Extreme Skateboarding", 10.0));
        exerciseList.add(new Exercise("Running", 10.0));
        exerciseList.add(new Exercise("Dancing", 9.0));  
        exerciseList.add(new Exercise("Jump Training", 9.0));  
        exerciseList.add(new Exercise("Sprinting", 9.0));  
        exerciseList.add(new Exercise("Skiing", 9.0));  
        exerciseList.add(new Exercise("Boxing Training", 9.0));  
        exerciseList.add(new Exercise("Rowing", 8.0));  
        exerciseList.add(new Exercise("Swimming", 8.0));
        exerciseList.add(new Exercise("Basketball", 8.0));  
        exerciseList.add(new Exercise("Tennis", 8.0));  
        exerciseList.add(new Exercise("Badminton", 8.0));  
        exerciseList.add(new Exercise("Squash", 8.0));  
        exerciseList.add(new Exercise("Volleyball", 8.0));  
        exerciseList.add(new Exercise("Soccer", 8.0)); 
        exerciseList.add(new Exercise("Elliptical Machine", 8.0));
        exerciseList.add(new Exercise("Boxing Aerobics", 8.0));
        exerciseList.add(new Exercise("Beach Volleyball", 8.0));
        exerciseList.add(new Exercise("Cycling", 7.5));
        exerciseList.add(new Exercise("Jogging", 7.0)); 
        exerciseList.add(new Exercise("High-Intensity Walking", 7.0));
        exerciseList.add(new Exercise("Aerobics", 7.0));  
        exerciseList.add(new Exercise("Dance Aerobics", 7.0));  
        exerciseList.add(new Exercise("Hiking", 7.0));  
        exerciseList.add(new Exercise("Kayaking", 7.0));
        exerciseList.add(new Exercise("Martial Arts Training", 6.0)); 
        exerciseList.add(new Exercise("Dance Machine", 6.0));
        exerciseList.add(new Exercise("Skating", 6.0)); 
        exerciseList.add(new Exercise("Aerobics", 6.0));
        exerciseList.add(new Exercise("Golf", 5.0));  
        exerciseList.add(new Exercise("Billiards", 5.0));  
        exerciseList.add(new Exercise("Strength Training", 5.0));  
        exerciseList.add(new Exercise("Judo", 5.0));  
        exerciseList.add(new Exercise("Stretching Exercises", 4.0));  
        exerciseList.add(new Exercise("Stationary Biking", 4.0));  
        exerciseList.add(new Exercise("Casual Walking", 4.0));  
        exerciseList.add(new Exercise("Yoga", 3.0));  
        exerciseList.add(new Exercise("Table Tennis", 3.0));  
        exerciseList.add(new Exercise("Bowling", 3.0));  
        exerciseList.add(new Exercise("Rowing (light intensity)", 3.0));  
        exerciseList.add(new Exercise("Skiing (casual)", 3.0));  
        exerciseList.add(new Exercise("Casual Cycling", 3.0));  
        exerciseList.add(new Exercise("Casual Dancing", 3.0));  
        exerciseList.add(new Exercise("Horse Riding", 2.0));  
        exerciseList.add(new Exercise("Health Walking", 2.0));  
        exerciseList.add(new Exercise("Climbing Stairs", 2.0));
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
