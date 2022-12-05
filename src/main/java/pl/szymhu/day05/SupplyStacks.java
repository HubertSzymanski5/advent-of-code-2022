package pl.szymhu.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collector;

import static pl.szymhu.utils.InputReader.NEW_LINE;

@AllArgsConstructor
@Getter
public class SupplyStacks {

    private List<Deque<Character>> stacks;

    public String getCratesFromTheTopString() {
        return stacks.stream()
                .map(Deque::getLast)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= stacks.size(); i++) {
            sb.append(i).append(": ");
            sb.append(stacks.get(i - 1));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static SupplyStacks initialize(String input) {
        var inputLine = Arrays.stream(input.split(NEW_LINE)).toList();

        final int stacksNum = Arrays.stream(inputLine.get(inputLine.size() - 1).trim().split("   "))
                .map(Integer::parseInt)
                .reduce(Integer::max)
                .orElseThrow();
        List<Deque<Character>> stacks = prepareEmptyStacks(stacksNum);

        populateStacksWithInput(inputLine, stacksNum, stacks);
        return new SupplyStacks(stacks);
    }

    private static void populateStacksWithInput(List<String> inputLine, int stacksNum, List<Deque<Character>> stacks) {
        inputLine.stream()
                .limit(inputLine.size() - 1) // removes stacks numbers
                .forEach(line -> {
                    for (int i = 0; i < stacksNum; i++) {
                        int lineIndex = 1 + 4 * i;
                        if (lineIndex >= line.length()) {
                            return;
                        }
                        var supply = line.charAt(1 + 4 * i);
                        if (supply != ' ') {
                            stacks.get(i).push(supply);
                        }
                    }
                });
    }

    private static List<Deque<Character>> prepareEmptyStacks(int stacksNum) {
        List<Deque<Character>> stacks = new ArrayList<>(stacksNum);
        for (int i = 0; i < stacksNum; i++) {
            stacks.add(new ArrayDeque<>());
        }
        return stacks;
    }
}
