package com.woowacourse.coordinate.domain;

import java.util.Objects;

public class Square extends Figure {
    public static final int NUM_OF_POINTS = 4;

    public Square(PointGroup points) {
        super(points, NUM_OF_POINTS);
        checkIfSquare(points);
        this.points = points;
    }

    private void checkIfSquare(PointGroup points) {
        // 네 점 중 세 점으로 만든 두 개의 삼각형이 직각삼각형이면 직사각형이라고 판단
        Triangle triangle1 = new Triangle(points.subGroup(0, 3));
        Triangle triangle2 = new Triangle(points.subGroup(1, 4));
        if (!(triangle1.isRightAngled() && triangle2.isRightAngled())) {
            throw new IllegalArgumentException("직사각형이 아닙니다.");
        }
    }

    @Override
    public double calculateArea() {
        return new Triangle(points.subGroup(0, 3)).calculateArea() * 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square square = (Square) o;
        return Objects.equals(points, square.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "사각형";
    }

}
