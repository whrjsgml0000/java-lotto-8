package lotto.view;

public class Output {

    public void println(String message) {
        System.out.println(message);
    }

    public void lineSeparate(int count) {
        System.out.println(System.lineSeparator().repeat(count));
    }
}
