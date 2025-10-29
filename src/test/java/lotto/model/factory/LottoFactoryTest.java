package lotto.model.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import lotto.model.domain.lotto.entity.Lotto;
import lotto.model.factory.impl.LottoFactoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    LottoFactory lottoFactory;

    @Test
    @DisplayName("유효한 번호를 가진 로또 생성 성공")
    void create() {
        // given
        lottoFactory = new LottoFactoryImpl();

        // when
        Lotto lotto = lottoFactory.create();

        // then
        assertThat(lotto)
                .as("lotto 객체가 null 이면 안됨.")
                .isNotNull();

        List<Integer> numbers = lotto.getNumbers();
        assertThat(numbers)
                .as("로또 번호 리스트가 null 이면 안됨.")
                .isNotNull();

        assertThat(numbers)
                .as("정확히 6개의 숫자를 가져야 됨.")
                .hasSize(6);

        assertThat(Set.copyOf(numbers))
                .as("중복된 값이 없어야 함.")
                .hasSize(6);

        for (int number : numbers) {
            assertThat(number)
                    .as("각 숫자는 1과 45 사이의 숫자여야 함.")
                    .isBetween(1, 45);
        }
    }
}