package lotto.view;

import lotto.model.domain.lotto.dto.res.LottoDTOs;

public class IOHandler {
    private final Input input;
    private final Output output;

    public IOHandler(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public String requestInputWithMessage(String message) {
        output.println(message);
        return input.readLine();
    }

    public String requestInputWithMessage(String message, int prefixLineSeparatorCount) {
        output.lineSeparate(prefixLineSeparatorCount);
        return requestInputWithMessage(message);
    }

    public void printMessage(String message) {
        output.println(message);
    }

    public void printMessage(String message, int prefixLineSeparatorCount) {
        output.lineSeparate(prefixLineSeparatorCount);
        printMessage(message);
    }

    public void printLottoDTOs(String prefixMessage, LottoDTOs lottoDTOs) {
        output.println(prefixMessage);
        output.println(lottoDTOs.toString());
    }
}
