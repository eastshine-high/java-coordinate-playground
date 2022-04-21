package coordinate.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RectangleTest {

    @Nested
    class Rectangle_생성자는 {

        @Nested
        class 직각사각형이_아닌_경우{
            List<Point> invalidRectangle = Arrays.asList(
                    new Point(10, 10), new Point(15, 10), new Point(22, 18), new Point(10, 18)
            );

            @Test
            void IllegalArgumentException_예외를_던진다() {
                assertThatThrownBy(() -> new Rectangle(invalidRectangle)).isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        class 직각사각형인_경우{
            List<Point> validRectangle = Arrays.asList(
                    new Point(10, 10), new Point(22, 10), new Point(22, 18), new Point(10, 18)
            );

            @Test
            void Rectangle_객체를_반환한다() {
                assertThat(new Rectangle(validRectangle)).isInstanceOf(Rectangle.class);
            }
        }
    }


    @Nested
    class findArea_메소드는{

        @Test
        void 직각사각형의_면적을_구한다() {
            List<Point> points = Arrays.asList(new Point(10, 10), new Point(22, 10), new Point(22, 18), new Point(10, 18));
            Rectangle rectangle = new Rectangle(points);

            double actual = rectangle.findArea();

            assertThat(actual).isEqualTo(96);
        }
    }
}
