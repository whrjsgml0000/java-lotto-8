package lotto.model.domain.lotto.entity;

import static lotto.exception.Error.INVALID_BONUS_NUMBER_RANGE;
import static lotto.exception.Error.INVALID_REGULAR_NUMBERS_RANGE;
import static lotto.exception.Error.INVALID_REGULAR_NUMBERS_SIZE;
import static lotto.exception.Error.NOT_UNIQUE_BONUS_NUMBER;
import static lotto.exception.Error.NOT_UNIQUE_REGULAR_NUMBERS;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.model.domain.lotto.constant.Winning;

public class WinningNumber {

    private final List<Integer> regularNumbers;
    private final int bonusNumber;

    public WinningNumber(List<Integer> regularNumbers, int bonusNumber) {
        validate(regularNumbers, bonusNumber);
        this.regularNumbers = regularNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static void validate(List<Integer> regularNumbers, int bonusNumber) {
        validateRegularNumbers(regularNumbers);
        validateBonusNumber(bonusNumber);
        validateBonusNumberUnique(regularNumbers, bonusNumber);
    }

    public static void validateRegularNumbers(List<Integer> regularNumbers) {
        validateRegularNumbersSize(regularNumbers);
        validateRegularNumbersRange(regularNumbers);
        validateRegularNumbersUnique(regularNumbers);
    }

    public static void validateBonusNumber(int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
    }

    public Winning match(List<Integer> lottoNumbers) {
        Set<Integer> jackpotNumbers = new HashSet<>(regularNumbers);
        jackpotNumbers.retainAll(new HashSet<>(lottoNumbers));
        int matchCount = jackpotNumbers.size();

        Winning result = Winning.from(matchCount);
        result = matchBonusNumber(result, lottoNumbers);

        return result;
    }

    private Winning matchBonusNumber(Winning result, List<Integer> lottoNumbers) {
        if (result == Winning.THIRD && lottoNumbers.contains(bonusNumber)) {
            result = Winning.SECOND;
        }

        return result;
    }

    private static void validateRegularNumbersSize(List<Integer> regularNumbers) {
        if (regularNumbers.size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException(INVALID_REGULAR_NUMBERS_SIZE.message());
        }
    }

    private static void validateRegularNumbersRange(List<Integer> regularNumbers) {
        if (!regularNumbers.stream().allMatch(WinningNumber::validateNumberRange)) {
            throw new IllegalArgumentException(INVALID_REGULAR_NUMBERS_RANGE.message());
        }
    }

    private static void validateRegularNumbersUnique(List<Integer> regularNumbers) {
        if (Set.copyOf(regularNumbers).size() != DEFAULT_LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException(NOT_UNIQUE_REGULAR_NUMBERS.message());
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (!validateNumberRange(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_RANGE.message());
        }
    }

    private static boolean validateNumberRange(int number) {
        return number >= DEFAULT_LOTTO_START_NUMBER.value() && number <= DEFAULT_LOTTO_END_NUMBER.value();
    }

    private static void validateBonusNumberUnique(List<Integer> regularNumbers, int bonusNumber) {
        if (regularNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(NOT_UNIQUE_BONUS_NUMBER.message());
        }
    }
}
