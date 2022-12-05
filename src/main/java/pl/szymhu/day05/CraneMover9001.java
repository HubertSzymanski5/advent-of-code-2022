package pl.szymhu.day05;

import lombok.RequiredArgsConstructor;

import java.util.List;

public class CraneMover9001 extends Crane {

    public CraneMover9001(List<CraneCommand> commands, SupplyStacks supplyStacks) {
        super(commands, supplyStacks);
    }

    void execute(CraneCommand command) {

    }
}
