package pl.szymhu;

import pl.szymhu.day01.CalorieCounter;
import pl.szymhu.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        runDay("01");
    }

    public static void runAll() {
        Arrays.stream(Main.class.getMethods())
                .filter(method -> method.getName().startsWith("day"))
                .forEach(method -> {
                    try {
                        method.invoke(new Object());
                    } catch (Exception e) {
                        System.err.println("Method '" + method.getName() + "' failed to execute");
                        e.printStackTrace();
                    }
                });
    }
    public static void runDay(String numberStr) {
        try {
            var dayMethod = Main.class.getMethod("day" + numberStr);
            dayMethod.invoke(new Object());
        } catch (Exception e) {
            System.err.println("Method doesn't exist or failed to execute");
            e.printStackTrace();
        }
    }

    public static void day01() throws IOException {
        List<String> input = InputReader.readEmptyLineSeparatedStringList("input.txt", CalorieCounter.class);
        CalorieCounter calorieCounter = CalorieCounter.from(input);
        System.out.println("Day01 I: " + calorieCounter.findMostCalories());
        System.out.println("Day01 I: " + calorieCounter.findSumOfMost3Calories());
    }
}