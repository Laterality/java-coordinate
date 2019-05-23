package com.woowacourse.coordinate.view;

import com.woowacourse.coordinate.domain.Line;
import com.woowacourse.coordinate.domain.Point;
import com.woowacourse.coordinate.domain.Shape;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printCoordinate(List<Point> points) {
        StringBuilder sb = new StringBuilder();

        for (int i = 24; i > 0; --i) {
            final int currentY = i;
            sb.append(getYAxisString(currentY));
            List<Point> pointsToPrint = points.stream()
                .filter(p -> p.matchY(currentY))
                .collect(Collectors.toList());
            printPoint(sb, pointsToPrint);
            sb.append("\n");
        }
        printXAxis(sb);
        System.out.println(sb.toString());
        System.out.println();
    }

    private static String getYAxisString(int y) {
        if (y % 2 == 0) {
            return String.format("%2s|", y);
        }
        return "  |";
    }

    private static void printPoint(StringBuilder sb, List<Point> pointsToPrint) {
        for (int j = 0; j <= 24; ++j) {
            sb.append(getPointString(pointsToPrint, j));
        }
    }

    private static String getPointString(List<Point> pointsToPrint, int currentX) {
        if (pointsToPrint.stream()
            .anyMatch(p -> p.matchX(currentX))) {
            return " ·";
        }
        return "  ";
    }

    private static void printXAxis(StringBuilder sb) {
        sb.append("  +");
        for (int i = 0; i < 24; i++) {
            sb.append("──");
        }
        sb.append("─\n  ");
        for (int i = 0; i <= 24; i++) {
            sb.append(getXAxisString(i));
        }
    }

    private static String getXAxisString(int x) {
        if (x % 2 == 0) {
            return String.format("%2d", x);
        }
        return "  ";
    }

    private static void printDistance(double distance) {
        System.out.println(String.format("두 점 사이의 거리는 %f", distance));
    }

    public static void printShape(Shape s) {
        if (s instanceof Line) {
            printDistance(s.calculateArea());
            return;
        }
        System.out.println(String.format("%s 넓이는 %.1f", s, s.calculateArea()));
    }

    public static void printError(String msg) {
        System.out.println(msg);
    }
}
