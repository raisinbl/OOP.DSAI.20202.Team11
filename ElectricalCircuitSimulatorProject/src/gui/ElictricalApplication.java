package gui;

import board.Circuit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ElictricalApplication extends JFrame{
	private Circuit circuit;
	
	public ElictricalApplication(Circuit circuit) {
		super();
		this.circuit = circuit;
		
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		this.setSize(1400,720);
		this.setTitle("Demo");
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
	public void pullThePlug() {
	    // this will make sure WindowListener.windowClosing() et al. will be called.
	    WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

	    // this will hide and dispose the frame, so that the application quits by
	    // itself if there is nothing else around. 
	    setVisible(false);
	    dispose();
	    // if you have other similar frames around, you should dispose them, too.

	    // finally, call this to really exit. 
	    // i/o libraries such as WiiRemoteJ need this. 
	    // also, this is what swing does for JFrame.EXIT_ON_CLOSE
	    System.exit(0); 
	}
	
	public static void main(String[] args) {
		new ElictricalApplication(new Circuit()); 
	}
}