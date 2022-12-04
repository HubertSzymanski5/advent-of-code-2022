package pl.szymhu.day04;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class CampCleaner {

    @Getter
    private List<ElvesPair> pairs;

    public long getSumOfFullyContainedInTheOther() {
        return pairs.stream()
                .filter(ElvesPair::isOneFullyContainedByTheOther)
                .count();
    }

    public static CampCleaner initialize(List<String> pairs) {
        List<ElvesPair> elvesPairs = pairs.stream()
                .map(ElvesPair::from)
                .toList();
        return new CampCleaner(elvesPairs);
    }


}

