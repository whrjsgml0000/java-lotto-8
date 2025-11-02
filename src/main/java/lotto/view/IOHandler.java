package lotto.view;

import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.domain.lotto.dto.res.MatchResultDTO;
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

    public <T extends Mappable<T>> T requestMappableDTOWithMessage(Class<T> requiredClassType, String message) {
        output.println(message);
        String read = input.readLine();
        output.lineSeparate(1);
        return objectMapper.mapping(requiredClassType, read);
    }

    public void printLottoDTOs(String prefixMessage, LottoDTOs lottoDTOs) {
        output.println(prefixMessage);
        output.println(lottoDTOs.toString());
        output.lineSeparate(1);
    }

    public void printError(Exception e) {
        output.println(e.getMessage());
        output.lineSeparate(1);
    }

    public void printMatchResultDTO(String prefixMessage, MatchResultDTO match) {
        output.println(prefixMessage);
        output.println(match.toString());
        output.lineSeparate(1);
    }
}
