package lotto.model.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {

    @Test
    void getUniqueNumbers() {
        // given
        int start = 1;
        int end = 45;
        int count = 6;

        // when
        List<Integer> uniqueNumbers = RandomNumberGenerator.getUniqueNumbers(start, end, count);

        // then
        assertThat(uniqueNumbers)
                .as("설정한 개수(%d) 만큼 뽑아야됨.", count)
                .hasSize(count);

        assertThat(Set.copyOf(uniqueNumbers))
                .as("겹치는 수가 존재해선 안됨.")
                .hasSize(count);

        for (int uniqueNumber : uniqueNumbers) {
            assertThat(uniqueNumber)
                    .as("설정된 시작값(%d)와 끝값(%d) 사이의 숫자여야 됨.", start, end)
                    .isBetween(start, end);
        }
    }
}