package board.component;

import board.component.complex.Complex;

public class Component {
	private String id;
	private Complex R;
	private double i;
	private double v;
	private String prefix;
    private String unit;
//    public Component() {
//    	
//    }
//    public Component(String id, Complex R, double I, double V) {
//    	this.id = id;
//    	this.R = R;
//    	this.I = I;
//    	this.V = V;
//    }
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
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
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
