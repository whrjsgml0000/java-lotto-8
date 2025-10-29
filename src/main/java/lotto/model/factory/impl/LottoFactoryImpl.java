package lotto.model.factory.impl;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.factory.LottoFactory;

public class LottoFactoryImpl implements LottoFactory {

    @Override
    public Lotto create() {
        List<Integer> list = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(list);
    }
}
