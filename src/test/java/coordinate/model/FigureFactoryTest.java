package coordinate.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FigureFactoryTest {

    @Nested
    class create_메소드는{

        @Nested
        class List의_크기가_2개일_경우{
            List<Point> twoPoints = Arrays.asList(new Point(10, 10), new Point(13, 14));

            @Test
            void Line_인스턴스를_반환한다() {
                assertThat(FigureFactory.create(twoPoints))
                        .isInstanceOf(Line.class);
            }
        }

        @Nested
        class List의_크기가_3개일_경우{
            List<Point> twoPoints = Arrays.asList(new Point(10, 10), new Point(13, 14));

            @Test
            void Line_인스턴스를_반환한다() {
                assertThat(FigureFactory.create(twoPoints))
                        .isInstanceOf(Line.class);
            }
        }

        @Nested
        class List의_크기가_4개일_경우{
            List<Point> twoPoints = Arrays.asList(new Point(10, 10), new Point(10, 24), new Point(16, 24), new Point(16, 10));

            @Test
            void Rectangle_인스턴스를_반환한다() {
                assertThat(FigureFactory.create(twoPoints))
                        .isInstanceOf(Rectangle.class);
            }
        }
    }
}
