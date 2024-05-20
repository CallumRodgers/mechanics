package mechanics.physics.vectors;

public class Vector3d extends Vector<Vector3d> {

    // Cartesian unit vectors.
    public static final Vector3d X_HAT = new Vector3d(1, 0, 0);
    public static final Vector3d Y_HAT = new Vector3d(0, 1, 0);
    public static final Vector3d Z_HAT = new Vector3d(0, 0, 1);

    public double v1;
    public double v2;
    public double v3;

    public Vector3d() {
        this(0, 0, 0, CoordinateSystem.CARTESIAN);
    }

    public Vector3d(double v1, double v2, double v3) {
        this(v1, v2, v3, CoordinateSystem.CARTESIAN);
    }

    public Vector3d(double v1, double v2, double v3, CoordinateSystem system) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.coordinateSystem = system;
    }

    // #######
    // Setting
    // #######

    @Override
    public void set(Vector3d v) {
        this.v1 = v.v1;
        this.v2 = v.v2;
        this.v3 = v.v3;
    }

    @Override
    public void set(double... coords) {
        checkCoordCount(coords);
        this.v1 = coords[0];
        this.v2 = coords[1];
        this.v3 = coords[2];
    }

    // ############
    // Add methods.
    // ############

    @Override
    public Vector3d add(Vector3d v) {
        return new Vector3d(v1 + v.v1, v2 + v.v2, v3 + v.v3);
    }

    @Override
    public Vector3d add(double... coords) {
        checkCoordCount(coords);
        return new Vector3d(v1 + coords[0], v2 + coords[1], v3 + coords[2]);
    }

    @Override
    public Vector3d sub(Vector3d v) {
        return null;
    }

    @Override
    public Vector3d sub(double... components) {
        return null;
    }

    @Override
    public Vector3d mul(double c) {
        return null;
    }

    @Override
    public double dot(Vector3d v) {
        return 0;
    }

    @Override
    public Vector3d normalize() {
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
    public boolean equals(Vector3d v) {
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
    public void copy(Vector3d v) {

    }
}
