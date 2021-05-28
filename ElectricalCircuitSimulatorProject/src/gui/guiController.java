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
//    private ParallelCircuit PCircuit;
//    private SerialCircuit SCircuit;
    
    public guiController(Circuit circuit) {
    	super();
    	this.circuit = circuit;
    }
    @FXML
    void btnCreateNewCircuitPressed(ActionEvent event) {
    	circuit.removeAllComponent();
    	contentBox.getChildren().clear();
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
   void btnSourcePressed(ActionEvent event) {
	   if (btnAC.isSelected()) {
		   FrequencyBox.setVisible(true);
	   }else {
		   FrequencyBox.setVisible(false);
	   }
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
   void btnRemovePressed(ActionEvent event) {
	   try {
	   contentBox.getChildren().remove((contentBox.getChildren().size()-1));
	   componentBox.i -- ;
	   } catch(IndexOutOfBoundsException exception){
		   System.out.print("");
	   }
   }
   
}

class componentBox extends HBox{
	
	static int i = 1;
//	private HBox root = new HBox();
	public void initialize(Component component) {
		
		this.setSpacing(10);
		Label name = new Label(component.getPrefix() + i);
		i++;
		component.setId(component.getPrefix() + i);
		
		TextField quantity = new TextField();
		quantity.setPrefSize(250, 20);
		
		Label unit = new Label(component.getUnit());
		
		this.getChildren().addAll(name,quantity,unit);
	}
	
}


