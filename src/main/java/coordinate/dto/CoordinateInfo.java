package coordinate.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 좌표 정보
 */
public class CoordinateInfo {
    private static final String POINTS_STRING_FORMAT = "(\\(\\d{1,2}\\,\\d{1,2}\\)\\-){1,3}\\(\\d{1,2}\\,\\d{1,2}\\)";

    private static final Pattern pointsStringPattern;

    static {
        pointsStringPattern = Pattern.compile(POINTS_STRING_FORMAT);
    }

    private final String coordinateInfo;

    public CoordinateInfo(String inputString) {
        validateFormat(inputString);

        coordinateInfo = inputString;
    }

    public String getValue() {
        return coordinateInfo;
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
        Matcher matcher = pointsStringPattern.matcher(inputString);
        if(!matcher.matches()){
            throw new IllegalArgumentException("좌표 형식이 올바르지 않습니다. 좌표 예: (10,10)-(14,15)");
        }
    }
}
