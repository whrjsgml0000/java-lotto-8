package lotto.view.mapper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class ObjectMapper {

    public <T extends Mappable<T>> T mapping(Class<T> requiredClassType, String input) {
        try {
            MethodHandle constructor = MethodHandles.lookup()
                    .findConstructor(requiredClassType, MethodType.methodType(void.class));
            T mapped = (T) constructor.invoke();
            return mapped.mapping(input);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Mappable 클래스는 기본 생성자가 필요합니다.");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Mappable 클래스는 기본 생성자가 public 해야 합니다.");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
