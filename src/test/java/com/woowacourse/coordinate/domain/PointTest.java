package com.woowacourse.coordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointTest {
    @Test
    void create() {
        Point point = new Point(10, 10);
        assertThat(point).isEqualTo(new Point(10, 10));
    }

    @Test
    void validateRange() {
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new Point(10, -1));
        assertThrows(IllegalArgumentException.class, () -> new Point(5, 25));
        assertThrows(IllegalArgumentException.class, () -> new Point(25, 5));
        assertThrows(IllegalArgumentException.class, () -> PointGroup.ofString("(5,5)-(25,10)"));
    }
}
