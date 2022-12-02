package pl.szymhu.day02;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Outcome {
    WIN(6),
    DRAW(3),
    LOSE(0);

    @Getter
    private final int points;
}
