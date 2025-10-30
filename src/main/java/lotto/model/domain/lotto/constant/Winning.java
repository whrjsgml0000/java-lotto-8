package lotto.model.domain.lotto.constant;

import java.math.BigInteger;
import java.util.Map;

public enum Winning {
    FIRST(new BigInteger("2000000000")),
    SECOND(new BigInteger("30000000")),
    THIRD(new BigInteger("1500000")),
    FOURTH(new BigInteger("50000")),
    FIFTH(new BigInteger("5000")),
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
