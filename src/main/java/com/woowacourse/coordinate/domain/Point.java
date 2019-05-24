package com.woowacourse.coordinate.domain;

import java.util.Objects;

public class Point {
    private static final int MIN = 0;
    private static final int MAX = 24;

    private final int x;
    private final int y;

    public Point(final int x, final int y) {
        checkRange(x, y);
        this.x = x;
        this.y = y;
    }

    private void checkRange(final int x, final int y) {
        if (x <= MIN || x > MAX || y <= MIN || y > MAX) {
            throw new IllegalArgumentException(String.format("범위를 벗어난 좌표입니다 { x: %d, y: %d }", x, y));
        }
    }

    public boolean matchX(final int x) {
        return this.x == x;
    }

    public boolean matchY(final int y) {
        return this.y == y;
    }

    public boolean matchX(final Point p) {
        return this.x == p.x;
    }

    public boolean matchY(final Point p) {
        return this.y == p.y;
    }

    public int deltaX(final Point p) {
        return this.x - p.x;
    }

    public int deltaY(final Point p) {
        return this.y - p.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x &&
            y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("Point { x: %d, y: %d }", x, y);
    }
}
