package pl.szymhu.day10;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Crt {

    private int regX = 1;
    private int clockCount = 0;
    private int commandIndex = 0;
    private boolean waitOnAdd = false;
    private final StringBuilder sb = new StringBuilder();
    @NonNull
    private List<CpuCommand> commands;

    public String generate() {
        for (int i = 0; i < 240; i++) {
            cycleOnce();
        }
        return sb.toString();
    }

    void cycleOnce() {
        if (clockCount % 40 == 0) {
            sb.append("\n");
        }
        if (Math.abs(regX - clockCount%40) <= 1) {
            sb.append("#");
        } else {
            sb.append(".");
        }
        clockCount++;
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

    public static Crt init(List<String> stringCommands) {
        List<CpuCommand> commands = stringCommands.stream()
                .map(CpuCommand::from)
                .toList();
        return new Crt(commands);
    }
}
