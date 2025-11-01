package lotto.controller;

import lotto.controller.message.Request;
import lotto.view.IOHandler;

public class LottoController {

    private final IOHandler ioHandler;

    public LottoController(IOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    public void run() {
        ioHandler.requestInputWithMessage(Request.PURCHASE_MONEY.message());
        ioHandler.requestInputWithMessage(Request.WINNING_NUMBER.message(), 1);
        ioHandler.requestInputWithMessage(Request.BONUS_NUMBER.message(), 1);
    }
}
