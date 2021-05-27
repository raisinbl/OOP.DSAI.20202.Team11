package board.test;

import board.ParallelCircuit;
import board.SerialCircuit;
import board.component.Capacitor;
import board.component.Component;
import board.component.Inductor;
import board.component.Resistor;
import board.component.complex.Complex;
import board.source.Source;

public class Test {
    public static void main(String[] args) {
        Source source = new Source(72, 50);
        Resistor R1 = new Resistor(40);
        Inductor L2 = new Inductor(0.4/Math.PI, source.get_f());
        Capacitor C3 = new Capacitor(Math.pow(10, -4)/Math.PI, source.get_f());
        Resistor R4 = new Resistor(200);
        Resistor R5 = new Resistor(150);
        Inductor L6 = new Inductor(200*Math.pow(10, -3), source.get_f());
        System.out.print("Impedance: ");
        System.out.println(Math.sqrt(R1.getR()*R1.getR() + (C3.getR() - L2.getR())*(C3.getR() - L2.getR())));

        SerialCircuit circuit = new SerialCircuit(source);
        circuit.addComponent(R1);
        circuit.addComponent(L2);
        circuit.addComponent(C3);
        // circuit.addComponent(R4);
        // circuit.addComponent(R5);
        // circuit.addComponent(R5);
        // circuit.removeComponent();
        // circuit.addComponent(L6);

        // circuit.calculateV();
        // circuit.calculateI();
        
        System.out.println(circuit.calculateZ());
        System.out.println(C3.getR());
        circuit.calculateI();
        circuit.calculateV();
        circuit.displayAnalysis();
    }
}
