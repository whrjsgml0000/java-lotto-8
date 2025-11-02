package lotto.view.mapper;

public interface Mappable<T extends Mappable<T>> {
    T mapping(String input);
}
