package laix.robotsegments.model;
/*
 * Класс для задания движения.
 * Содержит начальные параметры, время и закон движения.
 */
public class Task {
    private final double DELTA_T = 55 / 1000;             // 55 ms
    // start params
    private double x0;
    private double xk;
    private double N;
    // time params
    private double tAcceleration;       //acceleration time ms
    private double tSteadyMotion;       //steady motion time ms
    private double tBaking;
    private double tCurrent;

    // params for law
    private double a;       // acceleration
    private double v;       // speed
    private double v0;      // start speed

    private LawLinear lawLinear;

    public Task() {
        this.x0 = 0;
        this.xk = 0;
        this.N = 0;
        this.tAcceleration = 0;
        this.tSteadyMotion = 0;
        this.tBaking = 0;
        this.tCurrent = 0;
        this.lawLinear = new LawLinear(0,0, DELTA_T,0);
    }

    public double getNextX() {
        // TODO проверка на выход x за пределы xk
        tCurrent = tCurrent + DELTA_T;
        if (tCurrent < tAcceleration) {
            return lawLinear.acceleration(x0, tCurrent);
        } else {
            return lawLinear.steadymotion(x0, tCurrent);
        }
    }

    public LawLinear getLawLinear() {
        return lawLinear;
    }

    public void setLawLinear(LawLinear lawLinear) {
        this.lawLinear = lawLinear;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getXk() {
        return xk;
    }

    public void setXk(double xk) {
        this.xk = xk;
    }

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public double gettAcceleration() {
        return tAcceleration;
    }

    public void settAcceleration(double tAcceleration) {
        this.tAcceleration = tAcceleration / 1000;
    }

    public double gettSteadyMotion() {
        return tSteadyMotion;
    }

    public void settSteadyMotion(double tSteadyMotion) {
        this.tSteadyMotion = tSteadyMotion / 1000;
    }

    public double gettBaking() {
        return tBaking;
    }

    public void settBaking(double tBaking) {
        this.tBaking = tBaking / 1000;
    }

    public double gettCurrent() {
        return tCurrent;
    }

    public void settCurrent(double tCurrent) {
        this.tCurrent = tCurrent;
    }
}
