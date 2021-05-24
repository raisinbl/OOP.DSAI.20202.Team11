package board.component;

public abstract class Component {
    protected double R;
    protected double I;
    protected double V;

    public double getI() {
        return I;
    }

    public void setI(double i) {
        I = i;
    }

    public double getV() {
        return V;
    }

    public void setV(double v) {
        V = v;
    }


    public double getR() {
        return R;
    }
}
