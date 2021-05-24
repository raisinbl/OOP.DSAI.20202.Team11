package board.component;

public class Inductor extends  Component{
    private double L;

    public Inductor(double L, double f) {
        this.L = L;
        this.R = 2 * Math.PI * f * L;
    }

    public double getL() {
        return L;
    }
}
