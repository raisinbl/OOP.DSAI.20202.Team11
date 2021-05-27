package board.component;

import board.component.complex.Complex;

public abstract class Component {
    private Complex R;
    private double I;
    private double V;
    private String id;
    private String prefix;

    public void setR(Complex r) {
        R = r;
    }

    public Complex getRComplex() {
        return R;
    }

    public double getR() {
        return R.abs();
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
