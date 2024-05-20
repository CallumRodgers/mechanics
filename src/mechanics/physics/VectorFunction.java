package mechanics.physics;

import mechanics.physics.vectors.Vector2d;

public class VectorFunction {

    private Function func1;
    private Function func2;

    public VectorFunction(Function func1, Function func2) {
        if (func1.getParameterCount() != func2.getParameterCount()) {
            throw new IllegalArgumentException("Functions must have the same number of parameters");
        }
        this.func1 = func1;
        this.func2 = func2;
    }

    public Vector2d apply(Number arg1, Number... args) {
        double v1 = func1.apply(arg1, args);
        double v2 = func2.apply(arg1, args);
        return new Vector2d(v1, v2);
    }
}
