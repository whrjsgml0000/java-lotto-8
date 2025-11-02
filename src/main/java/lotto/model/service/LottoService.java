package lotto.model.service;

import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.service.impl.LottoServiceImpl;

public interface LottoService {
    LottoDTOs generateLotto(GenerateLottoDTO generateLottoDTO);

    static LottoService defaultService() {
        return new LottoServiceImpl();
    }
}
