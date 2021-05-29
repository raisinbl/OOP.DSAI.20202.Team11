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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import board.component.*;
import board.component.complex.Complex;
import board.source.Source;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;

import board.*;

public class guiController {

    @FXML
    private TableView<Component> tbCircuitAnalysis;
    
    @FXML
    private TableColumn<Component, String> colID;
    @FXML
    private TableColumn<Component, Double> colI, colU;
    @FXML
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
		   if(componentBox.i >0) {
			   componentBox.i -- ; 
			   circuit.removeComponent();
		   }
	   } catch(IndexOutOfBoundsException exception){
	   }
   }
   
   // Submit //////////////////////////////////////////////////////////////
   @FXML
   void btnSubmitPressed(ActionEvent event) throws Exception{
	   circuit.removeAllComponent();
	   circuit.resetNComponents();
	   componentBox.i = 0;
	   checkCircuit();
	   getSource();
	   getComponent();
	   calculateCircuit();
	   try {
		   circuit.displayAnalysis();
	   } catch (NullPointerException exception) {}
	  
	   getData();
	   createDigram(circuit);
	 }
	private void getData() {
    	colID.setCellValueFactory(new 
    			PropertyValueFactory<Component, String>("id"));
    	colR.setCellValueFactory(new 
    			PropertyValueFactory<Component, Complex>("R"));
    	colU.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("V"));
    	colI.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("I"));
    	tbCircuitAnalysis.setItems(circuit.getComponentsList());
	}
	private void alert (String title, String content) {
		Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	
	private void checkCircuit() {
		// TODO Auto-generated method stub
		if (!(circuit instanceof ParallelCircuit)&& !(circuit instanceof SerialCircuit)) {
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
	private void calculateCircuit() throws NullPointerException{
		// TODO Auto-generated method stub
		try {
			if(circuit instanceof ParallelCircuit) {
				ParallelCircuit PCircuit = (ParallelCircuit) circuit;
				PCircuit.calculateI();
				PCircuit.calculateV();
			}else if (circuit instanceof SerialCircuit) {
				SerialCircuit SCircuit = (SerialCircuit) circuit;
				SCircuit.calculateI();
				SCircuit.calculateV();
			}
		} catch (NullPointerException exception) {
//			alert("Circuit type choosing ALERT!","You have not choose a Circuit type yet");
		}
	}
	
	
	private void getComponent() throws NumberFormatException,NullPointerException{
		// TODO Auto-generated method stub
		try {
		for	(Node hbox: contentBox.getChildren()) {
			   componentBox comp = (componentBox)hbox;
			   
				   if (comp.component instanceof Resistor) {
					   Resistor compR = (Resistor) comp.component;
					   compR.setR(new Complex(Double.parseDouble(comp.quantity.getText()),0));
					   circuit.addComponent(compR);
					   
				   }else if (comp.component instanceof Capacitor) {
					   Capacitor compC = (Capacitor)comp.component;
					   compC.setC(Double.parseDouble(comp.quantity.getText()));
					   compC = new Capacitor(compC.getC(), source.getF());
					   circuit.addComponent(compC);
					   
				   }else if (comp.component instanceof Inductor) {
					   Inductor compI = (Inductor) comp.component;
					   compI.setL(Double.parseDouble(comp.quantity.getText()));
					   compI = new Inductor(compI.getL(), source.getF());
					   circuit.addComponent(compI);
				   }
			   }
			}catch (NumberFormatException exception){
					alert("Found Input Exception", "Please input a quantity for component");
							
			   }catch (NullPointerException exception) {
				   
			   }
	}
	void resetFactory() {
		//reset component box
		contentBox.getChildren().clear();
		componentBox.i = 0;
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
	   	diagramPane.getChildren().clear();
	}
	void createDigram(Circuit circuit) throws Exception {
		double LayoutX = 0;
		double LayoutY = 0;
		ObservableList<Component> list = this.circuit.getComponentsList();
		ImageView imgAC = new ImageView(new Image(getClass().getResourceAsStream("image/AC.png")));
		imgAC.setFitHeight(35);
		imgAC.setFitWidth(35);
		
		ImageView imgDC = new ImageView(new Image(getClass().getResourceAsStream("image/DC.png")));
		imgDC.setFitHeight(35);
		imgDC.setFitWidth(35);
		
		ImageView imgR = new ImageView(new Image(getClass().getResourceAsStream("image/resistor.png")));
		imgR.setFitHeight(35);
		imgR.setFitWidth(15);
		
		ImageView imgL = new ImageView(new Image(getClass().getResourceAsStream("image/Inductor.png")));
		imgR.setFitHeight(35);
		imgR.setFitWidth(15);
		
		ImageView imgC = new ImageView(new Image(getClass().getResourceAsStream("image/Capacitor.png")));
		imgR.setFitHeight(15);
		imgR.setFitWidth(35);
		
		if(circuit instanceof ParallelCircuit) {
			ImageView imgSrc;
			if (source.getF() == 0) {
				imgSrc = imgDC;
				imgSrc.setLayoutX(30);
				imgSrc.setLayoutY(80);
			}else {
				imgSrc = imgAC;
				imgSrc.setLayoutX(30);
				imgSrc.setLayoutY(80);
			}
			imgR.setRotate(90);
			imgC.setRotate(90);
			imgL.setRotate(90);
			diagramPane.getChildren().add(imgSrc);
			for (int i = 0; i <= list.size(); i++) {
				Line lineV = new Line (0,0,100,0);

				lineV.setLayoutX(LayoutX+100*i);
				lineV.setLayoutY(LayoutY+100);
				lineV.setRotate(90);
				diagramPane.getChildren().add(lineV);
				if (i< list.size()) {
					Line lineH_up = new Line (0,0,100,0);
					Line lineH_down = new Line (0,0,100,0);
					lineH_up.setLayoutX(LayoutX+50+100*i);
					lineH_up.setLayoutY(LayoutY+50);
					lineH_down.setLayoutX(LayoutX+50+100*i);
					lineH_down.setLayoutY(LayoutY+150);
					
					Component component = (Component) list.toArray()[i];
					HBox componentBox = new HBox();
					VBox componentInfo = new VBox();
					Label compID = new Label(component.getId());
					Label compZ = new Label(component.getR() + " (" + component.getUnit() + ")");
					componentInfo.getChildren().addAll(compID,compZ);
					
					if(component instanceof Resistor) {
						ImageView img = new ImageView();
						img = imgR;
						componentBox.getChildren().addAll(img,componentInfo);
						componentBox.setLayoutX(LayoutX+30+50*i);
						componentBox.setLayoutY(LayoutY+85);
						diagramPane.getChildren().add(componentBox);
					}else if(component instanceof Capacitor) {
						componentBox.getChildren().addAll(imgC,componentInfo);
					}else if(component instanceof Inductor) {
						componentBox.getChildren().addAll(imgL,componentInfo);
					}
					diagramPane.getChildren().addAll(lineH_up,lineH_down);
				}
				
				
			}
		}
		
	}
}


class componentBox extends HBox{
	
	static int i = 0;
	TextField quantity = new TextField();
	Component component;
//	private HBox root = new HBox();
	public void initialize(Component component) {
		this.component = component;
		this.setSpacing(10);
		i++;
		Label name = new Label(component.getPrefix() + i);
		
		component.setId(component.getPrefix() + i);
		
//		TextField quantity = new TextField();
		quantity.setPrefSize(250, 20);
		
		Label unit = new Label(component.getUnit());
		
		this.getChildren().addAll(name,quantity,unit);
	}
}




