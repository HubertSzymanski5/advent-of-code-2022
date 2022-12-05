package pl.szymhu.day05;

import java.util.List;

public class CraneMover9000 extends Crane {

    public CraneMover9000(List<CraneCommand> commands, SupplyStacks supplyStacks) {
        super(commands, supplyStacks);
    }

    @Override
    void execute(CraneCommand command) {
        for (int i = 0; i < command.stacksToMove(); i++) {
            executeMove(command.fromStack(), command.toStack());
        }
    }

    private void executeMove(int from, int to) {
        var stacks = super.getSupplyStacks().getStacks();
        var fromStack = stacks.get(from - 1);
        var toStack = stacks.get(to - 1);
        var el = fromStack.removeLast();
        toStack.add(el);
    }

    public static Crane initialize(List<String> commands, String stacks) {
        List<CraneCommand> parsedCommands = commands.stream()
                .map(CraneCommand::from)
                .toList();
        return new CraneMover9000(parsedCommands, SupplyStacks.initialize(stacks));
    }
}
