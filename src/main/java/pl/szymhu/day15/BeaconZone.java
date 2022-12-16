package pl.szymhu.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class BeaconZone {

    static final char BEACON = 'B';
    static final char SENSOR = 'S';

    private Map<Point<Long>, Character> map;
    private Map<Point<Long>, Long> sensorsDistances;

    public long findCountOfIllegalBeaconPosInRow(Long row) {
        Set<Point<Long>> excluded = new HashSet<>();
        sensorsDistances.entrySet().stream().filter(entry -> Math.abs(entry.getKey().y() - row) <= entry.getValue()).forEach(entry -> {
            Long distanceToBeacon = entry.getValue();
            Long distToRow = Math.abs(entry.getKey().y() - row);
            for (int i = 0; i <= distanceToBeacon - distToRow; i++) {
                excluded.add(Point.of(entry.getKey().x() + i, row));
                excluded.add(Point.of(entry.getKey().x() - i, row));
            }
        });
        long beaconsAndSensorsInExcludedRow = map.keySet().stream().filter(excluded::contains).count();
        return excluded.size() - beaconsAndSensorsInExcludedRow;
    }

    public static BeaconZone initialize(List<String> input) {
        Map<Point<Long>, Character> map = new HashMap<>();
        Map<Point<Long>, Long> sensors = new HashMap<>();
        input.stream().map(BeaconZone::getSensorAndBeaconLocation).forEach(pair -> {
            var sensor = pair.get(0);
            var beacon = pair.get(1);
            map.put(sensor, SENSOR);
            sensors.put(sensor, distanceBetween(sensor, beacon));
            map.put(beacon, BEACON);
        });
        return new BeaconZone(map, sensors);
    }

    private static List<Point<Long>> getSensorAndBeaconLocation(String inputLine) {
        return Arrays.stream(inputLine.split(":")).map(String::trim).map(BeaconZone::getPointFromInput).toList();
    }

    private static Point<Long> getPointFromInput(String input) {
        var points = Arrays.stream(input.substring(input.indexOf("x=")).split(",")).map(String::trim).map(str -> str.substring(2)).map(Long::parseLong).toList();
        return Point.of(points.get(0), points.get(1));
    }

    private static Long distanceBetween(Point<Long> sensor, Point<Long> beacon) {
        return Math.abs(sensor.x() - beacon.x()) + Math.abs(sensor.y() - beacon.y());
    }
}
