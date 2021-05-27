package board;

import board.component.Component;
import board.component.complex.Complex;
import board.source.Source;

public class SerialCircuit extends Circuit implements Calculator{
    public SerialCircuit(Source source) {
        super(source);
    }

    public double calculateZ() {
        Complex Z = new Complex(0, 0);
        for (Component component: getComponentsList()) {
            Z = Z.plus(component.getRComplex());
        }
        return Z.abs();
    }

    @Override
    public void calculateI() {
        double Z = calculateZ();
        double I;
        if (checkShortCircuit()) {
            for (Component component: getComponentsList()) {
                component.setI(Double.POSITIVE_INFINITY);
            }
        }
        else {
            for (Component component: getComponentsList()) {
                component.setI(getSource().getV() / calculateZ());
            }
        }
    }

    @Override
    public void calculateV() {
        for (Component component: getComponentsList()) {
            component.setV(component.getI() * component.getR());
        }
    }

    @Override
    public boolean checkShortCircuit() {
        if (calculateZ() == 0) {
            System.out.println("Short circuit detected!");
            return true;
        }
        return false;
    }
}
