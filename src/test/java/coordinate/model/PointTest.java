package coordinate.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PointTest {
    public static final int VALID_NUMBER = 10;

    @Nested
    class Point_생성자는{

        @Nested
        @DisplayName("0-24 범위 안의 숫자가 주어지면")
        class Context_with_valid_number{

            @ParameterizedTest
            @ValueSource(ints = {0, 15, 24})
            void Point_객체를_생성한다(int validNumber) {
                assertThat(new Point(validNumber, VALID_NUMBER)).isInstanceOf(Point.class);
                assertThat(new Point(VALID_NUMBER, validNumber)).isInstanceOf(Point.class);
            }
        }

        @Nested
        @DisplayName("0-24 범위 밖의 숫자가 주어지면")
        class Context_with_invalid_number{

            @ParameterizedTest
            @ValueSource(ints = {-1, 25})
            void IllegalArgumentException_예외를_던진다(int invalidNumber) {
                assertThatThrownBy(() -> new Point(invalidNumber, VALID_NUMBER))
                        .isInstanceOf(IllegalArgumentException.class);

                assertThatThrownBy(() -> new Point(VALID_NUMBER, invalidNumber))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @Nested
    class getDistance_메소드는{

        @Test
        void 점과_점의_거리를_반환한다() {
            Point startPoint = new Point(10, 10);
            Point endpoint = new Point(14, 15);

            double actual = startPoint.getDistance(endpoint);

            System.out.println("actual : "+actual);
            assertThat(actual).isEqualTo(6.403, offset(0.00099));
        }
    }
}
