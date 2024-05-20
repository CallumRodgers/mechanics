package mechanics.physics.vectors;

public class Vector4d extends Vector<Vector4d> {

    // Cartesian unit vectors.
    public static final Vector4d X_HAT = new Vector4d(1, 0, 0, 0);
    public static final Vector4d Y_HAT = new Vector4d(0, 1, 0, 0);
    public static final Vector4d Z_HAT = new Vector4d(0, 0, 1, 0);
    public static final Vector4d W_HAT = new Vector4d(0, 0, 0, 1);

    public double v1;
    public double v2;
    public double v3;
    public double v4;

    public Vector4d() {
        this(0, 0, 0, 0, CoordinateSystem.CARTESIAN);
    }

    public Vector4d(double v1, double v2, double v3, double v4) {
        this(v1, v2, v3, v4, CoordinateSystem.CARTESIAN);
    }

    public Vector4d(double v1, double v2, double v3, double v4, CoordinateSystem system) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.coordinateSystem = system;
    }

    // #######
    // Setting
    // #######

    @Override
    public void set(Vector4d v) {
        this.v1 = v.v1;
        this.v2 = v.v2;
        this.v3 = v.v3;
        this.v4 = v.v4;
    }

    @Override
    public void set(double... coords) {
        checkCoordCount(coords);
        this.v1 = coords[0];
        this.v2 = coords[1];
        this.v3 = coords[2];
        this.v4 = coords[3];
    }

    @Override
    public Vector4d add(Vector4d v) {
        return null;
    }

    @Override
    public Vector4d add(double... components) {
        return null;
    }

    @Override
    public Vector4d sub(Vector4d v) {
        return null;
    }

    @Override
    public Vector4d sub(double... components) {
        return null;
    }

    @Override
    public Vector4d mul(double c) {
        return null;
    }

    @Override
    public double dot(Vector4d v) {
        return 0;
    }

    @Override
    public Vector4d normalize() {
        return null;
    }

    @Override
    public double length() {
        return 0;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean equals(Vector4d v) {
        return false;
    }

    @Override
    public int getDimensions() {
        return 0;
    }

    @Override
    public double[] getComponents() {
        return new double[0];
    }

    @Override
    public void copy(Vector4d v) {

    }
}
