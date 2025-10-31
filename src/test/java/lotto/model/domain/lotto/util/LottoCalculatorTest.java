package lotto.model.domain.lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
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
}