package lotto.model.domain.lotto.factory.impl;

import java.util.List;
import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.domain.lotto.factory.LottoFactory;
import lotto.model.util.RandomNumberGenerator;

public class LottoFactoryImpl implements LottoFactory {

    private final int lottoStartNumber;
    private final int lottoEndNumber;
    private final int lottoNumberCount;

    public LottoFactoryImpl(int lottoStartNumber, int lottoEndNumber, int lottoNumberCount) {
        this.lottoStartNumber = lottoStartNumber;
        this.lottoEndNumber = lottoEndNumber;
        this.lottoNumberCount = lottoNumberCount;
    }

    @Override
    public Lotto create() {
        List<Integer> list = RandomNumberGenerator.getUniqueNumbers(lottoStartNumber, lottoEndNumber, lottoNumberCount);
        return new Lotto(list);
    }
}
