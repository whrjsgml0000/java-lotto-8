package lotto.model.domain.lotto.util;

import lotto.model.domain.lotto.util.impl.LottoCalculatorImpl;

public interface LottoCalculator {

    long calculatePurchasableLottoCount(long totalInputMoney);
    static LottoCalculator defaultCalculator() {
        return new LottoCalculatorImpl();
    }
}
