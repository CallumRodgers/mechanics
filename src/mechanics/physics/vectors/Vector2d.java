package mechanics.physics.vectors;

import static java.lang.Math.*;

/**
 * Simple class wrapping two double values, with vector operations.
 */
public class Vector2d extends Vector<Vector2d> {

    // Null vector.
    public static final Vector2d NULL = new Vector2d();

    // Cartesian unit vectors. We can't do the same to polar unit vectors as they're not
    // constant. To get the polar unit vectors for a specific vector, call rHat() and thetaHat().
    public static final Vector2d X_HAT = new Vector2d(1, 0);
    public static final Vector2d Y_HAT = new Vector2d(0, 1);

    /**
     * First component of the vector. Use <code>isPolar()()</code> to check
     * whether it's the <code>x</code> cartesian coordinate or the <code>r</code>
     * polar coordinate.
     */
    public double v1;

    /**
     * Second component of the vector. Use <code>isPolar()()</code> to check
     * whether it's the <code>y</code> cartesian coordinate or the <code>theta</code>
     * polar coordinate.
     */
    public double v2;

    public Vector2d() {
        this(0, 0, CoordinateSystem.CARTESIAN);
    }

    public Vector2d(double v1, double v2) {
        this(v1, v2, CoordinateSystem.CARTESIAN);
    }

    public Vector2d(double v1, double v2, CoordinateSystem system) {
        if (system != CoordinateSystem.CARTESIAN && system != CoordinateSystem.POLAR) {
            throw new IllegalArgumentException("Invalid coordinate system for a 2D vector");
        }
        this.v1 = v1;
        this.v2 = v2;
        this.coordinateSystem = system;
    }

    // ########
    // Setting.
    // ########

    @Override
    public void set(Vector2d v) {
        this.v1 = v.v1;
        this.v2 = v.v2;
    }

    @Override
    public void set(double... coords) {
        checkCoordCount(coords);
        this.v1 = coords[0];
        this.v2 = coords[1];
    }

    // ############
    // Add methods.
    // ############

    @Override
    public Vector2d add(Vector2d v) {
        Vector2d cart1 = toCartesian();
        Vector2d cart2 = v.toCartesian();
        Vector2d sum = new Vector2d(cart1.v1 + cart2.v1, cart1.v2 + cart2.v2);
        if (coordinateSystem == CoordinateSystem.POLAR) {
            return sum.toPolar();
        }
        return sum;
    }

    @Override
    public Vector2d add(double... components) {
        checkCoordCount(components);
        return new Vector2d(v1 + components[0], v2 + components[1]);
    }

    // #################
    // Subtract methods.
    // #################

    @Override
    public Vector2d sub(Vector2d v) {
        Vector2d cart1 = toCartesian();
        Vector2d cart2 = v.toCartesian();
        Vector2d diff = new Vector2d(cart1.v1 - cart2.v1, cart1.v2 - cart2.v2);
        if (coordinateSystem == CoordinateSystem.POLAR) {
            return diff.toPolar();
        }
        return diff;
    }

    @Override
    public Vector2d sub(double... components) {
        checkCoordCount(components);
        return new Vector2d(v1 - components[0], v2 - components[1]);
    }

    // ######################
    // Scalar multiplication.
    // ######################

    @Override
    public Vector2d mul(double c) {
        return null;
    }

    // ##########################
    // Dot and cross products.
    // ##########################

    @Override
    public double dot(Vector2d v) {
        Vector2d cart1 = toCartesian();
        Vector2d cart2 = v.toCartesian();
        return cart1.v1 * cart2.v1 + cart1.v2 * cart2.v2;
    }

    public double cross(Vector2d v) {
        return v1 * v.v2 - v2 * v.v1;
    }

    // #################################
    // Length and normalization methods.
    // #################################

    public Vector2d normalize() {
        if (isPolar()) {
            return new Vector2d(1.0, v2);
        } else {
            double len = length();
            return new Vector2d(v1 / len, v2 / len);
        }
    }

    public double length() {
        if (isPolar()) {
            return v1;
        } else {
            return sqrt(dot(this));
        }
    }

    // ################
    // Boolean methods.
    // ################

    @Override
    public boolean equals(Vector2d v) {
        return v.v1 == v1 && v.v2 == v2 && coordinateSystem.equals(v.coordinateSystem);
    }

    @Override
    public boolean isNull() {
        return v1 == 0 && (isPolar() || v2 == 0);
    }

    // ################
    // Utility methods.
    // ################

    @Override
    public int getDimensions() {
        return 2;
    }

    @Override
    public void copy(Vector2d v) {
        this.v1 = v.v1;
        this.v2 = v.v2;
        this.coordinateSystem = v.coordinateSystem;
    }

    @Override
    public double[] getComponents() {
        return new double[] { v1, v2 };
    }

    @Override
    public String toString() {
        return "(" + v1 + ", " + v2 + ")";
    }

    // ###########################################################
    // Conversion methods between cartesian and polar coordinates.
    // ###########################################################

    public boolean isPolar() {
        return coordinateSystem.equals(CoordinateSystem.POLAR);
    }

    public Vector2d toPolar() {
        if (isPolar()) return this;
        double r = length();
        // atan2() returns angle theta between the vector and the Y-AXIS, with a range
        // of -pi to pi clockwise, so we need first to negate the returned angle, so it runs
        // counter-clockwise, and then add pi/2 so the angle is with respect to the X-AXIS, instead
        // of the Y-AXIS.
        double theta = -atan2(v1, v2) + PI/2;
        // If the vector is in the fourth quadrant, theta will still be negative, so we need
        // to add to 2pi to it, so it points in the same direction but with a positive value.
        if (theta < 0.0) theta += (2*PI);
        return new Vector2d(r, theta, CoordinateSystem.POLAR);
    }

    public Vector2d toCartesian() {
        if (!isPolar()) return this;
        return new Vector2d(v1 * cos(v2), v1 * sin(v2), CoordinateSystem.CARTESIAN);
    }

    /**
     * Returns the r_hat unit vector for this vector, in whatever coordinates
     * this vector is in.
     * @return
     */
    public Vector2d rHat() {
        if (isPolar()) {
            return new Vector2d(1.0, v2);
        } else {
            double angle = toPolar().v2;
            return new Vector2d(cos(angle), sin(angle));
        }
    }

    /**
     * Returns the theta_hat unit vector for this vector, in whatever coordinates
     * this vector is in.
     * @return
     */
    public Vector2d thetaHat() {
        if (isPolar()) {
            return new Vector2d(1.0, (v2 + PI/2) % (2*PI));
        } else {
            double angle = toPolar().v2;
            return new Vector2d(-sin(angle), cos(angle));
        }
    }
}
