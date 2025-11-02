package lotto.view.mapper;

import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObjectMapperTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("GenerateLottoDTO 숫자 매핑 되는지 확인")
    void mapping() {
        // when & then
        assertThatCode(() -> objectMapper.mapping(GenerateLottoDTO.class, "8"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("GenerateLottoDTO 숫자 매핑 실패 확인")
    void mapping1() {
        assertThatCode(() -> objectMapper.mapping(GenerateLottoDTO.class, "asd"))
                .hasMessageStartingWith("[ERROR]");
    }
}