package lotto.model.domain.lotto.factory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import lotto.model.domain.lotto.entity.Lotto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    LottoFactory lottoFactory;

    @Test
    @DisplayName("유효한 번호를 가진 로또 생성 성공")
    void create() {
        // given
        lottoFactory = LottoFactory.defaultSetting();

        // when
        Lotto lotto = lottoFactory.create();

        // then
        assertThat(lotto)
                .as("lotto 객체가 null 이면 안됨.")
                .isNotNull();

        SoftAssertions soft = new SoftAssertions();

        List<Integer> numbers = lotto.getNumbers();
        soft.assertThat(numbers)
                .as("로또 번호 리스트가 null 이면 안됨.")
                .isNotNull();

        soft.assertThat(numbers)
                .as("정확히 6개의 숫자를 가져야 됨.")
                .hasSize(6);

        soft.assertThat(Set.copyOf(numbers))
                .as("중복된 값이 없어야 함.")
                .hasSize(6);

        for (int number : numbers) {
            soft.assertThat(number)
                    .as("각 숫자는 1과 45 사이의 숫자여야 함.")
                    .isBetween(1, 45);
        }

        soft.assertAll();
    }
}