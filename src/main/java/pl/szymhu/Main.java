package pl.szymhu;

import pl.szymhu.day01.CalorieCounter;
import pl.szymhu.day02.GamePicks;
import pl.szymhu.day02.OutcomeStrategy;
import pl.szymhu.day02.PickStrategy;
import pl.szymhu.day02.RockPaperScissorsGame;
import pl.szymhu.day03.RucksackReorganizer;
import pl.szymhu.day04.CampCleaner;
import pl.szymhu.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        runDay("04");
    }

    public static void day04() throws IOException {
        List<String> input = InputReader.readStringList("input.txt", CampCleaner.class);
        CampCleaner campCleaner = CampCleaner.initialize(input);
        System.out.println("Day04 I: " + campCleaner.getSumOfFullyContainedInTheOther());
        System.out.println("Day04 II: " + campCleaner.getSumOfOverlappingSectors());
    }

    public static void day03() throws IOException {
        List<String> input = InputReader.readStringList("input.txt", RucksackReorganizer.class);
        RucksackReorganizer rucksackReorganizer = RucksackReorganizer.initialize(input);
        System.out.println("Day03 I: " + rucksackReorganizer.getPrioritiesSum());
        System.out.println("Day03 II: " + rucksackReorganizer.getBadgesPrioritiesSum());
    }

    public static void day02() throws IOException {
        List<String> input = InputReader.readStringList("input.txt", RockPaperScissorsGame.class);
        List<GamePicks> gamePicksList = input.stream()
                .map(str -> new GamePicks(str.charAt(0), str.charAt(2)))
                .toList();
        RockPaperScissorsGame game = new RockPaperScissorsGame(gamePicksList, new PickStrategy());
        System.out.println("Day02 I: " + game.calculateTotalScore());
        game.setStrategy(new OutcomeStrategy());
        System.out.println("Day02 II: " + game.calculateTotalScore());

    }

    public static void day01() throws IOException {
        List<String> input = InputReader.readEmptyLineSeparatedStringList("input.txt", CalorieCounter.class);
        CalorieCounter calorieCounter = CalorieCounter.from(input);
        System.out.println("Day01 I: " + calorieCounter.findMostCalories());
        System.out.println("Day01 I: " + calorieCounter.findSumOfMost3Calories());
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
}