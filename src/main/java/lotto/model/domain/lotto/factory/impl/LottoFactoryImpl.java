package lotto.model.domain.lotto.factory.impl;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.domain.lotto.factory.LottoFactory;

public class LottoFactoryImpl implements LottoFactory {

    private final int lottoStartNumber;
    private final int lottoEndNumber;
    private final int lottoNumberCount;

    public static LottoFactoryImpl defaultSetting() {
        return new LottoFactoryImpl(DEFAULT_LOTTO_START_NUMBER.value(), DEFAULT_LOTTO_END_NUMBER.value(),
                DEFAULT_LOTTO_NUMBER_COUNT.value());
    }

    public LottoFactoryImpl(int lottoStartNumber, int lottoEndNumber, int lottoNumberCount) {
        this.lottoStartNumber = lottoStartNumber;
        this.lottoEndNumber = lottoEndNumber;
        this.lottoNumberCount = lottoNumberCount;
    }

    @Override
    public Lotto create() {
        List<Integer> list = Randoms.pickUniqueNumbersInRange(lottoStartNumber, lottoEndNumber, lottoNumberCount);
        return new Lotto(list);
    }
}
