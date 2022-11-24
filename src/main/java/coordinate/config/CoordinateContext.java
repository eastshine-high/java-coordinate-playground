package coordinate.config;

import coordinate.controller.CoordinateCalculator;
import coordinate.model.FigureFactory;
import coordinate.view.InputView;
import coordinate.view.ResultView;

/**
 * 의존성 생성과 주입을 담당
 */
public class CoordinateContext {
    private static CoordinateCalculator coordinateCalculator;

    private CoordinateContext() { }

    public static CoordinateCalculator getCoordinateCalculator() {
        if (coordinateCalculator == null) {
            coordinateCalculator = new CoordinateCalculator(
                    new InputView(),
                    new ResultView(),
                    new FigureFactory()
            );
        }

        return coordinateCalculator;
    }
}
