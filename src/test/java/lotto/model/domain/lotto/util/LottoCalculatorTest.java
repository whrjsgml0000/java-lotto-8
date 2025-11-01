package lotto.model.domain.lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;
import lotto.model.domain.lotto.constant.Winning;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoCalculatorTest {

    LottoCalculator lottoCalculator = LottoCalculator.defaultCalculator();

    @Nested
    @DisplayName("로또 구입 금액 변환")
    class 로또구입금액변환 {

        static Stream<Arguments> successTotalPurchaseMoneyProvider() {
            return Stream.of(
                    Arguments.of(1_000L, 1L),
                    Arguments.of(2_000L, 2L),
                    Arguments.of(5_000L, 5L),
                    Arguments.of(999_000L, 999L),
                    Arguments.of(123_000L, 123L)
            );
        }

        @ParameterizedTest(name = "money:{0}, expected count:{1}")
        @DisplayName("성공")
        @MethodSource("successTotalPurchaseMoneyProvider")
        void test1(long totalPurchaseMoney, long expectedPurchaseCount) {
            // when
            long purchasedLottoCount = lottoCalculator.calculatePurchasableLottoCount(totalPurchaseMoney);

            // then
            assertThat(purchasedLottoCount).isEqualTo(expectedPurchaseCount);
        }

        static Stream<Arguments> failTotalPurchaseMoneyProvider() {
            return Stream.of(
                    Arguments.of(0, "최소 한 개 이상"),
                    Arguments.of(1, "최소 한 개 이상"),
                    Arguments.of(1_001L, "1000으로"),
                    Arguments.of(2_100L, "1000으로"),
                    Arguments.of(5_010L, "1000으로"),
                    Arguments.of(999_999L, "1000으로"),
                    Arguments.of(-123_000L, "음수")
            );
        }

        @ParameterizedTest(name = "money:{0}, expected count:{1}")
        @DisplayName("실패")
        @MethodSource("failTotalPurchaseMoneyProvider")
        void test2(long totalPurchaseMoney, String containingMessage) {
            // when & then
            assertThatThrownBy(() -> lottoCalculator.calculatePurchasableLottoCount(totalPurchaseMoney))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageStartingWith("[ERROR]")
                    .hasMessageContaining(containingMessage);
        }
    }

    static Stream<Arguments> winningCountAndTotalInputMoneyProvider() {
        return Stream.of(
                Arguments.of(Map.of(Winning.FIRST, 1), 1_000L, 200_000_000.0D),
                Arguments.of(Map.of(Winning.SECOND, 1), 1_000L, 3_000_000.0D),
                Arguments.of(Map.of(Winning.THIRD, 1), 1_000L, 150_000.0D),
                Arguments.of(Map.of(Winning.FOURTH, 1), 1_000L, 5_000.0D),
                Arguments.of(Map.of(Winning.FIFTH, 1), 1_000L, 500.0D),
                Arguments.of(Map.of(Winning.FIFTH, 1), 3_000L, 166.7D),
                Arguments.of(Map.of(Winning.FIFTH, 1), 10_000_000L, 0.1D)
        );
    }

    @ParameterizedTest(name = "{0}, input:{1}")
    @DisplayName("수익률 계산")
    @MethodSource("winningCountAndTotalInputMoneyProvider")
    void test1(Map<Winning, Integer> winningCount, long totalInputMoney, double expectedProfitRate) {
        // when
        BigDecimal profitRate = lottoCalculator.calculateProfitRate(winningCount, totalInputMoney);

        // then
        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(profitRate.doubleValue())
                .as("수익률은 반올림되기 때문에 오차 범위는 0.05")
                .isEqualTo(expectedProfitRate, Offset.offset(0.05D));

        soft.assertAll();
    }
}