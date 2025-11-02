package lotto.controller;

import lotto.controller.message.Request;
import lotto.controller.message.ResponseFormat;
import lotto.model.domain.lotto.dto.req.AddBonusNumberDTO;
import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.req.GenerateWinningNumberDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.domain.lotto.dto.res.MatchResultDTO;
import lotto.model.domain.lotto.entity.WinningNumber;
import lotto.model.service.LottoService;
import lotto.view.IOHandler;

public class LottoController {

    private final IOHandler ioHandler;
    private final LottoService lottoService;

    public LottoController(IOHandler ioHandler, LottoService lottoService) {
        this.ioHandler = ioHandler;
        this.lottoService = lottoService;
    }

    public void run() {
        LottoDTOs lottoDTOs = generateLotto();
        GenerateWinningNumberDTO generateWinningNumberDTO = getGenerateWinningNumber();
        WinningNumber winningNumber = addBonusNumber(generateWinningNumberDTO);
        MatchResultDTO match = lottoService.match(lottoDTOs, winningNumber);
        ioHandler.printMatchResultDTO(match);
    }

    private LottoDTOs generateLotto() {
        while (true) {
            try {
                GenerateLottoDTO generateLottoDTO = ioHandler.requestMappableDTOWithMessage(GenerateLottoDTO.class,
                        Request.PURCHASE_MONEY.message());
                LottoDTOs lottoDTOs = lottoService.generateLotto(generateLottoDTO);
                ioHandler.printLottoDTOs(ResponseFormat.PURCHASE_COUNT.formatted(lottoDTOs.getLottoCount()), lottoDTOs,
                        1);
                return lottoDTOs;
            } catch (Exception e) {
                ioHandler.printError(e);
            }
        }
    }

    private GenerateWinningNumberDTO getGenerateWinningNumber() {
        while (true) {
            try {
                GenerateWinningNumberDTO generateWinningNumberDTO = ioHandler.requestMappableDTOWithMessage(
                        GenerateWinningNumberDTO.class, Request.WINNING_NUMBER.message(), 1);

                lottoService.checkValidWinningNumber(generateWinningNumberDTO);
                return generateWinningNumberDTO;
            } catch (Exception e) {
                ioHandler.printError(e);
            }
        }
    }

    private WinningNumber addBonusNumber(GenerateWinningNumberDTO generateWinningNumberDTO) {
        while (true) {
            try {
                AddBonusNumberDTO addBonusNumberDTO = ioHandler.requestMappableDTOWithMessage(AddBonusNumberDTO.class,
                        Request.BONUS_NUMBER.message());
                return lottoService.addBonusNumber(generateWinningNumberDTO, addBonusNumberDTO);
            } catch (Exception e) {
                ioHandler.printError(e);
            }
        }
    }
}
