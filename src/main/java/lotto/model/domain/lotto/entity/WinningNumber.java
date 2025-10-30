package lotto.model.domain.lotto.entity;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;

import java.util.List;
import java.util.Set;

public class WinningNumber {

    private final List<Integer> regularNumbers;
    private final int bonusNumber;

    public WinningNumber(List<Integer> regularNumbers, int bonusNumber) {
        validate(regularNumbers, bonusNumber);
        this.regularNumbers = regularNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> regularNumbers, int bonusNumber) {
        validateRegularNumbersSize(regularNumbers);
        validateRegularNumbersUnique(regularNumbers);
    }

    private static void validateRegularNumbersSize(List<Integer> regularNumbers) {
        if (regularNumbers.size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 " + DEFAULT_LOTTO_NUMBER_COUNT.value() + "개만 입력 가능합니다.");
        }
    }

    private static void validateRegularNumbersUnique(List<Integer> regularNumbers) {
        if (Set.copyOf(regularNumbers).size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복된 숫자를 가져선 안됩니다.");
        }
    }
}
