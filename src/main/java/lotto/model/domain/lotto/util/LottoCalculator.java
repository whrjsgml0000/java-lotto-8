package lotto.model.domain.lotto.util;

import java.math.BigDecimal;
import java.util.Map;
import lotto.model.domain.lotto.constant.Winning;
import lotto.model.domain.lotto.util.impl.LottoCalculatorImpl;

public interface LottoCalculator {

    long calculatePurchasableLottoCount(long totalInputMoney);
    BigDecimal calculateProfitRate(Map<Winning, Integer> winningsCount, long totalInputMoney);
    static LottoCalculator defaultCalculator() {
        return new LottoCalculatorImpl();
    }
}
