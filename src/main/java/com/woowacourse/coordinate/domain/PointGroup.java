package com.woowacourse.coordinate.domain;

import java.util.*;
import java.util.stream.Collectors;

public class PointGroup {
    private static final String PATTERN = "[,()]";

    private final List<Point> points;

    public PointGroup(List<Point> points) {
        if (new HashSet<>(points).size() != points.size()) {
            throw new IllegalArgumentException("위치가 같은 점(point)이 존재합니다. 각 점의 위치는 달라야 합니다.");
        }
        this.points = points;
    }

    public static PointGroup ofString(final String s) {
        return new PointGroup(Arrays.stream(s.split("-"))
            .map(PointGroup::createPointWithString)
            .collect(Collectors.toList()));
    }

    private static Point createPointWithString(final String str) {
        String trimmed = str.trim();
        if (!isSurroundedWithParenthesis(trimmed)) {
            throw new IllegalArgumentException("유효하지 않은 좌표 입력입니다.");
        }

        String[] segments = trimmed.split(PATTERN);
        int x = Integer.valueOf(segments[1].trim());
        int y = Integer.valueOf(segments[2].trim());
        return new Point(x, y);
    }

    private static boolean isSurroundedWithParenthesis(final String s) {
        return s.startsWith("(") && s.endsWith(")");
    }

    public int size() {
        return points.size();
    }

    /**
     * 두 점의 거리를 구한다.
     *
     * @param idx1 한 점의 인덱스
     * @param idx2 다른 한 점의 인덱스
     * @return 두 점(points[idx1], points[idx2]) 사이의 거리
     */
    public double calculateDistance(final int idx1, final int idx2) {
        double dx = Math.abs(points.get(idx1).deltaX(points.get(idx2)));
        double dy = Math.abs(points.get(idx1).deltaY(points.get(idx2)));
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 두 점 사이의 기울기를 구하여 반환한다.
     *
     * @param idx1
     * @param idx2
     * @return 기울기가 무한대인 경우(y축에 평행한 경우) null을 갖는 Optional 객체.
     */
    public Optional<Double> calculateSlope(final int idx1, final int idx2) {
        if (at(idx1).matchX(at(idx2))) {
            return Optional.ofNullable(null);
        }
        return Optional.of((at(idx1).deltaY(at(idx2))) / (double) (at(idx1).deltaX(at(idx2))));
    }

    /**
     * this를 중심으로 하는 각 ∠points[idx1]-idx[idxCorner]-points[idx2] 의 각도를 반환한다.
     *
     * @param idxCorner 각의 기준이 될 점의 인덱스
     * @param idx1
     * @param idx2
     * @return 계산된 각도, 양수
     */
    public double calculateAngle(final int idxCorner, final int idx1, final int idx2) {
        double cangleRad = Math.atan((double) at(idxCorner).deltaY(at(idx2)) / at(idxCorner).deltaX(at(idx2))) -
            Math.atan((double) at(idxCorner).deltaY(at(idx1)) / at(idxCorner).deltaX(at(idx1)));
        return Math.abs(Math.toDegrees(cangleRad));
    }

    private Point at(int idx) {
        return points.get(idx);
    }

    public boolean hasMatch(int x, int y) {
        return points.stream()
            .anyMatch(p -> p.matchX(x) && p.matchY(y));
    }

    /**
     * from <= i < to
     *
     * @param from
     * @param to
     * @return
     */
    public PointGroup subGroup(int from, int to) {
        return new PointGroup(points.subList(from, to));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointGroup that = (PointGroup) o;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("PointGroup [ ")
            .append(points.stream()
                .map(Point::toString)
                .collect(Collectors.joining(", ")))
            .append(" ]")
            .toString();
    }
}
