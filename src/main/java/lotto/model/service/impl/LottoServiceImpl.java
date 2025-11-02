package lotto.model.service.impl;

import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.req.GenerateWinningNumberDTO;
import lotto.model.domain.lotto.dto.res.LottoDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.domain.lotto.entity.WinningNumber;
import lotto.model.domain.lotto.factory.LottoFactory;
import lotto.model.domain.lotto.util.LottoCalculator;
import lotto.model.service.LottoService;

public class LottoServiceImpl implements LottoService {

    private final LottoFactory lottoFactory;
    private final LottoCalculator lottoCalculator;

    public LottoServiceImpl(LottoFactory lottoFactory, LottoCalculator lottoCalculator) {
        this.lottoFactory = lottoFactory;
        this.lottoCalculator = lottoCalculator;
    }

    @Override
    public LottoDTOs generateLotto(GenerateLottoDTO generateLottoDTO) {
        long lottoCount = lottoCalculator.calculatePurchasableLottoCount(generateLottoDTO.getMoney());
        LottoDTOs lottoDTOs = new LottoDTOs();
        for (long l = 0; l < lottoCount; l++) {
            lottoDTOs.add(LottoDTO.toDTO(lottoFactory.create()));
        }
        return lottoDTOs;
    }

    @Override
    public void checkValidWinningNumber(GenerateWinningNumberDTO generateWinningNumberDTO) {
        WinningNumber.validateRegularNumbers(generateWinningNumberDTO.getNumbers());
    }
}
