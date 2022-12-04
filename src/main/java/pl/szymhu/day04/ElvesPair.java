package pl.szymhu.day04;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class ElvesPair {
    private Sector first;
    private Sector second;

    public boolean isOneFullyContainedByTheOther() {
        return first.contains(second) || second.contains(first);
    }

    public boolean areOverlapping() {
        return first.overlaps(second) || second.overlaps(first);
    }

    public static ElvesPair from(String elvesPair) {
        List<Sector> sectors = Arrays.stream(elvesPair.split(","))
                .map(Sector::from)
                .toList();
        return new ElvesPair(sectors.get(0), sectors.get(1));
    }
}
