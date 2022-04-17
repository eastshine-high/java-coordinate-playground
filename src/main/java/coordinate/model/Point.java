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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
