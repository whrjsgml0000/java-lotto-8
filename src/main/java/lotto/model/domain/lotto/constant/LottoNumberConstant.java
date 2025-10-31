package lotto.model.domain.lotto.constant;

public enum LottoNumberConstant {
    DEFAULT_LOTTO_START_NUMBER(1),
    DEFAULT_LOTTO_END_NUMBER(45),
    DEFAULT_LOTTO_NUMBER_COUNT(6),
    DEFAULT_LOTTO_PRICE(1_000)
    ;
    private final int value;

    LottoNumberConstant(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
