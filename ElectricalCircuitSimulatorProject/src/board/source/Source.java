package board.source;

public class Source {
    private double V;
    private double f;
    public Source() {
    	
    }
    public Source(double v, double f) {
        this.V = v;
        this.f = f;
    }
    public double getV() {
        return V;
    }

    public double getF() {
        return f;
    }
	public void setV(double v) {
		V = v;
	}
	public void setF(double f) {
		this.f = f;
	}
}
