package lotto.model.domain.lotto.dto.res;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDTOs {

    private final List<LottoDTO> lottoDTOs;

    public LottoDTOs() {
        this.lottoDTOs = new ArrayList<>();
    }

    public void add(LottoDTO lottoDTO) {
        lottoDTOs.add(lottoDTO);
    }

    public void addAll(LottoDTO... lottoDTOs) {
        this.lottoDTOs.addAll(List.of(lottoDTOs));
    }

    public int getLottoCount() {
        return lottoDTOs.size();
    }

    @Override
    public String toString() {
        return lottoDTOs.stream()
                .map(LottoDTO::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
