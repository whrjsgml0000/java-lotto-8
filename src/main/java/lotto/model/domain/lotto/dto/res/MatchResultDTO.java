package lotto.model.domain.lotto.dto.res;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.StringJoiner;
import lotto.model.domain.lotto.constant.Winning;

public class MatchResultDTO {

    private static final String STATISTIC_FORMAT = "%d개 일치%s (%,d원) - %d개";
    private static final String BONUS_BALL_MATCH = ", 보너스 볼 일치";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %,.1f%%입니다.";
    private final Map<Winning, Integer> winningsCount;
    private final BigDecimal profitRate;

    public MatchResultDTO(Map<Winning, Integer> winningsCount, BigDecimal profitRate) {
        this.winningsCount = winningsCount;
        this.profitRate = profitRate;
    }

    @Override
    public String toString() {
        StringJoiner matchResult = new StringJoiner(System.lineSeparator());
        matchResult.add(formatting(3, "", Winning.FIFTH));
        matchResult.add(formatting(4, "", Winning.FOURTH));
        matchResult.add(formatting(5, "", Winning.THIRD));
        matchResult.add(formatting(5, BONUS_BALL_MATCH, Winning.SECOND));
        matchResult.add(formatting(6, "", Winning.FIRST));
        matchResult.add(PROFIT_RATE_FORMAT.formatted(profitRate.setScale(1, RoundingMode.HALF_UP)));

        return matchResult.toString();
    }

    private String formatting(int matchCount, String bonusBallPrint, Winning winning) {
        return STATISTIC_FORMAT.formatted(matchCount, bonusBallPrint, winning.getJackpot(),
                winningsCount.getOrDefault(winning, 0));
    }
}
