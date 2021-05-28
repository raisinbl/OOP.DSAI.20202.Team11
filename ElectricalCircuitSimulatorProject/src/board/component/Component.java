package board.component;

import board.component.complex.Complex;

public abstract class Component {
	private String id;
	private Complex R;
	private double I;
	private double V;
	private String prefix;
    private String unit;

    public void setR(Complex r) {
        this.R = r;
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
        this.I = i;
    }

    public double getV() {
        return V;
    }

    public void setV(double v) {
        this.V = v;
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
    
    public String getUnit() {
    	return unit;
    }
    public void setUnit(String unit) {
    	this.unit=unit;
    }
}
