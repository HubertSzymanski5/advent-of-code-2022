package pl.szymhu.day10;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Cpu {

    private int regX = 1;
    @NonNull
    private List<CpuCommand> commands;
    private int commandIndex = 0;
    private int clockCount = 0;
    private boolean waitOnAdd = false;

    private final List<Integer> signalStrengths = new ArrayList<>();

    public int getSumOfSignalStrengths() {
        cycleTimes(220);
        return signalStrengths.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    void cycleTimes(int n) {
        for (int i = 0; i < n; i++) {
            cycleOnce();
        }
    }

    void cycleOnce() {
        clockCount++;
        if ((clockCount - 20) % 40 == 0) {
            signalStrengths.add(regX * clockCount);
        }
        CpuCommand command = commands.get(commandIndex);
        switch (command.getCmdType()) {
            case NOOP -> {
                commandIndex++;
            }
            case ADDX -> {
                if (waitOnAdd) {
                    commandIndex++;
                    waitOnAdd = false;
                    regX += command.getValue();
                } else {
                    waitOnAdd = true;
                }
            }
        }
    }

    public static Cpu init(List<String> stringCommands) {
        List<CpuCommand> commands = stringCommands.stream()
                .map(CpuCommand::from)
                .toList();
        return new Cpu(commands);
    }

}
