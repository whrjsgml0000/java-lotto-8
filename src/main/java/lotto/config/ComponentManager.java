package lotto.config;

import lotto.controller.LottoController;
import lotto.model.domain.lotto.factory.LottoFactory;
import lotto.view.IOHandler;
import lotto.view.Input;
import lotto.view.Output;

public class ComponentManager {

    private static ComponentManager instance;
    private final LottoFactory lottoFactory;
    private final IOHandler ioHandler;
    private final Input input;
    private final Output output;
    private final LottoController lottoController;

    private ComponentManager() {
        this.input = new Input();
        this.output = new Output();
        this.ioHandler = new IOHandler(input, output);
        this.lottoFactory = LottoFactory.defaultSetting();
        this.lottoController = new LottoController(ioHandler);
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
