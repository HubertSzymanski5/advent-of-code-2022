package pl.szymhu.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public abstract class Crane {

    private List<CraneCommand> commands;
    private SupplyStacks supplyStacks;

    public void executeCommands() {
        commands.forEach(this::execute);
    }

    abstract void execute(CraneCommand command);
}

record CraneCommand(int stacksToMove, int fromStack, int toStack) {

    @Override
    public String toString() {
        return "MOVE: " + stacksToMove + "\tFROM: " + fromStack + "\tTO: " + toStack;
    }

    public static CraneCommand from(String command) {
        var commandFields = command.split(" ");
        var commandValues = Stream.of(commandFields[1], commandFields[3], commandFields[5])
                .map(Integer::parseInt)
                .toList();
        return new CraneCommand(commandValues.get(0), commandValues.get(1), commandValues.get(2));
    }
}
