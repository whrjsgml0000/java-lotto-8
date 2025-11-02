package lotto.controller.message;

public enum ResponseFormat {
    PURCHASE_COUNT("%d개를 구매했습니다."),
    ;
    private final String format;

    ResponseFormat(String format) {
        this.format = format;
    }

    public String formatted(Object... formatted) {
        return format.formatted(formatted);
    }
}
