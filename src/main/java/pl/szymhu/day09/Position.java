package pl.szymhu.day09;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Position {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
