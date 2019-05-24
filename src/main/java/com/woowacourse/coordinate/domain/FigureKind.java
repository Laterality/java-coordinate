package com.woowacourse.coordinate.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum FigureKind {
    LINE(Line.NUM_OF_POINTS, Line::new),
    TRIANGLE(Triangle.NUM_OF_POINTS, Triangle::new),
    SQUARE(Square.NUM_OF_POINTS, Square::new);

    private int numOfPoints;
    private Function<PointGroup, Figure> mapper;

    FigureKind(int numOfPoints, Function<PointGroup, Figure> shapeMapper) {
        this.numOfPoints = numOfPoints;
        this.mapper = shapeMapper;
    }

    public Figure mapToShape(PointGroup points) {
        return mapper.apply(points);
    }

    public static FigureKind valueOf(int numOfPoints) {
        Optional<FigureKind> found = Arrays.stream(values())
            .filter(k -> k.numOfPoints == numOfPoints)
            .findFirst();

        found.orElseThrow(() -> new IllegalArgumentException("일치하는 Figure 종류가 없습니다."));
        return found.get();
    }
}
