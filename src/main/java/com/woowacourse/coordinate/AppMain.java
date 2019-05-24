package com.woowacourse.coordinate;

import com.woowacourse.coordinate.domain.Figure;
import com.woowacourse.coordinate.domain.FigureCreator;
import com.woowacourse.coordinate.domain.FigureFactory;
import com.woowacourse.coordinate.domain.PointGroup;
import com.woowacourse.coordinate.view.InputView;
import com.woowacourse.coordinate.view.OutputView;

public class AppMain {
    public static void main(String[] args) {
        boolean handledResult = tryHandlePoints();

        while (!handledResult) {
            handledResult = tryHandlePoints();
        }
    }

    private static boolean tryHandlePoints() {
        try {
            printPoints();
            return true;
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
        }
        return false;
    }

    private static void printPoints() {
        PointGroup points = PointGroup.ofString(InputView.inputCoordinate());
        FigureCreator figureCreator = new FigureFactory();

        Figure figure = figureCreator.create(points);
        OutputView.printCoordinate(points);

        if (points.size() != 1) {
            OutputView.printShape(figure);
        }
    }
}
