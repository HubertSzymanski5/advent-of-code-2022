package pl.szymhu.day13;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PairPacket {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Object> left;
    private List<Object> right;

    public boolean isInOrder() {
        return compare(left, right) <= 0;
    }

    private int compare(List<Object> first, List<Object> second) {
        for (int i = 0; i < first.size() && i < second.size(); i++) {
            Object e1 = first.get(i);
            Object e2 = second.get(i);
            if (e1 instanceof Integer && e2 instanceof Integer) {
                if ((int) e1 != (int) e2) return (int) e1 - (int) e2;
            } else if (e1 instanceof List<?> && e2 instanceof List<?>) {
                int compareListRes = compare((List) e1, (List) e2);
                if (compareListRes != 0) return compareListRes;
            } else {
                e1 = e1 instanceof Integer ? List.of(e1) : e1;
                e2 = e2 instanceof Integer ? List.of(e2) : e2;
                int compareListRes = compare((List) e1, (List) e2);
                if (compareListRes != 0) return compareListRes;
            }
        }
        if (first.size() == second.size()) return 0;
        return first.size() - second.size();
    }

    public static PairPacket from(List<String> packets) {
        try {
            return new PairPacket(objectMapper.readValue(packets.get(0), new TypeReference<>() {
            }), objectMapper.readValue(packets.get(1), new TypeReference<>() {
            }));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse packets");
        }
    }

}
