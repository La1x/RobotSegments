package laix.robotsegments.model;

public class LawLinear{
    private double a;       // acceleration
    private double V;       // speed
    private double V0;      // start speed

    // times
    //private double delta_t;             // 55 ms
    //private double tCurrent;
    //private double tAcceleration;       //acceleration time ms
    //private double tSum;                // 55 ms * N
    //private double tSteadyMotion;       //steady motion time ms
    //private double tBraking;            //braking time ms

    public LawLinear(double v0, double a, double delta_t, double tAcceleration) {
        this.a = a;
        this.V = 0;
        this.V0 = v0;
        //this.delta_t = delta_t/1000;
        //this.tAcceleration = tAcceleration/1000;
        //this.tCurrent = 0;
    }

    /*public double getNextX (double x0) {
        if (tCurrent < tAcceleration) {
            System.out.println("acceleration " + "V: " + V);
            return acceleration(x0);
        } else {
            System.out.println("steady " + "V: " + V);
            return steadymotion(x0);
        }
    }*/

    public double acceleration (double x0, double tCurrent) {
        double x = x0 + V * tCurrent;
        V = V0 + a * tCurrent;
        //tCurrent = tCurrent + delta_t;
        return x;
    }

    public double steadymotion (double x0, double tCurrent) {
        double x = x0 + V * tCurrent;
        //tCurrent = tCurrent + delta_t;
        return x;
    }

    /*public double braking (double x0) {
        double x = x0 + V * tCurrent;
        V = V - a * delta_t;
        tCurrent = tCurrent + delta_t;
        return x;
    }*/
}
