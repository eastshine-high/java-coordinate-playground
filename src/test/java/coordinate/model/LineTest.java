package coordinate.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class LineTest {

    @Nested
    class findArea_메소드는{

        @Test
        void 선의_길이를_반환한다() {
            Line line = new Line(
                    Arrays.asList(
                            new Point(10, 10),
                            new Point(14, 15)));

            double actual = line.findArea();

            assertThat(actual).isEqualTo(6.403, offset(0.00099));
        }
    }
}
