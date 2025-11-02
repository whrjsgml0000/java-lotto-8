package lotto.exception;

import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_END_NUMBER;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_NUMBER_COUNT;
import static lotto.model.domain.lotto.constant.LottoNumberConstant.DEFAULT_LOTTO_PRICE;
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
    NOT_UNIQUE_LOTTO_NUMBERS("로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBERS_RANGE(
            "로또 번호는 " + DEFAULT_LOTTO_START_NUMBER.value() + "와 " + DEFAULT_LOTTO_END_NUMBER.value() + "사이의 번호여야 합니다."),
    CANT_DIVIDE_MONEY_CLEARLY("구입 금액이 " + DEFAULT_LOTTO_PRICE.value() + "으로 나눠져야 합니다."),
    PURCHASE_MONEY_CANT_BE_NEGATIVE("구입 금액은 음수일 수 없습니다."),
    PURCHASE_MONEY_SHOULD_BE_LARGER_THAN_LOTTO_PRICE("구입 금액은 로또를 최소 한 개 이상 구입할 수 있는 금액이어야 합니다."),
    INPUT_PURCHASE_MONEY_SHOULD_BE_NUMBER("구입 금액은 숫자를 입력하셔야 합니다."),
    MAPPABLE_CLASS_SHOULD_HAVE_DEFAULT_CONSTRUCTOR("Mappable 클래스는 기본 생성자가 필요합니다."),
    MAPPABLE_CLASS_SHOULD_HAVE_PUBLIC_DEFAULT_CONSTRUCTOR("Mappable 클래스는 기본 생성자가 public 해야 합니다."),
    MAPPING_PROCESS_ERROR("Mapping 과정에서 문제가 발생했습니다."),
    INPUT_SHOULD_EXIST("입력값이 필요합니다."),
    INPUT_SHOULD_CONSIST_OF_NUMBER_AND_SEPARATOR("입력값은 숫자와 ,로만 구분되야 합니다.")
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
