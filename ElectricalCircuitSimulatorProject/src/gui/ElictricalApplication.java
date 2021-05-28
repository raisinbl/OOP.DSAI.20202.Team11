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
        ParallelCircuit circuit = new ParallelCircuit();
		new ElictricalApplication(circuit);
	}
}