package lotto.model.domain.lotto.dto.req;

import static lotto.exception.Error.INPUT_PURCHASE_MONEY_SHOULD_BE_NUMBER;

import lotto.view.mapper.Mappable;

public class GenerateLottoDTO implements Mappable<GenerateLottoDTO> {

    private long money;

    public GenerateLottoDTO() {

    }

    @Override
    public GenerateLottoDTO mapping(String input) {
        try {
            money = Long.parseLong(input);
            return this;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_PURCHASE_MONEY_SHOULD_BE_NUMBER.message());
        }
    }

    public long getMoney() {
        return money;
    }
}
