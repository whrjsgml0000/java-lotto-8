package lotto.config;

import lotto.controller.LottoController;
import lotto.model.domain.lotto.factory.LottoFactory;
import lotto.model.service.LottoService;
import lotto.view.IOHandler;
import lotto.view.Input;
import lotto.view.Output;
import lotto.view.mapper.ObjectMapper;

public class ComponentManager {

    private static ComponentManager instance;
    private final LottoFactory lottoFactory;
    private final IOHandler ioHandler;
    private final Input input;
    private final Output output;
    private final ObjectMapper objectMapper;
    private final LottoService lottoService;
    private final LottoController lottoController;

    private ComponentManager() {
        this.input = new Input();
        this.output = new Output();
        this.objectMapper = new ObjectMapper();
        this.ioHandler = new IOHandler(input, output, objectMapper);
        this.lottoFactory = LottoFactory.defaultSetting();
        this.lottoService = LottoService.defaultService();
        this.lottoController = new LottoController(ioHandler, lottoService);
    }

    public static ComponentManager getInstance() {
        if(instance == null){
            instance = new ComponentManager();
        }
        return instance;
    }

    public void run() {
        lottoController.run();
    }
}
