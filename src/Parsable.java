//Team Red, Jacob Zahn, Christopher Gee , James Fallon, Ryan Pratt
abstract class Parsable<T extends Parsable<T>> {
    //static <T> T parse(String[] fields){
    //    numFields = T.getDeclaredFields().length;
    //    if(fields.length != numFields) throw new IllegalArgumentException("parse() requires String[] with length of " + numFields.toString());

    //    return null;
    //}

    public abstract String[] toArray();
}
