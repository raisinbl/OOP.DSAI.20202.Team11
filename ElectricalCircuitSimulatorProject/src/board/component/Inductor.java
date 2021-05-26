package board.component;

public class Inductor extends  Component{
    private double L;

    public Inductor(double L, double f) {
        this.L = L;
        setR(new Complex(0,2 * Math.PI * f * L));
        setPrefix("L");
    }

    public double getL() {
        return L;
    }

    @Override
    public String toString() {
        return "Inductor: " + getId() + " | Inductance: " + getL() + "(H)";
    }
}
