package laix.robotsegments.model;

// TODO abstract
public abstract class Law {
    // private double x0;
    // private double xk;                  // or Smax, so xk=x0+Smax
    private double a;
    private double delta_t; // 55 ms
    private double tSum; // 55 ms * N
    private double tAcceleration;       //acceleration time ms
    private double tSteadyMotion;       //steady motion time ms
    private double tBraking;            //braking time ms

    // abstract method for a(t)
    public abstract double acceleration(double t, double ti, double sMax); // T-разгона, торможения уд; ti - текущее время, Smax
    // abstract method for v(t)
    public abstract double velocity(double t, double ti, double sMax);
    // abstract method for s(t)
    public abstract double way(double t, double ti, double sMax);

    public double gettSum() {
        return tSum;
    }

    public void settSum(double tSum) {
        this.tSum = tSum;
    }

    public double gettAcceleration() {
        return tAcceleration;
    }

    public void settAcceleration(double tAcceleration) {
        this.tAcceleration = tAcceleration;
    }

    public double gettSteadyMotion() {
        return tSteadyMotion;
    }

    public void settSteadyMotion(double tSteadyMotion) {
        this.tSteadyMotion = tSteadyMotion;
    }

    public double gettBraking() {
        return tBraking;
    }

    public void settBraking(double tBraking) {
        this.tBraking = tBraking;
    }
}
