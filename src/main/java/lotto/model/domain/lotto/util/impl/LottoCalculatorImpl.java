package lotto.model.domain.lotto.util.impl;

import static lotto.exception.Error.CANT_DIVIDE_MONEY_CLEARLY;
import static lotto.exception.Error.PURCHASE_MONEY_CANT_BE_NEGATIVE;
import static lotto.exception.Error.PURCHASE_MONEY_SHOULD_BE_LARGER_THAN_LOTTO_PRICE;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_PRICE;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Map;
import java.util.Map.Entry;
import lotto.model.domain.lotto.constant.Winning;
import lotto.model.domain.lotto.util.LottoCalculator;

public class LottoCalculatorImpl implements LottoCalculator {
    private static final int SCALE = 1;
    private static final BigDecimal TO_RATE = new BigDecimal(100);

    @Override
    public long calculatePurchasableLottoCount(long totalInputMoney) {
        validatePositiveInputMoney(totalInputMoney);
        validatePurchasableMoneyAtLeastOneLotto(totalInputMoney);
        validateDividableInputMoney(totalInputMoney);
        return totalInputMoney / DEFAULT_LOTTO_PRICE.value();
    }

    @Override
    public BigDecimal calculateProfitRate(Map<Winning, Integer> winningsCount, long totalInputMoney) {
        BigDecimal total = BigDecimal.ZERO;

        for (Entry<Winning, Integer> winningCount : winningsCount.entrySet()) {
            BigDecimal sum = calculateTotalWinningJackpot(winningCount);
            total = total.add(sum);
        }

        return total.divide(BigDecimal.valueOf(totalInputMoney), MathContext.DECIMAL64).multiply(TO_RATE);
    }

    private static BigDecimal calculateTotalWinningJackpot(Entry<Winning, Integer> winningCount) {
        BigInteger winningJackpot = winningCount.getKey().getJackpot();
        BigInteger jackpotCount = BigInteger.valueOf(winningCount.getValue());
        return new BigDecimal(winningJackpot.multiply(jackpotCount));
    }

    private static void validatePositiveInputMoney(long inputMoney) {
        if (inputMoney < 0) {
            throw new IllegalArgumentException(PURCHASE_MONEY_CANT_BE_NEGATIVE.message());
        }
    }

    private static void validatePurchasableMoneyAtLeastOneLotto(long inputMoney) {
        if (inputMoney < DEFAULT_LOTTO_PRICE.value()) {
            throw new IllegalArgumentException(PURCHASE_MONEY_SHOULD_BE_LARGER_THAN_LOTTO_PRICE.message());
        }
    }

    private static void validateDividableInputMoney(long inputMoney) {
        if (inputMoney % DEFAULT_LOTTO_PRICE.value() != 0) {
            throw new IllegalArgumentException(CANT_DIVIDE_MONEY_CLEARLY.message());
        }
    }
}
