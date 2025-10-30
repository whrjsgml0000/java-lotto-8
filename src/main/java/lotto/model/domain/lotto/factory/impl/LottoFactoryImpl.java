package lotto.model.domain.lotto.factory.impl;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

import java.util.List;
import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.domain.lotto.factory.LottoFactory;
import lotto.model.util.RandomNumberGenerator;

public class LottoFactoryImpl implements LottoFactory {

    @Override
    public Lotto create() {
        List<Integer> list = RandomNumberGenerator.getUniqueNumbers(DEFAULT_LOTTO_START_NUMBER.value(),
                DEFAULT_LOTTO_END_NUMBER.value(), DEFAULT_LOTTO_NUMBER_COUNT.value());
        return new Lotto(list);
    }
}
