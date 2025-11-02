package lotto.model.domain.lotto.dto.req;

import static lotto.exception.Error.INPUT_SHOULD_CONSIST_OF_NUMBER_AND_SEPARATOR;
import static lotto.exception.Error.INPUT_SHOULD_EXIST;

import java.util.Arrays;
import java.util.List;
import lotto.view.mapper.Mappable;

public class GenerateWinningNumberDTO implements Mappable<GenerateWinningNumberDTO> {

    private List<Integer> numbers;

    public GenerateWinningNumberDTO() {

    }

    @Override
    public GenerateWinningNumberDTO mapping(String input) {
        try {
            numbers = Arrays.stream(input.split(",", -1))
                    .map(Integer::parseInt)
                    .toList();
            return this;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(INPUT_SHOULD_EXIST.message());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_SHOULD_CONSIST_OF_NUMBER_AND_SEPARATOR.message());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
