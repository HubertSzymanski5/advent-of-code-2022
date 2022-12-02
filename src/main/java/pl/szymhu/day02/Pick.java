package pl.szymhu.day02;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static pl.szymhu.day02.Outcome.*;

@AllArgsConstructor
public enum Pick {
    ROCK(1), PAPER(2), SCISSORS(3);

    @Getter
    private final int points;

    public Outcome against(Pick second) {
        if (this == second) {
            return DRAW;
        }
        return switch (this) {
            case ROCK -> (second == SCISSORS) ? WIN : LOSE;
            case PAPER -> (second == ROCK) ? WIN : LOSE;
            case SCISSORS -> (second == PAPER) ? WIN : LOSE;
        };
    }
}
