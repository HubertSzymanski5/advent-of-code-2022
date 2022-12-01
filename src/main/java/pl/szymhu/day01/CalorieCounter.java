package pl.szymhu.day01;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static pl.szymhu.utils.InputReader.NEW_LINE;

@AllArgsConstructor(access = PRIVATE)
public class CalorieCounter {

    private List<List<Integer>> caloriesByElf;

    public int findMostCalories() {
        return caloriesByElf.stream()
                .map(this::sumCaloriesInBags)
                .reduce(Integer::max)
                .orElse(0);
    }

    private int sumCaloriesInBags(List<Integer> bag) {
        return bag.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    public static CalorieCounter from(List<String> elvesBagsList) {
        var bags = elvesBagsList.stream()
                .map(CalorieCounter::caloriesInBag)
                .toList();
        return new CalorieCounter(bags);
    }

    private static List<Integer> caloriesInBag(String bag) {
        return Arrays.stream(bag.split(NEW_LINE))
                .map(Integer::parseInt)
                .toList();
    }
}
