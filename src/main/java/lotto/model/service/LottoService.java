package lotto.model.service;

import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.req.GenerateWinningNumberDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;

public interface LottoService {
    LottoDTOs generateLotto(GenerateLottoDTO generateLottoDTO);
    void checkValidWinningNumber(GenerateWinningNumberDTO generateWinningNumberDTO);
}
