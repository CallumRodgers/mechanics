package mechanics.physics.vectors;

/**
 * Abstract header class defining the methods all vector objects must have.
 */
public abstract class Vector<T extends Vector<T>> {

    protected static final int X_AXIS = -1;
    protected static final int Y_AXIS = -2;
    protected static final int Z_AXIS = -3;
    protected static final int W_AXIS = -4;

    public enum CoordinateSystem {
        CARTESIAN,
        POLAR,
        CYLINDRICAL,
        SPHERICAL
    }

    protected CoordinateSystem coordinateSystem;

    // ####### //
    // SETTING //
    // ####### //

    /**
     * Sets this vector to have all the components the other has.
     * Note that this copies the vector components with no care for the
     * coordinate systems used. If a vector needs to be fully copied, use
     * <code>copy()</code>
     * @param v the vector to copy the components from.
     */
    public abstract void set(T v);
    public abstract void set(double... coords);

    // ######## //
    // ADDITION //
    // ######## //

    /**
     * Returns the vector that is the sum of this vector and the given one.
     * Note that addition might not necessarily be "adding the components together".
     * It will be in Cartesian coordinates, but not in polar. This method is very different from the
     * one that takes a double array as input. That method will add the components no matter what coordinate
     * system. This method returns the <b>vector sum</b> of the two vectors.
     * @param v vector to add.
     * @return the vector sum of them.
     */
    public abstract T add(T v);

    /**
     * Adds the given components to this vector.
     * Note that this method is very different from the add() method that takes in
     * another vector object. This one adds the given components with no care for the underlying
     * coordinate system.
     * @param components components to add. The array size must match the dimensions of this vector.
     * @return this vector with its components increased by the given ones.
     */
    public abstract T add(double... components);

    // ########### //
    // SUBTRACTION //
    // ########### //

    /**
     * Returns the vector that is this vector minus the given one.
     * Note that subtraction might not necessarily be "subtracting the components one-by-one".
     * It will be in Cartesian coordinates, but not in polar. This method is very different from the
     * one that takes a double array as input. That method will subtract the components no matter what coordinate
     * system. This method returns the <b>vector difference</b> of the two vectors.
     * @param v vector to subtract.
     * @return the vector difference of them.
     */
    public abstract T sub(T v);

    /**
     * Adds the given components to this vector.
     * Note that this method is very different from the add() method that takes in
     * another vector object. This one adds the given components with no care for the underlying
     * coordinate system.
     * @param components components to add. The array size must match the dimensions of this vector.
     * @return this vector with its components increased by the given ones
     */
    public abstract T sub(double... components);

    // ##################### //
    // SCALAR MULTIPLICATION //
    // ##################### //

    public abstract T mul(double c);

    // ########### //
    // DOT PRODUCT //
    // ########### //

    public abstract double dot(T v);

    // ######################## //
    // LENGTH AND ANGLE METHODS //
    // ######################## //

    public abstract T normalize();

    public abstract double length();

    public double angle(T v) {
        return Math.acos(dot(v) / (length() * v.length()));
    }

    // ############### //
    // BOOLEAN METHODS //
    // ############### //

    public abstract boolean isNull();
    public abstract boolean equals(T v);

    // ####### //
    // UTILITY //
    // ####### //

    public abstract int getDimensions();
    public abstract double[] getComponents();
    public abstract void copy(T v);

    protected void checkCoordCount(double... coords) {
        if (coords.length != getDimensions()) {
            throw new IllegalArgumentException("Amount of coordinates does not match the number of dimensions");
        }
    }
}
