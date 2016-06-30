package cz.vasekric.utils;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Richard Vašek
 * @version 0.1
 *
 * <h1>You will never ever have another NullPointerException you would not expect.</h1>
 * <p>Problem:</p>
 * <code>
 *     Foo foo = FooFactory.getFoo();
 *     // This may throw NullPointerException. You have to check for null every time you call getter.
 *     String name = foo.getBaz().getStuff().getName();
 * </code>
 * <p>Solution:</p>
 * <code>
 *     // This if something after calling a getter is null and you will call another method on null, it will return null.
 *     String name = NullSafe.of(FooFactory.getFoo())
 *         .resolve((foo) -> foo.getBaz().getStuff().getName())
 * </code>
 */
public class NullSafe<T> {

    public static <T> T resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return result;
        }
        catch (NullPointerException ignored) {
            return null;
        }
    }

    public static <T> NullSafe<T> of(T t) {
        return new NullSafe<T>(t);
    }

    private T t;
    public NullSafe(T t) {
        this.t = t;
    }

    public <T1> T1 resolve(Function<T, T1> resolver) {
        try {
            T1 result = resolver.apply(this.t);
            return result;
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
