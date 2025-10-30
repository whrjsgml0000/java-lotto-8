package lotto.model.domain.lotto.factory;

import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.domain.lotto.factory.impl.LottoFactoryImpl;

public interface LottoFactory {
    Lotto create();
    static LottoFactory defaultSetting() {
        return new LottoFactoryImpl();
    }
}
