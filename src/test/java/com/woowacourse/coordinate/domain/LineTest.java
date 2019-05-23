package com.woowacourse.coordinate.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {

    @Test
    void testCreate() {
        List<Point> points = Arrays.asList(new Point(3, 3), new Point(6, 3));
        assertThat(new Line(points))
            .isEqualTo(new Line(points));
    }

    @Test
    void invalidSizeOfPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Line(Arrays.asList(new Point(1, 1)));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Line(Arrays.asList(new Point(1, 1), new Point(5, 10), new Point(10, 14)));
        });
    }

    @Test
    void calculateLength() {
        assertThat(new Line(Arrays.asList(new Point(3, 3), new Point(6, 3))).calculateArea())
            .isEqualTo(3);
    }

    @Test
    void duplicatePoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Line(Arrays.asList(new Point(5, 10), new Point(5, 10)));
        });
    }
}
