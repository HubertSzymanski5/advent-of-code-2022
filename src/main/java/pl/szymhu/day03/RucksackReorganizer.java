package pl.szymhu.day03;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class RucksackReorganizer {

    private List<Rucksack> rucksacks;

    public int getPrioritiesSum() {
        return getPrioritiesValues().stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int getBadgesPrioritiesSum() {
        return getElvesGroups().stream()
                .map(RucksackReorganizer::getBadgeFor)
                .map(RucksackReorganizer::priorityOf)
                .reduce(Integer::sum)
                .orElse(0);
    }

    static char getBadgeFor(List<Rucksack> group) {
        if (group.size() != 3) {
            throw new RuntimeException("Group size must contain 3 elves!");
        }
        Set<Character> firstSet = new HashSet<>(group.get(0).getItems());
        Set<Character> secondSet = new HashSet<>(group.get(1).getItems());
        return group.get(2).getItems().stream()
                .distinct()
                .filter(firstSet::contains)
                .filter(secondSet::contains)
                .findFirst()
                .orElseThrow();
    }
    
    private List<List<Rucksack>> getElvesGroups() {
        List<List<Rucksack>> groups = new ArrayList<>();
        for (var i = 0; i < rucksacks.size(); i++) {
            int groupIndex = i / 3;
            if (i % 3 == 0) {
                groups.add(new ArrayList<>(3));
            }
            groups.get(groupIndex).add(rucksacks.get(i));
        }
        return groups;
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
