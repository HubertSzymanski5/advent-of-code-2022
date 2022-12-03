package pl.szymhu.day03;

import lombok.AllArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class RucksackReorganizer {

    private List<Rucksack> rucksacks;

    public int getPrioritiesSum() {
        return getPrioritiesValues().stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    List<Integer> getPrioritiesValues() {
        return rucksacks.stream()
                .map(Rucksack::getDuplicatedType)
                .map(RucksackReorganizer::priorityOf)
                .toList();
    }

    private static final int UPPERCASE_CHAR_SHIFT = 64 - 26;
    private static final int LOWERCASE_CHAR_SHIFT = 96;

    private static int priorityOf(char c) {

        return isUppercase(c)
                ? c - UPPERCASE_CHAR_SHIFT
                : c - LOWERCASE_CHAR_SHIFT;
    }

    private static boolean isUppercase(Character c) {
        return c.toString().equals(c.toString().toUpperCase());
    }

    public static RucksackReorganizer initialize(List<String> rucksacks) {
        List<Rucksack> rucksacksList = rucksacks.stream()
                .map(Rucksack::initialize)
                .toList();
        return new RucksackReorganizer(rucksacksList);
    }
}
