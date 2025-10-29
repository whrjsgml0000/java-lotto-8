package lotto.model.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator {

    private RandomNumberGenerator() {}

    public static List<Integer> getUniqueNumbers(int start, int end, int count) {
        return Randoms.pickUniqueNumbersInRange(start, end, count);
    }
}
