package com.woowacourse.coordinate.domain;

import java.util.Objects;
import java.util.Optional;

public class Triangle extends Figure {
    public static final int NUM_OF_POINTS = 3;

    private static final double COMPARE_OFFSET = 0.01;

    public Triangle(PointGroup points) {
        super(points, NUM_OF_POINTS);
        checkIfValidTriangle(points);
    }

    private void checkIfValidTriangle(PointGroup points) {
        Optional<Double> maybeSlope1 = points.calculateSlope(0, 1);
        Optional<Double> maybeSlope2 = points.calculateSlope(0, 2);

        if (maybeSlope1.isPresent() && maybeSlope2.isPresent()) {
            double slope1 = maybeSlope1.get();
            double slope2 = maybeSlope2.get();
            throwIfSlopeEqual(slope1, slope2);
        }

        if (!maybeSlope1.isPresent() && !maybeSlope2.isPresent()) {
            throw new IllegalArgumentException("삼각형을 만들 수 없는 좌표입니다.");
        }

    }

    private void throwIfSlopeEqual(double slope1, double slope2) {
        if (slope1 == slope2) {
            throw new IllegalArgumentException("삼각형을 만들 수 없는 좌표입니다.");
        }
    }

    /**
     * @return 직각삼각형인 경우 true, 아니면 false
     */
    public boolean isRightAngled() {
        double a = points.calculateDistance(0, 1);
        double b = points.calculateDistance(1, 2);
        double c = points.calculateDistance(2, 0);
        double hypotenuse = Math.max(a, Math.max(b, c));
        double hSquare = hypotenuse * hypotenuse;
        // 가장 긴 변(=빗변)과 나머지 변의 길이가 피타고라스 법칙을 만족하는지 확인
        if (hypotenuse == a) {
            return Math.abs(hSquare - (b * b + c * c)) <= COMPARE_OFFSET;
        }
        if (hypotenuse == b) {
            return Math.abs(hSquare - (a * a + c * c)) <= COMPARE_OFFSET;
        }
        if (hypotenuse == c) {
            return Math.abs(hSquare - (a * a + b * b)) <= COMPARE_OFFSET;
        }
        return false;
    }

    @Override
    public double calculateArea() {
        double lengthA = points.calculateDistance(0, 1);
        double lengthB = points.calculateDistance(1, 2);
        double lengthC = points.calculateDistance(2, 0);
        double s = (lengthA + lengthB + lengthC) / 2;
        return Math.sqrt(s * (s - lengthA) * (s - lengthB) * (s - lengthC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Triangle)) {
            return false;
        }
        final Triangle triangle = (Triangle) o;
        return Objects.equals(points, triangle.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "삼각형";
    }
}
