package lotto.model.domain.lotto.dto.req;

import static lotto.exception.Error.INPUT_BONUS_NUMBER_SHOULD_BE_NUMBER;

import lotto.view.mapper.Mappable;

public class AddBonusNumberDTO implements Mappable<AddBonusNumberDTO> {

    private int bonusNumber;

    public AddBonusNumberDTO() {

    }

    @Override
    public AddBonusNumberDTO mapping(String input) {
        try {
            this.bonusNumber = Integer.parseInt(input);
            return this;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_BONUS_NUMBER_SHOULD_BE_NUMBER.message());
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
