package lotto.controller;

import lotto.controller.message.Request;
import lotto.controller.message.ResponseFormat;
import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
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
        ioHandler.requestInputWithMessage(Request.WINNING_NUMBER.message(), 1);
        ioHandler.requestInputWithMessage(Request.BONUS_NUMBER.message(), 1);
    }

    private LottoDTOs generateLotto() {
        GenerateLottoDTO generateLottoDTO = ioHandler.requestMappableDTOWithMessage(GenerateLottoDTO.class,
                Request.PURCHASE_MONEY.message());
        LottoDTOs lottoDTOs = lottoService.generateLotto(generateLottoDTO);
        ioHandler.printLottoDTOs(ResponseFormat.PURCHASE_COUNT.formatted(lottoDTOs.getLottoCount()), lottoDTOs, 1);
        return lottoDTOs;
    }
}
