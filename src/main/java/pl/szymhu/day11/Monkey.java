package pl.szymhu.day11;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;

import static pl.szymhu.utils.InputReader.NEW_LINE;

@Builder(access = AccessLevel.PRIVATE)
@Getter
class Monkey {

    private List<Integer> items;
    private Function<Integer, Integer> operation;
    private int testNumber;
    private Map<Boolean, Integer> throwToMapping;

    @Builder.Default
    private int inspectionCount = 0;

    public List<MonkeyThrowResult> processItems() {
        var result = items.stream()
                .map(this::processItem)
                .toList();
        items.clear();
        return result;
    }

    public void catchItem(int item) {
        items.add(item);
    }

    public void catchItems(Collection<? extends Integer> collection) {
        items.addAll(collection);
    }

    private MonkeyThrowResult processItem(int item) {
        int inspectedItem = inspect(item) / 3;
        int destination = throwToMapping.get(test(inspectedItem));
        return new MonkeyThrowResult(destination, inspectedItem);
    }

    private int inspect(int item) {
        inspectionCount++;
        return operation.apply(item);
    }

    private boolean test(int item) {
        return item % testNumber == 0;
    }

    public static Monkey from(String str) {
        String[] inputLines = str.split(NEW_LINE);

        return Monkey.builder()
                .items(new ArrayList<>(parseItems(inputLines)))
                .operation(parseOperation(inputLines))
                .testNumber(parseTestNumber(inputLines))
                .throwToMapping(parseThrowToMapping(inputLines))
                .build();
    }

    private static List<Integer> parseItems(String[] inputLines) {
        return Arrays.stream(inputLines[1].split(":")[1].trim().split(", "))
                .map(Integer::parseInt)
                .toList();
    }

    private static Function<Integer, Integer> parseOperation(String[] inputLines) {
        var operationInput = inputLines[2].split("=")[1].trim();
        if (operationInput.contains("*")) {
            String second = operationInput.split("\\*")[1].trim();
            if ("old".equals(second)) {
                return (old) -> old * old;
            } else {
                int secondInt = Integer.parseInt(second);
                return (old) -> old * secondInt;
            }
        }
        int secondInt = Integer.parseInt(operationInput.split("\\+")[1].trim());
        return (old) -> old + secondInt;
    }

    private static int parseTestNumber(String[] inputLines) {
        var testInput = inputLines[3].trim().split(" ");
        return Integer.parseInt(testInput[testInput.length - 1]);
    }

    private static Map<Boolean, Integer> parseThrowToMapping(String[] inputLines) {
        var trueResult = inputLines[4].substring(29);
        var falseResult = inputLines[5].substring(30);
        return Map.of(
                true, Integer.parseInt(trueResult),
                false, Integer.parseInt(falseResult));
    }
}

