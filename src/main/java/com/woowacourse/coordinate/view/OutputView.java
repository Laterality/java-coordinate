package com.woowacourse.coordinate.view;

import com.woowacourse.coordinate.domain.Line;
import com.woowacourse.coordinate.domain.PointGroup;
import com.woowacourse.coordinate.domain.Shape;

public class OutputView {

    public static void printCoordinate(PointGroup points) {
        StringBuilder sb = new StringBuilder();

        for (int y = 24; y > 0; --y) {
            sb.append(getYAxisString(y));
            appendX(sb, points, y);
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

    private static void appendX(StringBuilder sb, PointGroup points, int y) {
        for (int x = 0; x <= 24; ++x) {
            sb.append(getPointString(points.hasMatch(x, y)));
        }
    }

    private static String getPointString(boolean isPointExist) {
        if (isPointExist) {
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
        System.out.println(String.format("%s 넓이는 %.1f", s.getName(), s.calculateArea()));
    }

    public static void printError(String msg) {
        System.out.println(msg);
    }
}
