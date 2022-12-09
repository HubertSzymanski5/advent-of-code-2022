package pl.szymhu.day09;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@RequiredArgsConstructor
public class RopeBridge {

    @NonNull
    private List<Command> commands;

    private Position head = new Position(0, 0);

    private Position tail = new Position(0, 0);

    private final List<Position> tailHistory = new ArrayList<>();

    public void executeCommands() {
        commands.forEach(this::executeCommand);
    }

    public long getUniqueTailPositionsNum() {
        return tailHistory.stream()
                .distinct()
                .count();
    }

    private void executeCommand(Command command) {

        for (int i = 0; i < command.steps(); i++) {
            moveHead(command);
            moveTail();
        }
    }

    private void moveHead(Command command) {
        switch (command.direction()) {
            case U -> head = new Position(head.getX(), head.getY() + 1);
            case D -> head = new Position(head.getX(), head.getY() - 1);
            case L -> head = new Position(head.getX() - 1, head.getY());
            case R -> head = new Position(head.getX() + 1, head.getY());
        }
    }

    private void moveTail() {
        int dx = head.getX() - tail.getX();
        int dy = head.getY() - tail.getY();
        if (abs(dx) > 1 || abs(dy) > 1) {
            int newX = dx != 0 ? tail.getX() + dx / abs(dx) : tail.getX();
            int newY = dy != 0 ? tail.getY() + dy / abs(dy) : tail.getY();
            tail = new Position(newX, newY);
        }
        tailHistory.add(tail);
    }

    public static RopeBridge initialize(List<String> commands) {
        List<Command> commandList = commands.stream()
                .map(Command::from)
                .toList();
        return new RopeBridge(commandList);
    }
}

