package pl.szymhu.day04;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class Sector {
    private int start;
    private int end;

    public boolean contains(Sector other) {
        return this.start <= other.start
                && this.end >= other.end;
    }

    public static Sector from(String sector) {
        List<Integer> nums = Arrays.stream(sector.split("-"))
                .map(Integer::parseInt)
                .toList();
        return new Sector(nums.get(0), nums.get(1));
    }
}
