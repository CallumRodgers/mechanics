package mechanics.physics.linalg;

import java.awt.geom.AffineTransform;

public class ScaleTransform extends AffineTransform {

    public ScaleTransform(double x, double y) {
        super();
        scale(x, y);
    }
}
