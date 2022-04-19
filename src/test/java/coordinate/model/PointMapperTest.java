package coordinate.model;

import coordinate.dto.CoordinateInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PointMapperTest {
    private PointMapper pointMapper;

    @BeforeEach
    void setUp() {
        pointMapper = new PointMapper();
    }

    @Nested
    class toPointList_메서드는 {

        @Test
        @DisplayName("CoordinateInfo를 List<Point>로 매핑한다.")
        void map_coordinateInfo_to_PointList() {
            CoordinateInfo coordinateInfo = new CoordinateInfo("(2,5)-(4,7)-(14,15)");

            List<Point> points = pointMapper.toPointList(coordinateInfo);
            assertThat(points.size()).isEqualTo(3);
        }
    }
}
