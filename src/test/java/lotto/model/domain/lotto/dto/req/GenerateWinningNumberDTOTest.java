package lotto.model.domain.lotto.dto.req;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class GenerateWinningNumberDTOTest {

    static Stream<String> failMappingProvider() {
        return Stream.of(
                "a,1,2,3,4,5",
                "1,2,3,4,5 ,6",
                ","
        );
    }

    @ParameterizedTest(name = "mapping value:{0}")
    @DisplayName("매핑 실패")
    @NullAndEmptySource
    @MethodSource("failMappingProvider")
    void mapping(String fail) {
        // given
        GenerateWinningNumberDTO generateWinningNumberDTO = new GenerateWinningNumberDTO();

        // when & then
        assertThatThrownBy(() -> generateWinningNumberDTO.mapping(fail))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}