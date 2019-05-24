package com.woowacourse.coordinate.domain;

public class FigureFactory implements FigureCreator {

    @Override
    public Figure create(PointGroup points) {
        return FigureKind.valueOf(points.size()).mapToShape(points);
    }
}
