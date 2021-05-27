package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import board.component.*;
import board.component.complex.Complex;
import board.source.Source;
import board.*;

public class guiController {

    @FXML
    private TableView<Component> tbCircuitAnalysis;
    
    @FXML
    private TableColumn<Component, String> colID;
    private TableColumn<Component, Double> colI, colU;
    private TableColumn<Component, Complex> colR;
    
    @FXML
    private ScrollPane waitingPane;
    
    @FXML
    private Pane diagramPane;
    @FXML
    private AnchorPane componentPane;
    
    @FXML
    private Button btnSerial, btnParralel;
    
    @FXML
    private Button btnAC, btnDC;

    @FXML
    private Button  btnAddR, btnAddL, btnAddC;
    
    @FXML
    private Button btnCreateNewCircuit, btnRemove, btnSubmit, btnExit;
    
    static private Circuit circuit;
//    private ParallelCircuit PCircuit;
//    private SerialCircuit SCircuit;
    
    public guiController(Circuit circuit) {
    	super();
    	this.circuit = circuit;
    }
    @FXML
    void btnCreateNewCircuitPressed(ActionEvent event) {
    	circuit.removeAllComponent();
    }
   @FXML
    private void initialize() {
    	colID.setCellValueFactory(new 
    			PropertyValueFactory<Component, String>("Component"));
    	colR.setCellValueFactory(new 
    			PropertyValueFactory<Component, Complex>("R (Ω)"));
    	colU.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("U (V)"));
    	colI.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("I (A)"));
    	tbCircuitAnalysis.setItems(this.circuit.getComponentsList());
    	
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
   void addComponentX(ActionEvent event) {
	   componentBox componentBox = new componentBox();
	   componentBox.initialize();
   }
}


class componentBox{
	
	private HBox root = new HBox();
	private Component component;
	public void initialize() {
		root.setSpacing(10);
		Label name = new Label(component.getId());
		
		TextField quantity = new TextField();
		
		Label unit = new Label(component.getUnit());
		
		root.getChildren().add(name);
		root.getChildren().add(quantity);
		root.getChildren().add(unit);
	}
}
