package pl.szymhu.day11;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;


@Getter
@AllArgsConstructor
public class MonkeyGang {

    private List<Monkey> monkeys;

    public int findLevelOfMonkeyBusiness() {
        for (int i = 0; i < 20; i++) {
            nextRound();
        }
        return monkeys.stream()
                .map(Monkey::getInspectionCount)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((product, inspects) -> product * inspects)
                .orElse(0);
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
        int item = result.item();
        monkeys.get(toMonkey).catchItem(item);
    }

    public static MonkeyGang initialize(List<String> monkeysDetails) {
        var monkeys = monkeysDetails.stream()
                .map(Monkey::from)
                .toList();
        return new MonkeyGang(monkeys);
    }
}
