package lotto.model.domain.lotto.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Nested
    @DisplayName("로또 번호 개수")
    class 로또번호개수 {

        static Stream<List<Integer>> sizeGt6ListProvider() {
            return Stream.of(
                    List.of(1, 2, 3, 4, 5, 6, 7),
                    List.of(1, 2, 3, 4, 5, 6, 7, 8),
                    List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            );
        }

        @ParameterizedTest(name = "{0}")
        @DisplayName("6개 초과일시 실패")
        @MethodSource("sizeGt6ListProvider")
        void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다(List<Integer> sizeGt6List) {
            // when & then
            assertThatThrownBy(() -> new Lotto(sizeGt6List))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("6개");
        }

        static Stream<List<Integer>> sizeLt6ListProvider() {
            return Stream.of(
                    List.of(1, 2, 3, 4, 5),
                    List.of(1, 2, 3, 4),
                    List.of(1, 2, 3)
            );
        }

        @ParameterizedTest(name = "{0}")
        @DisplayName("6개 미만일시 실패")
        @MethodSource("sizeLt6ListProvider")
        void 로또_번호_개수_6개_미만_예외(List<Integer> sizeLt6List) {
            // when & then
            assertThatThrownBy(() -> new Lotto(sizeLt6List))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("6개");
        }

        static Stream<List<Integer>> sizeEq6ListProvider() {
            return Stream.of(
                    List.of(1, 2, 3, 4, 5, 6),
                    List.of(11, 12, 13, 14, 15, 16),
                    List.of(21, 22, 23, 24, 25, 26)
            );
        }

        @ParameterizedTest(name = "{0}")
        @DisplayName("6개일시 성공")
        @MethodSource("sizeEq6ListProvider")
        void 로또_번호_개수_6개_성공(List<Integer> sizeEq6List) {
            // when & then
            assertDoesNotThrow(() -> new Lotto(sizeEq6List));
        }

    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}
