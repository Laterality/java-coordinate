package com.woowacourse.coordinate.domain;

import java.util.Objects;

public class Line extends Figure {
    public static final int NUM_OF_POINTS = 2;

    public Line(PointGroup points) {
        super(points, NUM_OF_POINTS);
        this.points = points;
    }

    @Override
    public double calculateArea() {
        return points.calculateDistance(0, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Line)) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(points, line.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
