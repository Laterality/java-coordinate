package com.woowacourse.coordinate.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointGroupTest {

    @Test
    void create() {
        List<Point> points = Arrays.asList(new Point(1, 5), new Point(5, 1));
        assertThat(new PointGroup(points)).isEqualTo(new PointGroup(points));
    }

    @Test
    void createWithString() {
        assertThat(PointGroup.ofString("(4, 10)")).isEqualTo(new PointGroup(Collections.singletonList(new Point(4, 10))));
        assertThat(PointGroup.ofString("(4 , 10)")).isEqualTo(new PointGroup(Collections.singletonList(new Point(4, 10))));
        assertThat(PointGroup.ofString("( 4 , 10 )")).isEqualTo(new PointGroup(Collections.singletonList(new Point(4, 10))));
    }

    @Test
    void ofString() {
        assertThat(PointGroup.ofString("(4, 10)-(13,13)")).isEqualTo(new PointGroup(Arrays.asList(new Point(4, 10), new Point(13, 13))));
        assertThat(PointGroup.ofString("(4, 10) - (13,13)")).isEqualTo(new PointGroup(Arrays.asList(new Point(4, 10), new Point(13, 13))));
        assertThat(PointGroup.ofString("(4, 10) - ( 13,13 )")).isEqualTo(new PointGroup(Arrays.asList(new Point(4, 10), new Point(13, 13))));
        assertThat(PointGroup.ofString("(10, 10) - ( 22,10 )-(22, 18)-(10, 18)"))
            .isEqualTo(new PointGroup(Arrays.asList(new Point(10, 10), new Point(22, 10), new Point(22, 18), new Point(10, 18))));
    }

    @Test
    void validateInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> PointGroup.ofString("4,10"));
    }


    @Test
    void calculateDistance() {
        PointGroup pg = new PointGroup(Arrays.asList(new Point(1, 1), new Point(1, 3), new Point(2, 2)));
        assertThat(pg.calculateDistance(0, 1)).isEqualTo(2);
        assertThat(pg.calculateDistance(0, 2)).isEqualTo(1.414, offset(0.01));
    }

    @Test
    void calculateSlope() {
        PointGroup pg = new PointGroup(Arrays.asList(new Point(1, 1), new Point(3, 3), new Point(5, 2),
            new Point(5, 1), new Point(1, 6)));
        assertThat(pg.calculateSlope(0, 1).get()).isEqualTo(1);
        assertThat(pg.calculateSlope(0, 2).get()).isEqualTo(0.25, offset(0.01));
        assertThat(pg.calculateSlope(0, 3).get()).isEqualTo(0, offset(0.01));
        assertThat(pg.calculateSlope(0, 4).isPresent()).isFalse();
    }

    @Test
    void calculateAngle() {
        PointGroup pg = new PointGroup(Arrays.asList(new Point(6, 3), new Point(9, 6), new Point(3, 6), new Point(1, 1), new Point(1, 5), new Point(7, 1)));
        assertThat(pg.calculateAngle(0, 1, 2)).isEqualTo(90);
        assertThat(pg.calculateAngle(3, 4, 5)).isEqualTo(90);
    }

    @Test
    void subGroup() {
        PointGroup pg = new PointGroup(Arrays.asList(new Point(1, 2), new Point(2, 3), new Point(3, 4), new Point(4, 5)));
        PointGroup subGroup = pg.subGroup(0, 3);
        assertThat(subGroup.size()).isEqualTo(3);
        assertThat(subGroup.hasMatch(1, 2)).isTrue();
        assertThat(subGroup.hasMatch(2, 3)).isTrue();
        assertThat(subGroup.hasMatch(3, 4)).isTrue();
    }
}
