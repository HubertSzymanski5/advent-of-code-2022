package pl.szymhu.day09;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@RequiredArgsConstructor
public class RopeBridge {

    @NonNull
    private List<Command> commands;

    private final List<Position> ropeElements = new ArrayList<>();

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
            case U -> ropeElements.set(0, new Position(ropeElements.get(0).getX(), ropeElements.get(0).getY() + 1));
            case D -> ropeElements.set(0, new Position(ropeElements.get(0).getX(), ropeElements.get(0).getY() - 1));
            case L -> ropeElements.set(0, new Position(ropeElements.get(0).getX() - 1, ropeElements.get(0).getY()));
            case R -> ropeElements.set(0, new Position(ropeElements.get(0).getX() + 1, ropeElements.get(0).getY()));
        }
    }

    private void moveTail() {
        for (int i = 1; i < ropeElements.size(); i++) {
            int dx = ropeElements.get(i - 1).getX() - ropeElements.get(i).getX();
            int dy = ropeElements.get(i - 1).getY() - ropeElements.get(i).getY();
            if (abs(dx) > 1 || abs(dy) > 1) {
                int newX = dx != 0 ? ropeElements.get(i).getX() + dx / abs(dx) : ropeElements.get(i).getX();
                int newY = dy != 0 ? ropeElements.get(i).getY() + dy / abs(dy) : ropeElements.get(i).getY();
                ropeElements.set(i, new Position(newX, newY));
            }
        }
        tailHistory.add(ropeElements.get(ropeElements.size() - 1));
    }

    public static RopeBridge initialize(List<String> commands, int ropeLength) {
        List<Command> commandList = commands.stream()
                .map(Command::from)
                .toList();
        RopeBridge result = new RopeBridge(commandList);
        for (int i = 0; i < ropeLength; i++) {
            result.ropeElements.add(new Position(0, 0));
        }
        return result;
    }
}

