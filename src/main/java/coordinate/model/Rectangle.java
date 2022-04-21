package coordinate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

public class Rectangle extends AbstractFigure{
    private static final String ERROR_INVALID_RECTANGLE = "직각사각형이 아닙니다.";
    public static final int NUM_OF_TYPES_OF_RECTANGLE_COORDINATES = 2;

    public Rectangle(List<Point> points) {
        super(points);
        validateRectangle(points);
    }

    private void validateRectangle(List<Point> points) {
        Set<Integer> xValuesOfPoints = convertToUniqueXValues(points);
        Set<Integer> yValuesOfPoints = convertToUniqueYValues(points);

        if (hasNotTwoValues(xValuesOfPoints) || hasNotTwoValues(yValuesOfPoints)) {
            throw new IllegalArgumentException(ERROR_INVALID_RECTANGLE);
        }
    }

    private Set<Integer> convertToUniqueXValues(List<Point> points) {
        return convertToUniqueValue(points, Point::getX);
    }

    private Set<Integer> convertToUniqueYValues(List<Point> points) {
        return convertToUniqueValue(points, Point::getY);
    }

    private Set<Integer> convertToUniqueValue(List<Point> points, Function<Point, Integer> function) {
        return points.stream()
                .map(function)
                .collect(toSet());
    }

    private boolean hasNotTwoValues(Set<Integer> values) {
        return values.size() != NUM_OF_TYPES_OF_RECTANGLE_COORDINATES;
    }

    @Override
    public double findArea() {
        return getLength() * getBreadth();
    }

    private double getLength() {
        List<Integer> xValues = new ArrayList(convertToUniqueXValues(getPoints()));
        return Math.abs(xValues.get(0) - xValues.get(1));
    }

    private double getBreadth() {
        List<Integer> yValues = new ArrayList(convertToUniqueYValues(getPoints()));
        return Math.abs(yValues.get(0) - yValues.get(1));
    }

    @Override
    public String reportArea() {
        return "사각형 넓이는 " + findArea();
    }
}
