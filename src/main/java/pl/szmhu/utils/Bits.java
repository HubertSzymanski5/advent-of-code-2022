package pl.szmhu.utils;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Bits {

    private final List<Boolean> bits;

    public boolean get(int n) {
        if (n >= bits.size()) {
            throw new IndexOutOfBoundsException("Cannot access bit at " + n + ". Length is " + bits.size());
        }
        return bits.get(n);
    }

    public int toInt() {
        int result = 0;
        for (int i = 0; i < bits.size(); i++) {
            if (bits.get(i)) {
                result += Math.pow(2, i);
            }
        }
        return result;
    }

    public int size() {
        return bits.size();
    }

    public Bits inverted() {
        List<Boolean> inverted = this.bits.stream().map(b -> !b).toList();
        return Bits.from(inverted);
    }

    public Bits xor(Bits bits1) {
        if (size() != bits1.size()) {
            throw new IllegalArgumentException("Bits must be the same size");
        }
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            result.add(i, get(i) != bits1.get(i));
        }
        return Bits.from(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = this.bits.size() - 1; i >= 0; i--) {
            if (bits.get(i))
                sb.append('1');
            else sb.append('0');
        }
        return sb.toString();
    }

    private Bits() {
        this(new ArrayList<>());
    }

    private Bits(List<Boolean> bits) {
        this.bits = bits;
    }

    public static Bits from(List<Boolean> list) {
        return new Bits(list);
    }

    public static Bits valueOf(String string) {
        Bits result = new Bits();
        for (int i = string.length() - 1; i >= 0; i--) {
            char b = string.charAt(i);
            if (b == '1') {
                result.bits.add(true);
            } else if (b == '0') {
                result.bits.add(false);
            } else {
                throw new IllegalArgumentException("String has invalid format, only '1' and '0' are allowed");
            }
        }
        return result;
    }
}
