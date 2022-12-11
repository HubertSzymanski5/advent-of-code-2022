package pl.szymhu;

import pl.szymhu.day01.CalorieCounter;
import pl.szymhu.day02.GamePicks;
import pl.szymhu.day02.OutcomeStrategy;
import pl.szymhu.day02.PickStrategy;
import pl.szymhu.day02.RockPaperScissorsGame;
import pl.szymhu.day03.RucksackReorganizer;
import pl.szymhu.day04.CampCleaner;
import pl.szymhu.day05.Crane;
import pl.szymhu.day05.CraneMover9000;
import pl.szymhu.day05.CraneMover9001;
import pl.szymhu.day05.SupplyStacks;
import pl.szymhu.day06.CommunicationSystemSignalProcessor;
import pl.szymhu.day07.FileSystem;
import pl.szymhu.day08.Forrest;
import pl.szymhu.day09.RopeBridge;
import pl.szymhu.day10.Cpu;
import pl.szymhu.day10.Crt;
import pl.szymhu.day11.MonkeyGang;
import pl.szymhu.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static pl.szymhu.utils.InputReader.NEW_LINE;

public class Main {
    public static void main(String[] args) {
        runDay("11");
    }

    public static void day11() throws IOException {
        List<String> monkeys = InputReader.readEmptyLineSeparatedStringList("input.txt", MonkeyGang.class);
        MonkeyGang monkeyGang = MonkeyGang.initialize(monkeys);
        System.out.println("Day11 I: " + monkeyGang.findLevelOfMonkeyBusiness());
    }

    public static void day10() throws IOException {
        List<String> commands = InputReader.readStringList("input.txt", Cpu.class);
        Cpu cpu = Cpu.init(commands);
        System.out.println("Day10 I: " + cpu.getSumOfSignalStrengths());
        Crt crt = Crt.init(commands);
        System.out.println("Day10 II: " + crt.generate());
    }

    public static void day09() throws IOException {
        List<String> inputs = InputReader.readStringList("input.txt", RopeBridge.class);
        RopeBridge ropeBridge = RopeBridge.initialize(inputs, 2);
        ropeBridge.executeCommands();
        System.out.println("Day09 I: " + ropeBridge.getUniqueTailPositionsNum());
        ropeBridge = RopeBridge.initialize(inputs, 10);
        ropeBridge.executeCommands();
        System.out.println("Day09 II: " + ropeBridge.getUniqueTailPositionsNum());
    }

    public static void day08() throws IOException {
        String input = InputReader.fileToString("input.txt", Forrest.class);
        Forrest forrest = Forrest.initialize(input);
        System.out.println("Day08 I: " + forrest.countVisibleTrees());
        System.out.println("Day08 II: " + forrest.findBestTreeScenicScore());
    }

    public static void day07() throws IOException {
        List<String> input = InputReader.readStringList("input.txt", FileSystem.class);
        FileSystem fileSystem = FileSystem.initialize(input);
        System.out.println("Day07 I: " + fileSystem.getDirectoriesSumSizeWithSizeLessThan(100_000));
        System.out.println("Day07 II: " + fileSystem.getDirectorySizeToDelete());
    }

    public static void day06() throws IOException {
        String input = InputReader.fileToString("input.txt", CommunicationSystemSignalProcessor.class);
        System.out.println("Day06 I: " + CommunicationSystemSignalProcessor.getPacketStartIndex(input));
        System.out.println("Day06 II: " + CommunicationSystemSignalProcessor.getMessageStartIndex(input));
    }

    public static void day05() throws IOException {
        List<String> input = InputReader.readEmptyLineSeparatedStringList("input.txt", SupplyStacks.class);
        List<String> commands = Arrays.stream(input.get(1).split(NEW_LINE)).toList();
        Crane crane = CraneMover9000.initialize(commands, input.get(0));
        crane.executeCommands();
        System.out.println("Day05 I: " + crane.getSupplyStacks().getCratesFromTheTopString());
        crane = CraneMover9001.initialize(commands, input.get(0));
        crane.executeCommands();
        System.out.println("Day05 II: " + crane.getSupplyStacks().getCratesFromTheTopString());

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