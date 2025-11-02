package lotto.model.domain.lotto.dto.req;

import static lotto.exception.Error.INPUT_PURCHASE_MONEY_SHOULD_BE_NUMBER;

public record GenerateLottoDTO(long money) {

    static GenerateLottoDTO mapping(String input) {
        try {
            long money = Long.parseLong(input);
            return new GenerateLottoDTO(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_PURCHASE_MONEY_SHOULD_BE_NUMBER.message());
        }
    }
}
