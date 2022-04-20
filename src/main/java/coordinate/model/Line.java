package coordinate.model;

import java.util.List;

/**
 * 선
 */
public class Line extends AbstractFigure{
    public Line(List<Point> points) {
        super(points);
    }

    @Override
    public double findArea() {
        return getPoints().get(0).getDistance(getPoints().get(1));
    }

    @Override
    public String reportArea() {
        return "두 점 사이 거리는 " + findArea();
    }
}
