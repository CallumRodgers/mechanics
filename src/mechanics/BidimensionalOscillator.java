package mechanics;

import mechanics.physics.vectors.Vector2d;

import java.util.Arrays;

import static java.lang.Math.*;

public class BidimensionalOscillator {

    public static final double[] DEFAULT_CONFIG = {
            1.0, // m
            1.0, // kx
            1.0, // ky
            100.0, // x0
            100.0, // y0
            0.0, // vx0
            0.0, // vy0
            0.0, // b
    };

    public static final int A = 0;
    public static final int W = 1;
    public static final int DEL = 2;
    public static final int W1 = 3;
    public static final int BETA = 4;

    private final double m;
    private final double kx, ky;
    private double b;
    private Vector2d r0;
    private Vector2d v0;

    private boolean hasDrag;

    /**
     * Computed oscillator params.
     */
    private final double[] paramsX, paramsY;

    public BidimensionalOscillator(double m, double kx, double ky) {
        this.m = m;
        this.kx = kx;
        this.ky = ky;
        this.paramsX = new double[5];
        this.paramsY = new double[5];
    }

    public void setR0(Vector2d r0) {
        this.r0 = r0;
    }

    public void setV0(Vector2d v0) {
        this.v0 = v0;
    }

    public void setDrag(double b) {
        hasDrag = (b != 0.0);
        this.b = max(b, 0.0);
    }

    public void prepare() {
        paramsX[W] = sqrt(kx / m);
        paramsY[W] = sqrt(ky / m);

        paramsX[BETA] = b / (2 * m);
        paramsY[BETA] = b / (2 * m);

        paramsX[W1] = sqrt(-pow(paramsX[BETA], 2) + (kx / m));
        paramsY[W1] = sqrt(-pow(paramsY[BETA], 2) + (ky / m));

        paramsX[A] = hypot(r0.v1, (v0.v1 / paramsX[W]));
        if (paramsX[A] == 0) {
            paramsX[DEL] = 0;
        } else if (v0.v1 == 0) {
            paramsX[DEL] = acos(r0.v1 / paramsX[A]);
        } else {
            paramsX[DEL] = asin(v0.v1 / (paramsX[A] * paramsX[W]));
        }

        paramsY[A] = hypot(r0.v2, (v0.v2 / paramsY[W]));
        if (paramsY[A] == 0) {
            paramsY[DEL] = 0;
        } else if (v0.v2 == 0) {
            paramsY[DEL] = acos(r0.v2 / paramsY[A]);
        } else {
            paramsY[DEL] = asin(v0.v2 / (paramsY[A] * paramsY[W]));
        }

        System.out.println(Arrays.toString(paramsX) + " " + Arrays.toString(paramsY));
    }

    public double[] xVars(double t) {
        return compute(t, paramsX, kx);
    }

    public double[] yVars(double t) {
        return compute(t, paramsY, ky);
    }

    public double[] getParamsX() {
        return paramsX;
    }

    public double[] getParamsY() {
        return paramsY;
    }

    private double[] compute(double t, double[] params, double k) {
        double x, vx, fx;
        if (!hasDrag) {
            double trigArg = params[W] * t - params[DEL];
            x = params[A] * cos(trigArg);
            vx = -params[A] * params[W] * sin(trigArg);
            fx = -k * x;
        } else {
            double trigArg = params[W1] * t - params[DEL];
            x = params[A] * exp(-params[BETA] * t) * cos(trigArg);
            vx = params[A] * exp(-params[BETA] * t) * (params[BETA] * cos(trigArg) + params[W1] * sin(trigArg));
            fx = -k * x - b * vx;
        }
        return new double[]{
                x, vx, fx
        };
    }

    public double getM() {
        return m;
    }

    public double getKx() {
        return kx;
    }

    public double getKy() {
        return ky;
    }

    public double getB() {
        return b;
    }
}
