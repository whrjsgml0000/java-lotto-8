package lotto.model.domain.lotto.entity;

import static lotto.exception.Error.INVALID_LOTTO_NUMBERS_SIZE;
import static lotto.exception.Error.NOT_UNIQUE_LOTTO_NUMBERS;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;

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

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
