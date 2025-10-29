package lotto.config;

import lotto.model.domain.lotto.factory.LottoFactory;

public class ComponentManager {

    private static ComponentManager instance;
    private final LottoFactory lottoFactory;

    private ComponentManager() {
        this.lottoFactory = LottoFactory.defaultSetting();
    }

    public static ComponentManager getInstance() {
        if(instance == null){
            instance = new ComponentManager();
        }
        return instance;
    }
}
