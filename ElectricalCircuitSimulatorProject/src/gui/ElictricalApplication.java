package gui;

import java.io.IOException;

import javax.swing.*;

import board.Circuit;
import board.ParallelCircuit;
import board.component.Capacitor;
import board.component.Inductor;
import board.component.Resistor;
import board.source.Source;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class ElictricalApplication extends JFrame{
	private Circuit circuit;
	public ElictricalApplication(Circuit circuit) {
		super();
		this.circuit = circuit;
		
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		this.setSize(1024,720);
		this.setTitle("Circuit demo");
		this.setVisible(true);
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().
							getResource("guiElectrical.fxml"));
					guiController controller =
							new guiController(circuit);
					loader.setController(controller);
					Parent root  = loader.load();
					fxPanel.setScene(new Scene(root));
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void main(String[] args) {
		Source source = new Source(120, 0);
        Resistor R1 = new Resistor(100);
        Inductor L2 = new Inductor(200*Math.pow(10, -3), source.getF());
        Capacitor C3 = new Capacitor(20*Math.pow(10, -6), source.getF());
        Resistor R4 = new Resistor(200);
        Resistor R5 = new Resistor(150);
        System.out.print("Impedance: ");
        System.out.println(Math.sqrt(R1.getR()*R1.getR() + (C3.getR() - L2.getR())*(C3.getR() - L2.getR())));

        ParallelCircuit circuit = new ParallelCircuit();
        circuit.addSource(source);
        circuit.addComponent(R1);
        circuit.addComponent(L2);
        circuit.addComponent(C3);
        circuit.addComponent(R4);
        circuit.addComponent(R5);
        circuit.addComponent(R5);
        circuit.removeComponent();

        circuit.calculateV();
        circuit.calculateI();
        circuit.displayAnalysis();
		new ElictricalApplication(circuit);
	}
}