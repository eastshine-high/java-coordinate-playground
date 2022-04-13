package coordinate.dto;

import coordinate.dto.CoordinateInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// 변두리 테스트 정리
class CoordinateInfoTest {

    @Nested
    class CoordinateString_생성자는{

        @Nested
        @DisplayName("일 혹은 십의 자리 숫자의 올바른 형식의 좌표가 2-4개가 주어지면")
        class Context_with_valid_format{

            @ParameterizedTest
            @ValueSource(strings = { "(2,10)-(9,15)", "(2,5)-(4,7)-(14,15)", "(10,10)-(14,15)-(14,15)-(14,15)" })
            void CoordinateString을_생성한다(String coordinateString) {
                assertThat(new CoordinateInfo(coordinateString)).isInstanceOf(CoordinateInfo.class);
            }
        }

        @Nested
        @DisplayName("올바르지 못한 형식의 좌표가 주어진다면")
        class Context_with_invalid_format {

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = { "2,10-9,15", "(2,5)(4,7)", "(1,)(3,)", "  " })
            void IllegalArgumentException_예외를_던진다(String invalidFormatString) {
                assertThatThrownBy(() -> new CoordinateInfo(invalidFormatString))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("일 혹은 십의 자리 숫자 외의 좌표가 주어질 경우")
        class Context_with_invalid_range {
            String overRangeNumber = "(10,100)-(14,15)";

            @Test
            void IllegalArgumentException_예외를_던진다() {
                assertThatThrownBy(() -> new CoordinateInfo(overRangeNumber))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("좌표가 2-4개 외의 주어지면 좌표가 주어질 경우")
        class Context_with_invalid_coordinates {

            @ParameterizedTest
            @ValueSource(strings = {"(2,10)", "(10,10)-(14,15)-(14,15)-(14,15)-(14,15)"})
            void IllegalArgumentException_예외를_던진다(String invalidCoordinates) {
                assertThatThrownBy(() -> new CoordinateInfo(invalidCoordinates))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }
}
