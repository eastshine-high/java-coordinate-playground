package coordinate;

import coordinate.controller.CoordinateCalculator;
import coordinate.model.FigureFactory;
import coordinate.model.PointMapper;
import coordinate.view.InputView;
import coordinate.view.ResultView;

public class CoordinateApp {
    public static void main(String[] args) {
        CoordinateCalculator coordinateCaculator = new CoordinateCalculator(
                new InputView(),
                new ResultView(),
                new PointMapper(),
                new FigureFactory()
        );

        coordinateCaculator.run();
    }
}
