package com.woowacourse.coordinate.domain;

public abstract class Figure implements Shape {
    protected PointGroup points;

    Figure(PointGroup points, int numOfPoints) {
        if (points.size() != numOfPoints) {
            throw new IllegalArgumentException("점의 개수가 올바르지 않습니다.");
        }
        this.points = points;
    }
}
