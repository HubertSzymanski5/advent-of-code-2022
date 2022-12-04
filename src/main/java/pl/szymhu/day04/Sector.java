package pl.szymhu.day04;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class Sector {
    private int start;
    private int end;

    public boolean contains(Sector other) {
        return this.start <= other.start
                && this.end >= other.end;
    }

    public boolean overlaps(Sector other) {
        return this.isWithinSector(other.start) || this.isWithinSector(other.end);
    }

    private boolean isWithinSector(int i) {
        return this.start <= i && i <= this.end;
    }

    public static Sector from(String sector) {
        List<Integer> nums = Arrays.stream(sector.split("-"))
                .map(Integer::parseInt)
                .toList();
        return new Sector(nums.get(0), nums.get(1));
    }
}
