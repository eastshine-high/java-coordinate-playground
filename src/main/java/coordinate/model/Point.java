package coordinate.model;

/**
 * 점.
 */
public class Point {
    public static final String INVALID_COORDINATE_RANGE = "X, Y 좌표 모두 0-24까지만 입력 가능합니다.";
    public static final int MIN_RANGE = 0;
    public static final int MAX_RANGE = 24;

    private final int x;
    private final int y;

    public Point(int x, int y) {
        validateRange(x);
        validateRange(y);

        this.x = x;
        this.y = y;
    }

    /**
     * 숫자의 범위를 검증한다. 최소 0(포함), 최대 24(포함)까지의 숫자만 허용한다.
     *
     * @param number 숫자.
     */
    private void validateRange(int number) {
        if (number > MAX_RANGE || number < MIN_RANGE) throw new IllegalArgumentException(INVALID_COORDINATE_RANGE);
    }

    /**
     * 현재 점 객체와 끝점과의 거리를 계산하여 반환한다.
     * 두 점 사이 거리는 `제곱근((A.x - B.x)^제곱 + (A.y - B.y)^제곱)`로 구할 수 있다.
     *
     * @param endpoint 끝점.
     * @return 현재 점 객체와 끝점과의 거리.
     */
    public double getDistance(Point endpoint) {
        return Math.sqrt(
            square(this.getX() - endpoint.getX()) + square(this.getY() - endpoint.getY())
        );
    }

    /**
     * 숫자를 제곱한다.
     *
     * @param number 숫자
     * @return 숫자를 제곱한 값.
     */
    private double square(int number) {
        return number * number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
