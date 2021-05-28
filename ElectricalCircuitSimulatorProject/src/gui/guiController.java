package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import board.component.*;
import board.component.complex.Complex;
import board.source.Source;

import java.util.Iterator;

import board.*;

public class guiController {

    @FXML
    private TableView<Component> tbCircuitAnalysis;
    
    @FXML
    private TableColumn<Component, String> colID;
    private TableColumn<Component, Double> colI, colU;
    private TableColumn<Component, Complex> colR;
    
    @FXML
    private Pane diagramPane;
    
    @FXML
    private VBox contentBox;
    @FXML
    private RadioMenuItem btnAC,btnDC;
    @FXML
    private Button btnSerial, btnParralel;
    
    @FXML
    private Button  btnAddR, btnAddL, btnAddC;
    
    @FXML
    private Button btnCreateNewCircuit, btnRemove, btnSubmit, btnExit;
    @FXML
    private TextField sourceQuantity, frequencyQuantity;
    @FXML
    private HBox SourceBox, FrequencyBox;
    
    private Circuit circuit;
    private Source source;
//    private ParallelCircuit PCircuit;
//    private SerialCircuit SCircuit;
    
    public guiController(Circuit circuit) {
    	super();
    	this.circuit = circuit;
    }

    @FXML
    void btnCreateNewCircuitPressed(ActionEvent event) {
    	resetFactory();
    }
   @FXML
    private void initialize() {
	   
//    	colID.setCellValueFactory(new 
//    			PropertyValueFactory<Component, String>("Component"));
//    	colR.setCellValueFactory(new 
//    			PropertyValueFactory<Component, Complex>("R (Ω)"));
//    	colU.setCellValueFactory(new 
//    			PropertyValueFactory<Component, Double>("U (V)"));
//    	colI.setCellValueFactory(new 
//    			PropertyValueFactory<Component, Double>("I (A)"));
//    	tbCircuitAnalysis.setItems(this.circuit.getComponentsList());
//    	
//    	tbCircuitAnalysis.getSelectionModel().selectedItemProperty().addListener(
//    			(ChangeListener<? super Component>) new ChangeListener<Component>() {
//
//					@Override
//					public void changed(ObservableValue<? extends Component> observable, Component oldValue, Component newValue) {
//						// TODO Auto-generated method stub
//						if(newValue != null) {
//    						
//					}	
//    			}		
//    		});
    }
   
   @FXML
   	void btnSerrialPressed(ActionEvent event) {
	   btnSerial.setTextFill(Color.RED);
	   btnParralel.setTextFill(Color.GREY);
	   circuit = new SerialCircuit();
   	}
   @FXML
   void btnParrallelPressed(ActionEvent event) {
	   btnSerial.setTextFill(Color.GREY);
	   btnParralel.setTextFill(Color.RED);
	   circuit = new ParallelCircuit();
   }
   
   @FXML
   void btnSourcePressed(ActionEvent event) {
	   if (btnAC.isSelected()) {
		   FrequencyBox.setVisible(true);
	   }else {
		   FrequencyBox.setVisible(false);
		   frequencyQuantity.clear();
	   }
   }
   
   @FXML
   void btnAddRPressed(ActionEvent event) {
	   componentBox componentBox = new componentBox();
	   Resistor resistor = new Resistor();
	   if (contentBox.getChildren().size() >= 5) {
		}else {
		componentBox.initialize(resistor);
		contentBox.getChildren().add(componentBox);
		}
   }
   
   @FXML
   void btnAddLPressed(ActionEvent event) {
	   componentBox componentBox = new componentBox();
	   Inductor inductor = new Inductor();
		if (contentBox.getChildren().size() >= 5) {
		}else {
			componentBox.initialize(inductor);
			contentBox.getChildren().add(componentBox);
		}
   }
   
   @FXML
   void btnAddCPressed(ActionEvent event) {
	   componentBox componentBox = new componentBox();
	   Capacitor capacitor = new Capacitor();
	   if (contentBox.getChildren().size() >= 5) {
		}else {
			componentBox.initialize(capacitor);
			contentBox.getChildren().add(componentBox);
		}
   }
   
