package lotto.model.domain.lotto.constant;

import java.math.BigInteger;
import java.util.Map;

public enum Winning {
    FIRST(BigInteger.valueOf(2_000_000_000L)),
    SECOND(BigInteger.valueOf(30_000_000L)),
    THIRD(BigInteger.valueOf(1_500_000L)),
    FOURTH(BigInteger.valueOf(50_000L)),
    FIFTH(BigInteger.valueOf(5_000L)),
    ETC(BigInteger.ZERO)
    ;
    private final BigInteger jackpot;

    Winning(BigInteger jackpot) {
        this.jackpot = jackpot;
    }

    public BigInteger getJackpot() {
        return jackpot;
    }

    private static final Map<Integer, Winning> MATCHES = Map.of(
            6, FIRST,
            5, THIRD,
            4, FOURTH,
            3, FIFTH
    );

    public static Winning from (int count) {
        return MATCHES.getOrDefault(count, ETC);
    }
}
