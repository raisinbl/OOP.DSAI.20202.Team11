package board.test;

import board.ParallelCircuit;
import board.component.Capacitor;
import board.component.Inductor;
import board.component.Resistor;
import board.source.Source;

public class Test {
    public static void main(String[] args) {
        Source source = new Source(120, 0);
        Resistor R1 = new Resistor(100);
        Inductor L2 = new Inductor(200*Math.pow(10, -3), source.get_f());
        Capacitor C3 = new Capacitor(20*Math.pow(10, -6), source.get_f());
        Resistor R4 = new Resistor(200);
        Resistor R5 = new Resistor(150);
        Inductor L6 = new Inductor(200*Math.pow(10, -3), source.get_f());
        System.out.print("Impedance: ");
        System.out.println(Math.sqrt(R1.getR()*R1.getR() + (C3.getR() - L2.getR())*(C3.getR() - L2.getR())));

        ParallelCircuit circuit = new ParallelCircuit(source);
        circuit.addComponent(R1);
        circuit.addComponent(L2);
        circuit.addComponent(C3);
        circuit.addComponent(R4);
        circuit.addComponent(R5);
        circuit.addComponent(R5);
        circuit.removeComponent();
        circuit.addComponent(L6);

        circuit.calculateV();
        circuit.calculateI();
        circuit.displayAnalysis();
    }
}
