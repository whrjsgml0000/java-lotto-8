package lotto.model.service;

import lotto.model.domain.lotto.dto.req.AddBonusNumberDTO;
import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.req.GenerateWinningNumberDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.domain.lotto.dto.res.MatchResultDTO;
import lotto.model.domain.lotto.entity.WinningNumber;

public interface LottoService {
    LottoDTOs generateLotto(GenerateLottoDTO generateLottoDTO);
    void checkValidWinningNumber(GenerateWinningNumberDTO generateWinningNumberDTO);
    WinningNumber addBonusNumber(GenerateWinningNumberDTO generateWinningNumberDTO, AddBonusNumberDTO addBonusNumberDTO);
    MatchResultDTO match(LottoDTOs lottoDTOs, WinningNumber winningNumber);
}
