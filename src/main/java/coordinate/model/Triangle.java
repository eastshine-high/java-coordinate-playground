package coordinate.model;

import java.util.List;

public class Triangle extends AbstractFigure{
    public Triangle(List<Point> points) {
        super(points);
    }

    @Override
    public double findArea() {
        Point firstPoint = getPoints().get(0);
        Point secondPoint = getPoints().get(1);
        Point thirdPoint = getPoints().get(2);

        double firstSide = firstPoint.getDistance(secondPoint);
        double secondSide = secondPoint.getDistance(thirdPoint);
        double thirdSide = thirdPoint.getDistance(firstPoint);

        return useHeronFormula(firstSide, secondSide, thirdSide);
    }

    // Heron's formula
    private double useHeronFormula(double firstSide, double secondSide, double thirdSide) {
        double s = (firstSide + secondSide + thirdSide) / 2;
        return Math.sqrt(
                s * (s - firstSide) * (s - secondSide) * (s - thirdSide)
        );
    }

    @Override
    public String reportArea() {
        return "삼각형 넓이는 " + findArea();
    }
}
