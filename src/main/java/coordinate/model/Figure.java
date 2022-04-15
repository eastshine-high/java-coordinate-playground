package coordinate.model;

import java.util.List;

/**
 * 도형의 행동(메서드)을 추상화.
 */
public interface Figure {
    List<Point> getPoints();

    double findArea();

    String reportArea();
}
