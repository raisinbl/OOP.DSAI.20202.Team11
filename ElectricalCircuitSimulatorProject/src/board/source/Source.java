package board.source;

public class Source {
    private double V;
    private double F;
    public Source() {
    	
    }
    public Source(double v, double f) {
        this.V = v;
        this.F = f;
    }
    public double getV() {
        return V;
    }

    public double getF() {
        return F;
    }
	public void setV(double v) {
		V = v;
	}
	public void setF(double f) {
		F = f;
	}
}
