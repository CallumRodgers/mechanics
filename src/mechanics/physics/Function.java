package mechanics.physics;

public class Function {

    // Function interfaces for up to 4 parameters.
    // These interfaces are used to construct functions easily
    // through Java's lambda expressions, with equivalent constructors
    // in this class.
    @FunctionalInterface
    public interface Function1Param<T1 extends Double> {
        Double apply(T1 t1);
    }
    @FunctionalInterface
    public interface Function2Param<T1 extends Double, T2 extends Double> {
        Double apply(T1 t1, T2 t2);
    }
    @FunctionalInterface
    public interface Function3Param<T1 extends Double, T2 extends Double,
            T3 extends Number> {
        Double apply(T1 t1, T2 t2, T3 t3);
    }
    @FunctionalInterface
    public interface Function4Param<T1 extends Double, T2 extends Double,
            T3 extends Double, T4 extends Double> {
        Double apply(T1 t1, T2 t2, T3 t3, T4 t4);
    }

    private Function1Param func1;
    private Function2Param func2;
    private Function3Param func3;
    private Function4Param func4;

    private int parameterCount;

    public Function(Function1Param func1) {
        this.func1 = func1;
        this.parameterCount = 1;
    }

    public Function(Function2Param func2) {
        this.func2 = func2;
        this.parameterCount = 2;
    }

    public Function(Function3Param func3) {
        this.func3 = func3;
        this.parameterCount = 3;
    }

    public Function(Function4Param func4) {
        this.func4 = func4;
        this.parameterCount = 4;
    }

    public float apply(Number arg1, Number... args) {
        int length = args.length + 1;
        try {
            switch (length) {
                case 1 -> {
                    return func1.apply(arg1.doubleValue()).floatValue();
                }
                case 2 -> {
                    return func2.apply(arg1.doubleValue(), args[0].doubleValue()).floatValue();
                }
                case 3 -> {
                    return func3.apply(arg1.doubleValue(), args[0].doubleValue(),
                            args[1].doubleValue()).floatValue();
                }
                case 4 -> {
                    return func4.apply(arg1.doubleValue(), args[0].doubleValue(),
                            args[1].doubleValue(), args[2].doubleValue()).floatValue();
                }
                default -> throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Function doesn't support " + length + " parameters.");
        }
    }

    public int getParameterCount() {
        return parameterCount;
    }
}
