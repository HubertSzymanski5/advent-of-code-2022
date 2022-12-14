package pl.szymhu.day13;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.szymhu.utils.InputReader.NEW_LINE;

@AllArgsConstructor
public class DistressSignal {

    private List<PairPacket> packets;

    public int getSumOfRightOrderedIndexes() {
        return getIndexesOfPairsInRightOrder().stream().reduce(Integer::sum).orElse(0);
    }

    List<Integer> getIndexesOfPairsInRightOrder() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < packets.size(); i++) {
            if (packets.get(i).isInOrder()) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static DistressSignal initialize(List<String> pairs) {
        List<PairPacket> packets = pairs.stream().map(DistressSignal::toStringPair).map(PairPacket::from).toList();
        return new DistressSignal(packets);
    }

    private static List<String> toStringPair(String input) {
        return Arrays.stream(input.split(NEW_LINE)).toList();
    }
}
