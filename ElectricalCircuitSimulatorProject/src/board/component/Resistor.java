package board.component;

import board.component.complex.Complex;

public class Resistor extends Component{
	public Resistor() {
		setPrefix("R");
		setUnit("Ω");
	}
    public Resistor(double R) {
        setR(new Complex(R, 0));
        setPrefix("R");
        setUnit("Ω");
    }


    @Override
    public String toString() {
        return "Resistor: " + getId() + " | Resistance: " + getR() + "(Ω)";
    }

}
