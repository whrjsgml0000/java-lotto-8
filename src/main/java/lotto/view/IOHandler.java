package lotto.view;

import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.view.mapper.Mappable;
import lotto.view.mapper.ObjectMapper;

public class IOHandler {
    private final Input input;
    private final Output output;
    private final ObjectMapper objectMapper;

    public IOHandler(Input input, Output output, ObjectMapper objectMapper) {
        this.input = input;
        this.output = output;
        this.objectMapper = objectMapper;
    }

    public String requestInputWithMessage(String message) {
        output.println(message);
        return input.readLine();
    }

    public String requestInputWithMessage(String message, int prefixLineSeparatorCount) {
        output.lineSeparate(prefixLineSeparatorCount);
        return requestInputWithMessage(message);
    }

    public <T extends Mappable<T>> T requestMappableDTOWithMessage(Class<T> requiredClassType, String message) {
        output.println(message);
        String value = input.readLine();
        return objectMapper.mapping(requiredClassType, value);
    }

    public <T extends Mappable<T>> T requestMappableDTOWithMessage(Class<T> requiredClassType, String message,
                                                                   int prefixLineSeparatorCount) {
        output.lineSeparate(prefixLineSeparatorCount);
        return this.requestMappableDTOWithMessage(requiredClassType, message);
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

    public void printLottoDTOs(String prefixMessage, LottoDTOs lottoDTOs, int prefixLineSeparatorCount) {
        output.lineSeparate(prefixLineSeparatorCount);
        printLottoDTOs(prefixMessage, lottoDTOs);
    }

    public void printError(Exception e) {
        output.println(e.getMessage());
        output.lineSeparate(1);
    }
}
