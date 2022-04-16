package coordinate.model;

import java.util.List;

/**
 * 도형의 속성을 추상화.
 */
public abstract class AbstractFigure  implements Figure{
    private final List<Point> points;

    public AbstractFigure(List<Point> points) {
        this.points = points;
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }
}
