package pl.szymhu.day13;

import lombok.AllArgsConstructor;

import java.util.*;

import static pl.szymhu.utils.InputReader.NEW_LINE;

@AllArgsConstructor
public class DistressSignal {

    private static final PairPacket DIVIDER_PAIR = PairPacket.from(List.of("[[2]]", "[[6]]"));

    private List<PairPacket> packets;

    public int getSumOfRightOrderedIndexes() {
        return getIndexesOfPairsInRightOrder().stream().reduce(Integer::sum).orElse(0);
    }

    public int getDecoderKey() {
        List<List<Object>> sortedPackets = getSortedPackages();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < sortedPackets.size(); i++) {
            List<Object> currPackage = sortedPackets.get(i);
            if (currPackage == DIVIDER_PAIR.getLeft() || currPackage == DIVIDER_PAIR.getRight()) {
                indexes.add(i + 1);
            }
        }
        return indexes.get(0) * indexes.get(1);
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

    List<List<Object>> getSortedPackages() {
        List<PairPacket> packetsWithDividers = new ArrayList<>(packets);
        packetsWithDividers.add(DIVIDER_PAIR);
        return packetsWithDividers.stream()
                .map(p -> List.of(p.getLeft(), p.getRight()))
                .flatMap(Collection::stream)
                .sorted(PairPacket::compare)
                .toList();
    }

    public static DistressSignal initialize(List<String> pairs) {
        List<PairPacket> packets = pairs.stream().map(DistressSignal::toStringPair).map(PairPacket::from).toList();
        return new DistressSignal(packets);
    }

    private static List<String> toStringPair(String input) {
        return Arrays.stream(input.split(NEW_LINE)).toList();
    }
}
