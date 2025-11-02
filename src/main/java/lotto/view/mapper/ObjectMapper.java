package lotto.view.mapper;

import static lotto.exception.Error.MAPPABLE_CLASS_SHOULD_HAVE_DEFAULT_CONSTRUCTOR;
import static lotto.exception.Error.MAPPABLE_CLASS_SHOULD_HAVE_PUBLIC_DEFAULT_CONSTRUCTOR;
import static lotto.exception.Error.MAPPING_PROCESS_ERROR;

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
            throw new IllegalStateException(MAPPABLE_CLASS_SHOULD_HAVE_DEFAULT_CONSTRUCTOR.message());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(MAPPABLE_CLASS_SHOULD_HAVE_PUBLIC_DEFAULT_CONSTRUCTOR.message());
        } catch (Throwable e) {
            throw new IllegalStateException(MAPPING_PROCESS_ERROR.message());
        }
    }
}
