package lotto.model.domain.lotto.dto.res;

import java.math.BigDecimal;
import java.util.Map;
import lotto.model.domain.lotto.constant.Winning;

public class MatchResultDTO {

    private final Map<Winning, Integer> winningsCount;
    private final BigDecimal profitRate;

    public MatchResultDTO(Map<Winning, Integer> winningsCount, BigDecimal profitRate) {
        this.winningsCount = winningsCount;
        this.profitRate = profitRate;
    }

    public Map<Winning, Integer> getWinningsCount() {
        return Map.copyOf(winningsCount);
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
