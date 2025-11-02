package lotto.model.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import lotto.model.domain.lotto.dto.req.AddBonusNumberDTO;
import lotto.model.domain.lotto.dto.req.GenerateLottoDTO;
import lotto.model.domain.lotto.dto.req.GenerateWinningNumberDTO;
import lotto.model.domain.lotto.dto.res.LottoDTOs;
import lotto.model.domain.lotto.entity.WinningNumber;
import lotto.model.domain.lotto.factory.LottoFactory;
import lotto.model.domain.lotto.util.LottoCalculator;
import lotto.model.service.LottoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoServiceImplTest {

    LottoService lottoService = new LottoServiceImpl(LottoFactory.defaultSetting(),
            LottoCalculator.defaultCalculator());

    @Nested
    @DisplayName("로또 발행 기능")
    class 로또발행기능 {

        static Stream<Arguments> lottoPurchaseMoneyProvider() {
            return Stream.of(
                    Arguments.of(1000, 1),
                    Arguments.of(2000, 2),
                    Arguments.of(5000, 5),
                    Arguments.of(111000, 111)
            );
        }

        @ParameterizedTest()
        @DisplayName("성공")
        @MethodSource("lottoPurchaseMoneyProvider")
        void success(long inputMoney, long expectedGeneratedLottoCount) {
            // given
            GenerateLottoDTO generateLottoDTO = new GenerateLottoDTO();
            generateLottoDTO.mapping(String.valueOf(inputMoney));

            // when
            LottoDTOs lottoDTOs = lottoService.generateLotto(generateLottoDTO);
            int lottoCount = lottoDTOs.getLottoCount();

            // then
            assertThat(lottoCount)
                    .as("1000원 단위로 로또 구매가 진행되야됨.")
                    .isEqualTo(expectedGeneratedLottoCount);
        }

        static Stream<Integer> failLottoPurchaseMoneyProvider() {
            return Stream.of(
                    1001, 2020, 3300, 111, -1
            );
        }

        @ParameterizedTest()
        @DisplayName("실패")
        @MethodSource("failLottoPurchaseMoneyProvider")
        void fail(long inputMoney) {
            // given
            GenerateLottoDTO generateLottoDTO = new GenerateLottoDTO();
            generateLottoDTO.mapping(String.valueOf(inputMoney));

            // when & then
            assertThatThrownBy(() -> lottoService.generateLotto(generateLottoDTO))
                    .as("입력값이 잘못됐으므로 에러가 나야됨.")
                    .hasMessageStartingWith("[ERROR]")
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("유효한 당첨 번호 생성")
    class 유효한당첨번호생성 {

        static Stream<String> successWinningNumberMappingProvider() {
            return Stream.of(
                    "1,2,3,4,5,6",
                    "2,3,4,5,6,7",
                    "45,44,43,42,41,40"
            );
        }

        @ParameterizedTest(name = "{0}")
        @DisplayName("성공")
        @MethodSource("successWinningNumberMappingProvider")
        void success(String mapping) {
            // given
            GenerateWinningNumberDTO generateWinningNumberDTO = new GenerateWinningNumberDTO();
            generateWinningNumberDTO.mapping(mapping);

            // when & then
            assertThatCode(() -> lottoService.checkValidWinningNumber(generateWinningNumberDTO))
                    .doesNotThrowAnyException();
        }

        static Stream<String> failWinningNumberMappingProvider() {
            return Stream.of(
                    "1,2,3,4,5",
                    "1,2,3,4,5,6,7",
                    "-1,2,3,4,5,6",
                    "0,1,2,3,4,5",
                    "46,45,44,43,42,41"
            );
        }

        @ParameterizedTest(name = "{0}")
        @DisplayName("실패")
        @MethodSource("failWinningNumberMappingProvider")
        void fail(String mapping) {
            // given
            GenerateWinningNumberDTO generateWinningNumberDTO = new GenerateWinningNumberDTO();
            generateWinningNumberDTO.mapping(mapping);

            // when & then
            assertThatCode(() -> lottoService.checkValidWinningNumber(generateWinningNumberDTO))
                    .withFailMessage("입력값이 잘못됐으므로 에러")
                    .hasMessageStartingWith("[ERROR]")
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("보너스 번호 추가")
    class 보너스번호추가 {

        static GenerateWinningNumberDTO generateWinningNumberDTO;

        static {
            generateWinningNumberDTO = new GenerateWinningNumberDTO();
            generateWinningNumberDTO.mapping("1,2,3,4,5,6");
        }

        static Stream<String> successBonusNumberMapperProvider() {
            return Stream.of(
                    "10", "11", "12", "13", "45"
            );
        }

        @ParameterizedTest(name = "success: {0}")
        @DisplayName("성공")
        @MethodSource("successBonusNumberMapperProvider")
        void success(String mappedValue) {
            AddBonusNumberDTO addBonusNumberDTO = new AddBonusNumberDTO();
            addBonusNumberDTO.mapping(mappedValue);
            assertThatCode(() -> lottoService.addBonusNumber(generateWinningNumberDTO, addBonusNumberDTO))
                    .doesNotThrowAnyException();
        }

        static Stream<String> failBonusNumberMapperProvider() {
            return Stream.of(
                    "0","46","1","2","3","4","5","6"
            );
        }

        @ParameterizedTest(name = "fail: {0}")
        @DisplayName("실패")
        @MethodSource("failBonusNumberMapperProvider")
        void fail(String failMappedValue) {
            AddBonusNumberDTO addBonusNumberDTO = new AddBonusNumberDTO();
            addBonusNumberDTO.mapping(failMappedValue);
            assertThatThrownBy(()->lottoService.addBonusNumber(generateWinningNumberDTO, addBonusNumberDTO))
                    .as("중복된 보너스 번호, 경계값")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageStartingWith("[ERROR]");
        }
    }
}