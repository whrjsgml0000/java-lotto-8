package lotto.controller;

import lotto.controller.message.Request;
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
        generateLotto();
        ioHandler.requestInputWithMessage(Request.WINNING_NUMBER.message(), 1);
        ioHandler.requestInputWithMessage(Request.BONUS_NUMBER.message(), 1);
    }

    private LottoDTOs generateLotto() {
        ioHandler.requestInputWithMessage(Request.PURCHASE_MONEY.message());
        // todo
        return lottoService.generateLotto(null);
    }
}
