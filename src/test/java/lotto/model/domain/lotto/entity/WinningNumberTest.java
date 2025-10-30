package lotto.model.domain.lotto.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumberTest {

    static Stream<Arguments> validWinningNumberCreateProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(List.of(45, 44, 43, 42, 41, 40), 39)
        );
    }

    @ParameterizedTest(name = "numbers:{0} bonus:{1}")
    @DisplayName("WinningNumber 생성 성공")
    @MethodSource("validWinningNumberCreateProvider")
    void test1(List<Integer> regularNumbers, int bonusNumber) {
        // when & then
        assertDoesNotThrow(() -> new WinningNumber(regularNumbers, bonusNumber), "예외가 나오면 안됨.");
    }

    static Stream<Arguments> invalidWinningNumberCreateProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7), 8, "6개"),
                Arguments.of(List.of(1, 2, 3, 4, 5), 8, "6개"),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6, "보너스 숫자"),
                Arguments.of(List.of(45, 44, 43, 42, 41, 41), 39, "중복된 숫자"),
                Arguments.of(List.of(0, 2, 3, 4, 5, 6), 8, "1 미만"),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46), 8, "45 초과")
        );
    }

    @ParameterizedTest(name = "numbers:{0} bonus:{1}")
    @DisplayName("WinningNumber 생성 실패")
    @MethodSource("invalidWinningNumberCreateProvider")
    void test2(List<Integer> regularNumbers, int bonusNumber, String containingMessage) {
        // when & then
        assertThatThrownBy(() -> new WinningNumber(regularNumbers, bonusNumber))
                .as("입력값 에러")
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining(containingMessage);
    }
}