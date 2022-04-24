package coordinate.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FigureFactory {
    private static final Map<Integer, Function<List<Point>, AbstractFigure>> operators = new HashMap<>();

    public static final int NUMBER_OF_POINTS_OF_LINE = 2;
    public static final int NUMBER_OF_POINTS_OF_TRIANGLE = 2;
    public static final int NUMBER_OF_POINTS_OF_RECTANGLE = 4;

    static {
        operators.put(NUMBER_OF_POINTS_OF_LINE, Line::new);
        operators.put(NUMBER_OF_POINTS_OF_TRIANGLE, Triangle::new);
        operators.put(NUMBER_OF_POINTS_OF_RECTANGLE, Rectangle::new);
    }

    public static AbstractFigure create(List<Point> points) {
        return operators.get(points.size()).apply(points);
    }
}
