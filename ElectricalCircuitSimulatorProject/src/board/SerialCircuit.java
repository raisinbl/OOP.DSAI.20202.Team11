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
        Complex ZC =new Complex(0, 0);
        Complex a= new Complex(0, - Double.POSITIVE_INFINITY);
        double check=0;
        double c=0;
        for (Component component: getComponentsList()){
            if (component.getPrefix()!="C" ){
                Z=Z.plus(component.getr());
            }
            else{
                if (getSource().get_f()==0){
                    ZC=ZC.plus(a);
                    check=1;
                }
                else{
                    c+=1/ ((Capacitor) component).getC();
                }
            }
        }
        Complex b= new Complex(0, - 1 / (2 * Math.PI * getSource().get_f()/c));
        if (check!=1){
            ZC=ZC.plus(b);
            Z=Z.plus(ZC);
            return Z.abs();
        }
        else{
            Z=Z.plus(ZC);
            return Z.abs();
        }
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
