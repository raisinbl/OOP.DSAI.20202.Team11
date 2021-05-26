package board.component;

import board.component.complex.Complex;

public class Capacitor extends Component {
    private double C;

    public Capacitor(double C, double f) {
        this.C = C;
        if (f == 0) setR(new Complex(0, Double.POSITIVE_INFINITY));
        else setR(new Complex(0, 1 / (2 * Math.PI * f * C)));
        setPrefix("C");
    }

    public double getC() {
        return C;
    }

    @Override
    public String toString() {
        return "Capacitor: " + getId() + " | Capacitance: " + getC() + "(F)";
    }
}
