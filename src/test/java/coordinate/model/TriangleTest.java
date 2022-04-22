package coordinate.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class TriangleTest {

    @Nested
    class findArea_메소드는{

        @Test
        void 삼각형의_면적을_구한다() {
            Triangle triangle = new Triangle(
                    Arrays.asList(
                            new Point(10, 10), new Point(14,15), new Point(20,8)));

            double actual = triangle.findArea();

            assertThat(actual).isEqualTo(29.0, offset(0.00099));
        }
    }
}
