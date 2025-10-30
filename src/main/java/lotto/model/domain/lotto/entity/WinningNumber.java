package lotto.model.domain.lotto.entity;

import java.util.List;

public class WinningNumber {

    private final List<Integer> regularNumbers;
    private final int bonusNumber;

    public WinningNumber(List<Integer> regularNumbers, int bonusNumber) {
        validate(regularNumbers, bonusNumber);
        this.regularNumbers = regularNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> regularNumbers, int bonusNumber) {

    }
}