   @FXML
   void btnRemovePressed(ActionEvent event) throws Exception {
	   try {
	   contentBox.getChildren().remove((contentBox.getChildren().size()-1));
	   componentBox.i -- ;
	   } catch(IndexOutOfBoundsException exception){
//		   System.out.print("");
	   }
   }
   // Submit 
   @FXML
   void btnSubmitPressed(ActionEvent event) {
	   checkCircuit();
	   getSource();
	   getComponent();
	   calculateCircuit();
       circuit.displayAnalysis();
	 }
	private void checkCircuit() {
		// TODO Auto-generated method stub
		if ((circuit instanceof ParallelCircuit)||(circuit instanceof SerialCircuit)) {
			
		}else {
			alert("Circuit type choosing ALERT!","You have not choose a Circuit type yet");
		}
	}
	private void getSource() {
		// TODO Auto-generated method stub
		if ((btnDC.isSelected() == false) && (btnAC.isSelected()== false)){
			alert("Source type missing","Please select type of source");
		}else	{
			try {
				Double source = Double.parseDouble(sourceQuantity.getText());
				if (btnDC.isSelected()) {
					this.source = new Source(source,0);
				}else {
					try {
						Double freq = Double.parseDouble(frequencyQuantity.getText());
						this.source = new Source(source,freq);
					}catch (NumberFormatException excpetion) {
						alert("Found input Exception","Please input a quantity for Frequency");
					}
				}
			}catch (NumberFormatException excpetion) {
				alert("Found input Exception","please input a quantity for Source");
			}	
		}
		this.circuit.addSource(this.source);
	}
	private void calculateCircuit() {
		// TODO Auto-generated method stub
		if(circuit instanceof ParallelCircuit) {
			ParallelCircuit PCircuit = (ParallelCircuit) circuit;
			PCircuit.calculateV();
		    PCircuit.calculateI();
		}else if (circuit instanceof SerialCircuit) {
			SerialCircuit SCircuit = (SerialCircuit) circuit;
			SCircuit.calculateV();
			SCircuit.calculateI();
		}
	}
	
	private void alert (String title, String content) {
		Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	private void getComponent() throws NumberFormatException{
		// TODO Auto-generated method stub
		
		for	(Node hbox: contentBox.getChildren()) {
			   componentBox comp = (componentBox)hbox;
			   try {
				   if (comp.component instanceof Resistor) {
					   Resistor compR = (Resistor) comp.component;
					   compR.setR(new Complex(Double.parseDouble(comp.quantity.getText()),0));
					   circuit.addComponent(compR);
				   }else if (comp.component instanceof Capacitor) {
					   Capacitor compC = (Capacitor)comp.component;
					   compC.setC(Double.parseDouble(comp.quantity.getText()));
					   circuit.addComponent(compC);
				   }else if (comp.component instanceof Inductor) {
					   Inductor compI = (Inductor) comp.component;
					   compI.setL(Double.parseDouble(comp.quantity.getText()));
					   circuit.addComponent(compI);
				   }
			   }catch (NumberFormatException exception){
					alert("Found Input Exception", "Please input a quantity for component");
					circuit.removeAllComponent();
					componentBox.i = 1;
			   }
		}
	}
	void resetFactory() {
		//reset component box
		contentBox.getChildren().clear();
		componentBox.i = 1;
		sourceQuantity.clear();
		frequencyQuantity.clear();
		//reset circuit
		btnSerial.setTextFill(Color.BLACK);
	    btnParralel.setTextFill(Color.BLACK);
	    circuit = (Circuit)circuit;
	    circuit.removeAllComponent();
	    //reset source
	   	btnAC.setSelected(false);
	   	btnDC.setSelected(false);
	}
}

class componentBox extends HBox{
	
	static int i = 1;
	TextField quantity = new TextField();
	Component component;
//	private HBox root = new HBox();
	public void initialize(Component component) {
		this.component = component;
		this.setSpacing(10);
		Label name = new Label(component.getPrefix() + i);
		i++;
		component.setId(component.getPrefix() + i);
		
//		TextField quantity = new TextField();
		quantity.setPrefSize(250, 20);
		
		Label unit = new Label(component.getUnit());
		
		this.getChildren().addAll(name,quantity,unit);
	}
}


