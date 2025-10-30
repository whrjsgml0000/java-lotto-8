package lotto.model.domain.lotto.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;
import lotto.model.domain.lotto.constant.Winning;
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
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6, "보너스 번호"),
                Arguments.of(List.of(45, 44, 43, 42, 41, 41), 39, "중복된 번호"),
                Arguments.of(List.of(0, 2, 3, 4, 5, 6), 8, "1"),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46), 8, "45"),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 0, "1"),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 46, "45")
        );
    }

    @ParameterizedTest(name = "numbers:{0} bonus:{1}")
    @DisplayName("WinningNumber 생성 실패")
    @MethodSource("invalidWinningNumberCreateProvider")
    void test2(List<Integer> regularNumbers, int bonusNumber, String containingMessage) {
        // when & then
        assertThatThrownBy(() -> new WinningNumber(regularNumbers, bonusNumber))
                .as(containingMessage)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining(containingMessage);
    }

    WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);

    static Stream<Arguments> lottoNumberProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), Winning.FIRST),
                Arguments.of(List.of(2, 3, 4, 5, 6, 7), Winning.SECOND),
                Arguments.of(List.of(2, 3, 4, 5, 6, 8), Winning.THIRD),
                Arguments.of(List.of(3, 4, 5, 6, 7, 8), Winning.FOURTH),
                Arguments.of(List.of(4, 5, 6, 7, 8, 9), Winning.FIFTH),
                Arguments.of(List.of(5, 6, 7, 8, 9, 10), Winning.ETC)
        );
    }

    @ParameterizedTest(name = "lotto number:{0}, winning:{1}")
    @DisplayName("로또 번호 맞추고 등수 확인")
    @MethodSource("lottoNumberProvider")
    void test3(List<Integer> lottoNumber, Winning expectedResult) {
        // when
        Winning matchResult = winningNumber.match(lottoNumber);

        // then
        assertThat(matchResult)
                .as("등수가 일치해야됨.")
                .isEqualTo(expectedResult);
    }
}