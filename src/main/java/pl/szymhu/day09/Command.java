package pl.szymhu.day09;

public record Command(Direction direction, int steps) {
    public static Command from(String command) {
        String[] commandParts = command.split(" ");
        return new Command(Direction.valueOf(commandParts[0]), Integer.parseInt(commandParts[1]));
    }
}
