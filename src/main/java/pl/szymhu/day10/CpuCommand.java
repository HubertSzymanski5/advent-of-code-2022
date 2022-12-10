package pl.szymhu.day10;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static pl.szymhu.day10.CmdType.ADDX;

@AllArgsConstructor
@Getter
class CpuCommand {
    private CmdType cmdType;
    private int value;

    public static CpuCommand from(String str) {
        String[] parts = str.split(" ");
        CmdType type = CmdType.valueOf(parts[0].toUpperCase());
        return type == ADDX ? new CpuCommand(type, Integer.parseInt(parts[1])) : new CpuCommand(type, 0);
    }
}

enum CmdType {
    ADDX, NOOP
}
