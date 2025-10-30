package lotto.model.domain.lotto.entity;

import static lotto.exception.Error.INVALID_LOTTO_NUMBERS_RANGE;
import static lotto.exception.Error.INVALID_LOTTO_NUMBERS_SIZE;
import static lotto.exception.Error.NOT_UNIQUE_LOTTO_NUMBERS;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateLottoNumbersSize(numbers);
        validateLottoNumbersUnique(numbers);
        validateLottoNumbersRange(numbers);
    }

    private static void validateLottoNumbersSize(List<Integer> numbers) {
        if (numbers.size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalStateException(INVALID_LOTTO_NUMBERS_SIZE.message());
        }
    }

    private static void validateLottoNumbersUnique(List<Integer> numbers) {
        if (Set.copyOf(numbers).size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalStateException(NOT_UNIQUE_LOTTO_NUMBERS.message());
        }
    }

    private static void validateLottoNumbersRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(
                number -> number < DEFAULT_LOTTO_START_NUMBER.value() || number > DEFAULT_LOTTO_END_NUMBER.value())) {
            throw new IllegalStateException(INVALID_LOTTO_NUMBERS_RANGE.message());
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
