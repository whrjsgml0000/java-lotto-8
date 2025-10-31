package lotto.model.domain.lotto.util.impl;

import static lotto.exception.Error.CANT_DIVIDE_MONEY_CLEARLY;
import static lotto.exception.Error.PURCHASE_MONEY_CANT_BE_NEGATIVE;
import static lotto.exception.Error.PURCHASE_MONEY_SHOULD_BE_LARGER_THAN_LOTTO_PRICE;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_PRICE;

import lotto.model.domain.lotto.util.LottoCalculator;

public class LottoCalculatorImpl implements LottoCalculator {

    @Override
    public long calculatePurchasableLottoCount(long totalInputMoney) {
        validatePositiveInputMoney(totalInputMoney);
        validatePurchasableMoneyAtLeastOneLotto(totalInputMoney);
        validateDividableInputMoney(totalInputMoney);
        return totalInputMoney / DEFAULT_LOTTO_PRICE.value();
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
