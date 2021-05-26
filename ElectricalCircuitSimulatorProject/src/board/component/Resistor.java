package board.component;

import board.component.complex.Complex;

public class Resistor extends Component{
    public Resistor(double R) {
        setR(new Complex(R, 0));
        setPrefix("R");
    }


    @Override
    public String toString() {
        return "Resistor: " + getId() + " | Resistance: " + getR() + "(Ω)";
    }

}
