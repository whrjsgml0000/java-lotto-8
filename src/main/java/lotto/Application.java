package lotto;

import lotto.config.ComponentManager;

public class Application {
    public static void main(String[] args) {
        ComponentManager componentManager = ComponentManager.getInstance();
        componentManager.run();
    }
}
