import java.util.*;

public interface Parsable<T extends Parsable<T>> {
    public static T parse(String[] fields);
}
