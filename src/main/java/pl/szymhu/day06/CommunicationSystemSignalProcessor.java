package pl.szymhu.day06;


import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

public class CommunicationSystemSignalProcessor {

    public static int getPacketStartIndex(String signal) {
        Deque<Character> markerBox = initializeMarkerBox(signal, 3);
        return findFirstDistinctiveSignal(signal, markerBox, 3);
    }

    public static int getMessageStartIndex(String signal) {
        Deque<Character> markerBox = initializeMarkerBox(signal, 13);
        return findFirstDistinctiveSignal(signal, markerBox, 13);
    }

    private static Deque<Character> initializeMarkerBox(String signal, int n) {
        Deque<Character> markerBox = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            markerBox.addLast(signal.charAt(i));
        }
        return markerBox;
    }

    private static int findFirstDistinctiveSignal(String signal, Deque<Character> markerBox, int startIndex) {
        for (int i = startIndex; i < signal.length(); i++) {
            markerBox.addLast(signal.charAt(i));
            if (hasDistinctElements(markerBox)) {
                return i + 1;
            }
            markerBox.removeFirst();
        }
        return -1;
    }

    private static boolean hasDistinctElements(Collection<?> collection) {
        return collection.stream().distinct().count() == collection.size();
    }
}
