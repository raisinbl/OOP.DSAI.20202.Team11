package board.test;

import board.SerialCircuit;
import board.component.Capacitor;
import board.component.Inductor;
import board.component.Resistor;
import board.source.Source;

public class Test {
    public static void main(String[] args) {
        // Data 1
//        Source source = new Source(120, 0);
//        Resistor R1 = new Resistor(100);
//        Inductor L2 = new Inductor(200*Math.pow(10, -3), source.getF());
//        Capacitor C3 = new Capacitor(20*Math.pow(10, -6), source.getF());
//        Resistor R4 = new Resistor(200);
//        Resistor R5 = new Resistor(150);
//        Inductor L6 = new Inductor(200*Math.pow(10, -3), source.getF());
//        ParallelCircuit circuit = new ParallelCircuit();
//        circuit.setSource(source);
//        circuit.addComponent(R1);
//        circuit.addComponent(L2);
//        circuit.addComponent(C3);
//        circuit.addComponent(R4);
//        circuit.addComponent(R5);
//        circuit.addComponent(R5);
//        circuit.removeComponent();
//        circuit.addComponent(L6);

        // Data 2
//        Source source = new Source(100, 50);
//        Resistor R1 = new Resistor(10);
//        Inductor L2 = new Inductor(33*Math.pow(10, -3), source.getF());
//        Capacitor C3 = new Capacitor(180*Math.pow(10, -6), source.getF());
//        SerialCircuit circuit = new SerialCircuit();
//        circuit.setSource(source);
//        circuit.addComponent(R1);
//        circuit.addComponent(L2);
//        circuit.addComponent(C3);

        // Data 3
//        Source source = new Source(120, 50);
//        Resistor R1 = new Resistor(100);
//        Inductor L2 = new Inductor(560*Math.pow(10, -3), source.getF());
//        Capacitor C3 = new Capacitor(20*Math.pow(10, -6), source.getF());
//        SerialCircuit circuit = new SerialCircuit();
//        circuit.setSource(source);
//        circuit.addComponent(R1);
//        circuit.addComponent(L2);
//        circuit.addComponent(C3);

        // Data 4
        Source source = new Source(10, 0);
        Resistor R1 = new Resistor(5);
        Inductor L2 = new Inductor(10*Math.pow(10, -3), source.getF());
        Capacitor C3 = new Capacitor(20*Math.pow(10, -6), source.getF());
        SerialCircuit circuit = new SerialCircuit();
        circuit.addSource(source);
        circuit.addComponent(R1);
        circuit.addComponent(L2);
        circuit.addComponent(C3);

        circuit.calculateI();
        circuit.calculateV();
        circuit.displayAnalysis();
    }
}