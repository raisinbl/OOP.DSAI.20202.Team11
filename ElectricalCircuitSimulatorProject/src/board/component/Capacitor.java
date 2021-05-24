package board.component;

public class Capacitor extends Component {
    private double C;

    public Capacitor(double C, double f) {
        this.C = C;
        if (f == 0) this.R = Double.POSITIVE_INFINITY;
        else this.R = 1 / (2 * Math.PI * f * C);
    }

    public double getC() {
        return C;
    }
}
