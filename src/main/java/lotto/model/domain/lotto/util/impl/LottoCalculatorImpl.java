package lotto.model.domain.lotto.util.impl;

import static lotto.exception.Error.CANT_DIVIDE_MONEY_CLEARLY;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_PRICE;

import lotto.model.domain.lotto.util.LottoCalculator;

public class LottoCalculatorImpl implements LottoCalculator {

    @Override
    public long calculatePurchasableLottoCount(long totalInputMoney) {
        validateDividableInputMoney(totalInputMoney);
        return totalInputMoney / DEFAULT_LOTTO_PRICE.value();
    }

    private static void validateDividableInputMoney(long inputMoney) {
        if(inputMoney % DEFAULT_LOTTO_PRICE.value() != 0) {
            throw new IllegalArgumentException(CANT_DIVIDE_MONEY_CLEARLY.message());
        }
    }
}
