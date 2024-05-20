package mechanics.physics.linalg;

import java.awt.geom.AffineTransform;

/**
 * Utility class to create an <code>AffineTransform</code> that already contains
 * translation information.
 */
public class TranslateTransform extends AffineTransform {

    public TranslateTransform(double x, double y) {
        super();
        translate(x, y);
    }
}
