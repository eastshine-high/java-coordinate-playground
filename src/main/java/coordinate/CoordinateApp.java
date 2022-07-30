package coordinate;

import coordinate.config.CoordinateContext;
import coordinate.controller.CoordinateCalculator;

public class CoordinateApp {
    public static void main(String[] args) {
        CoordinateCalculator coordinateCalculator = CoordinateContext.getCoordinateCalculator();
        coordinateCalculator.run();
    }
}
