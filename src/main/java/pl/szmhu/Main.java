package pl.szmhu;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        runAll();
    }

    public static void runAll() {
        Arrays.stream(Main.class.getMethods())
                .filter(method -> method.getName().startsWith("day"))
                .forEach(method -> {
                    try {
                        method.invoke(new Object());
                    } catch (Exception e) {
                        System.err.println("Method '" + method.getName() + "' failed to execute");
                        e.printStackTrace();
                    }
                });
    }
    public static void runDay(String numberStr) {
        try {
            var dayMethod = Main.class.getMethod("day" + numberStr);
            dayMethod.invoke(new Object());
        } catch (Exception e) {
            System.err.println("Method doesn't exist or failed to execute");
            e.printStackTrace();
        }
    }
}