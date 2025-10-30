package lotto.exception;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_START_NUMBER;

public enum Error {
    INVALID_REGULAR_NUMBERS_SIZE("당첨 번호는 " + DEFAULT_LOTTO_NUMBER_COUNT.value() + "개만 입력 가능합니다."),
    NOT_UNIQUE_REGULAR_NUMBERS("당첨 번호는 중복된 번호를 가져선 안됩니다."),
    INVALID_REGULAR_NUMBERS_RANGE(
            "당첨 번호는 " + DEFAULT_LOTTO_START_NUMBER.value() + "와 " + DEFAULT_LOTTO_END_NUMBER.value() + "사이의 번호여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 " + DEFAULT_LOTTO_START_NUMBER.value() + "와 " + DEFAULT_LOTTO_END_NUMBER.value()
            + "사이의 번호여야 합니다."),
    NOT_UNIQUE_BONUS_NUMBER("보너스 번호가 당첨 번호와 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBERS_SIZE("로또 번호는 6개여야 합니다."),
    NOT_UNIQUE_LOTTO_NUMBERS("로또 번호는 중복될 수 없습니다.")
    ;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String message() {
        return ERROR_PREFIX + message;
    }
}
