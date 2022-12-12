package pl.szymhu.day11;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;


@Getter
@AllArgsConstructor
public class MonkeyGang {

    private List<Monkey> monkeys;
    private int lcm;

    public long findLevelOfMonkeyBusinessAfter(int n) {
        for (int i = 0; i < n; i++) {
            nextRound();
        }
        return monkeys.stream()
                .map(Monkey::getInspectionCount)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .map(Long::valueOf)
                .reduce((product, inspects) -> product * inspects)
                .orElse(0L);
    }

    void nextRound() {
        monkeys.stream()
                .map(Monkey::processItems)
                .forEach(this::processMonkeyThrowResults);
    }

    private void processMonkeyThrowResults(List<MonkeyThrowResult> results) {
        results.forEach(this::processMonkeyThrowResult);
    }

    private void processMonkeyThrowResult(MonkeyThrowResult result) {
        int toMonkey = result.destination();
        long item = result.item() % lcm;
        monkeys.get(toMonkey).catchItem(item);
    }

    public static MonkeyGang initialize(List<String> monkeysDetails, boolean bored) {
        var monkeys = monkeysDetails.stream()
                .map(s -> Monkey.from(s, bored))
                .toList();
        int lcm = findLeastCommonMultiplier(monkeys);
        return new MonkeyGang(monkeys, lcm);
    }

    private static int findLeastCommonMultiplier(List<Monkey> monkeys) {
        return monkeys.stream()
                .map(Monkey::getTestNumber)
                .reduce(MonkeyGang::lcm)
                .orElse(1);
    }

    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }
}
