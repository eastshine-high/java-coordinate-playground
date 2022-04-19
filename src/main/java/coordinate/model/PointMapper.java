package coordinate.model;

import coordinate.dto.CoordinateInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 점 변환기
 */
public class PointMapper {
    private static final String POINT_DELIMITER = "-";

    private static final Pattern pattern = Pattern.compile("\\((\\d{1,2}),(\\d{1,2})\\)");

    /**
     * '좌표 정보'를 '점으로 이뤄진 리스트'로 변환한다.
     *
     * @param coordinateInfo 좌표 정보.
     * @return 점으로 이뤄진 리스트.
     */
    public List<Point> toPointList(CoordinateInfo coordinateInfo) {
        String[] pointStrings = coordinateInfo.getValue().split(POINT_DELIMITER);

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
        Matcher matcher = pattern.matcher(pointString);
        matcher.find();
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        return new Point(x, y);
    }
}
