package pl.szymhu.day03;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class Rucksack {

    @Getter
    private List<Character> items;

    public char getDuplicatedType() {
        Set<Character> firstCompartment = new HashSet<>(getFirstCompartment());
        return getSecondCompartment().stream()
                .filter(firstCompartment::contains)
                .findFirst()
                .orElseThrow();
    }

    List<Character> getFirstCompartment() {
        return items.stream()
                .limit(items.size() / 2)
                .toList();
    }

    List<Character> getSecondCompartment() {
        return items.stream()
                .skip(items.size() / 2)
                .toList();
    }

    public static Rucksack initialize(String items) {
        List<Character> itemsList = items.chars()
                .mapToObj(s -> (char) s)
                .toList();
        return new Rucksack(itemsList);
    }
}
