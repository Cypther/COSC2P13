/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */



/**
 * Convenience implementation of {@link NumberGenerator} that always
 * returns the same value.
 * @param <T> The numeric type (Integer, Long, Double, etc.) of the constant.
 */
public class ConstantGenerator<T extends Number> implements NumberGenerator<T>
{
    private final T constant;

    /**
     * Creates a number generator that always returns the same
     * values.
     * constant The value to be returned by all invocations
     */
    public ConstantGenerator(T constant)
    {
        this.constant = constant;
    }

    /**
     *  The constant value specified when the generator was constructed.
     */
    public T nextValue()
    {
        return constant;
    }
}