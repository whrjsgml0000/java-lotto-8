package lotto.model.service.impl;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_PRICE;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.model.domain.lotto.constant.Winning;
import lotto.model.domain.lotto.dto.req.AddBonusNumberDTO;
import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.req.GenerateWinningNumberDTO;
import lotto.model.domain.lotto.dto.res.LottoDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.domain.lotto.dto.res.MatchResultDTO;
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

    @Override
    public WinningNumber addBonusNumber(GenerateWinningNumberDTO generateWinningNumberDTO,
                                        AddBonusNumberDTO addBonusNumberDTO) {
        List<Integer> regularNumbers = generateWinningNumberDTO.getNumbers();
        int bonusNumber = addBonusNumberDTO.getBonusNumber();
        WinningNumber.validate(regularNumbers, bonusNumber);
        return new WinningNumber(regularNumbers, bonusNumber);
    }

    @Override
    public MatchResultDTO match(LottoDTOs lottoDTOs, WinningNumber winningNumber) {
        Map<Winning, Integer> winningsCount = new EnumMap<>(Winning.class);
        lottoDTOs.getLottoDTOs().stream()
                .map(LottoDTO::getLottoNumbers)
                .map(winningNumber::match)
                .forEach(winning -> winningsCount.merge(winning, 1, Integer::sum));

        BigDecimal profitRate = lottoCalculator.calculateProfitRate(winningsCount, lottoDTOs.getLottoCount() * DEFAULT_LOTTO_PRICE.value());
        return new MatchResultDTO(winningsCount, profitRate);
    }
}
