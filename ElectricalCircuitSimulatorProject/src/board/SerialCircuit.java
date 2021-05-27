package board;
import board.component.Component;
import board.source.Source;
import board.component.Capacitor;
import board.component.Inductor;
import board.component.Resistor;
import board.component.complex.Complex;

public class SerialCircuit extends Circuit implements Calculator{
    public SerialCircuit(Source source) {
        super(source);
    }
    
    public double calculateZ(){
        Complex Z =new Complex(0,0);
        for (Component component: getComponentsList()){
            Z=Z.plus(component.getr());
        }
        return Z.abs();
    }

    @Override
    public void calculateI() {
        Complex Z =new Complex(0,0);
        for (Component component: getComponentsList()){
            Z=Z.plus(component.getr());
        }
        for (Component component: getComponentsList()) {
            component.setI(getSource().getV()/Z.abs());
        }
    }

    @Override
    public void calculateV() {
        for (Component component: getComponentsList()){
            component.setV(component.getI()*component.getR());
        }
    }

    @Override
    public boolean checkShortCircuit() {
    }
}
