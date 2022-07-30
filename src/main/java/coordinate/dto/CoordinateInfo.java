package coordinate.dto;

import coordinate.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 좌표 정보
 */
public class CoordinateInfo {
    private static final String COORDINATE_INFO_FORMAT = "(\\(\\d{1,2}\\,\\d{1,2}\\)\\-){1,3}\\(\\d{1,2}\\,\\d{1,2}\\)";
    private static final String EXTRACT_NUMBER_FORMAT = "\\((\\d{1,2}),(\\d{1,2})\\)";
    private static final String POINT_DELIMITER = "-";

    private static final Pattern coordinateInfoPattern;
    private static final Pattern extractNumberPattern;

    private final String coordinateInfo;

    static {
        coordinateInfoPattern = Pattern.compile(COORDINATE_INFO_FORMAT);
        extractNumberPattern = Pattern.compile(EXTRACT_NUMBER_FORMAT);
    }

    public CoordinateInfo(String inputString) {
        validateFormat(inputString);
        coordinateInfo = inputString;
    }

    /**
     * 입력 문자열이 '좌표 정보'인지를 검증한다.
     *
     * '좌표 정보'는 괄호"(", ")"로 둘러쌓인 좌표 값으로 이뤄져 있다.
     * 좌표 값과 좌표 값 사이는 '-' 문자로 구분한다.
     * 좌표 값의 갯수는 2~4개이다.
     * @param inputString 입력 문자열.
     */
    private void validateFormat(String inputString) {
        Matcher matcher = coordinateInfoPattern.matcher(inputString);
        if(!matcher.matches()){
            throw new IllegalArgumentException("좌표 형식이 올바르지 않습니다. 좌표 예: (10,10)-(14,15)");
        }
    }

    /**
     * '좌표 정보'를 '점으로 이뤄진 리스트'로 변환한다.
     *
     * @return 점으로 이뤄진 리스트.
     */
    public List<Point> toPointList() {
        String[] pointStrings = coordinateInfo.split(POINT_DELIMITER);

        List<Point> points = new ArrayList<>();
        for (String pointString : pointStrings) {
            points.add(makePoint(pointString));
        }
        return points;
    }

    /**
     * '점 문자열'을 '점'으로 변환한다.
     *
     * @param pointString 점 문자열.
     * @return 점.
     */
    private Point makePoint(String pointString) {
        Matcher matcher = extractNumberPattern.matcher(pointString);
        matcher.find();
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        return new Point(x, y);
    }
}
