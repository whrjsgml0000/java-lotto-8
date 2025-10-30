package lotto.model.domain.lotto.entity;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

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
        validateRegularNumbersRange(regularNumbers);
        validateRegularNumbersUnique(regularNumbers);
        validateBonusNumberRange(bonusNumber);
    }

    private static void validateRegularNumbersSize(List<Integer> regularNumbers) {
        if (regularNumbers.size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 " + DEFAULT_LOTTO_NUMBER_COUNT.value() + "개만 입력 가능합니다.");
        }
    }

    private static void validateRegularNumbersUnique(List<Integer> regularNumbers) {
        if (Set.copyOf(regularNumbers).size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복된 번호를 가져선 안됩니다.");
        }
    }

    private static void validateRegularNumbersRange(List<Integer> regularNumbers) {
        if (!regularNumbers.stream().allMatch(WinningNumber::validateNumberRange)) {
            throw new IllegalArgumentException(
                    "[ERROR] 당첨 번호는 " + DEFAULT_LOTTO_START_NUMBER.value() + "와 " + DEFAULT_LOTTO_END_NUMBER.value()
                            + "사이의 번호여야 합니다.");
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (!validateNumberRange(bonusNumber)) {
            throw new IllegalArgumentException(
                    "[ERROR] 보너스 번호는 " + DEFAULT_LOTTO_START_NUMBER.value() + "와 " + DEFAULT_LOTTO_END_NUMBER.value()
                            + "사이의 번호여야 합니다.");
        }
    }

    private static boolean validateNumberRange(int number) {
        return number >= DEFAULT_LOTTO_START_NUMBER.value() && number <= DEFAULT_LOTTO_END_NUMBER.value();
    }
}
