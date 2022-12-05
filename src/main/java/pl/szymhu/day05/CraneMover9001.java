package pl.szymhu.day05;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CraneMover9001 extends Crane {

    public CraneMover9001(List<CraneCommand> commands, SupplyStacks supplyStacks) {
        super(commands, supplyStacks);
    }

    void execute(CraneCommand command) {
        var fromStack = super.getSupplyStacks().getStacks().get(command.fromStack() - 1);
        var toStack = super.getSupplyStacks().getStacks().get(command.toStack() - 1);
        Deque<Character> moving = new LinkedList<>();
        for (int i = 0; i < command.stacksToMove(); i++) {
            moving.addFirst(fromStack.removeLast());
        }
        toStack.addAll(moving);
    }

    public static Crane initialize(List<String> commands, String stacks) {
        List<CraneCommand> parsedCommands = commands.stream()
                .map(CraneCommand::from)
                .toList();
        return new CraneMover9001(parsedCommands, SupplyStacks.initialize(stacks));
    }
}
