package lotto.model.domain.lotto.dto.res;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lotto.model.domain.lotto.entity.Lotto;

public class LottoDTO {

    private final List<Integer> lottoNumbers;

    public LottoDTO(List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoDTO toDTO(Lotto lotto) {
        return new LottoDTO(lotto.getNumbers());
    }

    @Override
    public String toString() {
        lottoNumbers.sort(Comparator.naturalOrder());
        return lottoNumbers.toString();
    }
}
