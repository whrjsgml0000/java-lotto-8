package lotto.model.domain.lotto.factory;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.domain.lotto.factory.impl.LottoFactoryImpl;

public interface LottoFactory {
    Lotto create();
    static LottoFactory defaultSetting() {
        return new LottoFactoryImpl(DEFAULT_LOTTO_START_NUMBER.value(), DEFAULT_LOTTO_END_NUMBER.value(),
                DEFAULT_LOTTO_NUMBER_COUNT.value());
    }
}
