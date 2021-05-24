package board.test;

import board.component.Capacitor;
import board.component.Inductor;
import board.component.Resistor;
import board.source.Source;

public class Test {
    public static void main(String[] args) {
        Source source = new Source(120, 60);
        Resistor R1 = new Resistor(100);
        Inductor L2 = new Inductor(200*Math.pow(10, -3), source.get_f());
        Capacitor C3 = new Capacitor(20*Math.pow(10, -6), source.get_f());
        System.out.println("Impedance: ");
        System.out.println(Math.sqrt(R1.getR()*R1.getR() + (C3.getR() - L2.getR())*(C3.getR() - L2.getR())));
    }
}
