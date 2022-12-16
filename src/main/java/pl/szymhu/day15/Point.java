package pl.szymhu.day15;

record Point<T extends Number>(T x, T y) {

    static <T extends Number> Point<T> of(T x, T y) {
        return new Point<>(x, y);
    }
}
