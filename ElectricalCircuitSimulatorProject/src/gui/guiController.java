package gui;

import board.Circuit;
import board.ParallelCircuit;
import board.SerialCircuit;
import board.component.Capacitor;
import board.component.Component;
import board.component.Inductor;
import board.component.Resistor;
import board.component.complex.Complex;
import board.source.Source;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Callback;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class guiController {

    @FXML
    private TableView<Component> tbCircuitAnalysis;
    
    @FXML
    private TableColumn<Component, String> colID;
    @FXML
    private TableColumn<Component, Double> colI, colU, colR;
    
    @FXML
    private Pane diagramPane;
    @FXML 
    private Label boomTitle;
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
   @FXML
   private void btnExitPressed() {
	   new ElictricalApplication(circuit).pullThePlug();
   }
   // Submit //////////////////////////////////////////////////////////////
   @FXML
   void btnSubmitPressed(ActionEvent event) throws Exception{
	   try {
	   circuit.getComponentsList().clear();
	   circuit.resetNComponents();
	   source = null;
	   diagramPane.getChildren().clear();
//	   componentBox.i = 0;
	   checkCircuit();
	   getSource();
	   getComponent();
	   calculateCircuit();
	   circuit.displayAnalysis();
	   getData();
	   createDigram(circuit);
	   } catch (Exception exception) {
		   diagramPane.setVisible(false);
		   circuit.getComponentsList().clear();
		   diagramPane.getChildren().clear();
		   source = null;
	   }
	 }
	
	private void getData() {

    	colID.setCellValueFactory(new 
    			PropertyValueFactory<Component, String>("id"));
    	colR.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("R"));
    	colU.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("V"));
    	colI.setCellValueFactory(new 
    			PropertyValueFactory<Component, Double>("I"));
    	tbCircuitAnalysis.setItems(circuit.getComponentsList());
    	//set cell factory
    	colR.setCellFactory(new Callback<TableColumn<Component,Double>,TableCell<Component,Double>>(){

			@Override
			public TableCell<Component,Double> call(TableColumn<Component, Double> list) {
				// TODO Auto-generated method stub
				return new DoubleFormatCell();
			}
    	});
    	colI.setCellFactory(new Callback<TableColumn<Component,Double>,TableCell<Component,Double>>(){

			@Override
			public TableCell<Component,Double> call(TableColumn<Component, Double> list) {
				// TODO Auto-generated method stub
				return new DoubleFormatCell();
			}
    	});
    	colU.setCellFactory(new Callback<TableColumn<Component,Double>,TableCell<Component,Double>>(){

			@Override
			public TableCell<Component,Double> call(TableColumn<Component, Double> list) {
				// TODO Auto-generated method stub
				return new DoubleFormatCell();
			}
    	});
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
	
	private void getSource() throws NullPointerException{
		// TODO Auto-generated method stubs
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
						alert("Found input Exception","Please input a valid quantity for Frequency");
					}catch (NullPointerException exception)	{
						alert("Found input Exception","Please input a quantity for Frequency");
					}
				}
			}catch (Exception excpetion) {
				source = null;
				alert("Found input Exception","please input a quantity for Source");
			}	
		}
		this.circuit.addSource(this.source);
	}
	private void checkShortCircuit() {
		ImageView img = new 
				ImageView(new Image(getClass().getResourceAsStream("image/shortCircuit.gif")));
		img.setFitHeight(300);
		img.setFitWidth(160);
		img.setLayoutX(150);
		diagramPane.getChildren().add(img);
		img.setVisible(true);
		img.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				img.setVisible(false);
			}
		});
		
		Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle("Short circuit detected");
	    alert.setContentText("Be careful!! Try not to keep your smile last forever <3 ");
	    alert.setGraphic(img);
	    alert.showAndWait();
	}
	private void calculateCircuit() throws NullPointerException{
		// TODO Auto-generated method stub
		try {
			if(circuit instanceof ParallelCircuit) {
				ParallelCircuit PCircuit = (ParallelCircuit) circuit;
				PCircuit.calculateV();
				PCircuit.calculateI();
				if( PCircuit.checkShortCircuit()) {
					checkShortCircuit();
				}
			}else if (circuit instanceof SerialCircuit) {
				SerialCircuit SCircuit = (SerialCircuit) circuit;
				SCircuit.calculateI();
				SCircuit.calculateV();
				if( SCircuit.checkShortCircuit()) {
					checkShortCircuit();
				}
			}
		} catch (Exception exception) {
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
					alert("Found Input Exception", "Please input a valid quantity for component");
					this.circuit.getComponentsList().clear();
					this.diagramPane.getChildren().clear();		
			   }catch (NullPointerException exception) {
				   this.circuit.getComponentsList().clear();
				   this.diagramPane.getChildren().clear();
				   alert("Found Input Exception", "Please input a quantity for component");
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
	    circuit.getComponentsList().clear();
	    circuit = new Circuit();
	    //reset source
	   	btnAC.setSelected(false);
	   	btnDC.setSelected(false);
	   	FrequencyBox.setVisible(false);
	   	diagramPane.getChildren().clear();
	}
	void createDigram(Circuit circuit) throws Exception{
		try {
		double LayoutX = 30;
		double LayoutY = 50;
		ObservableList<Component> list = this.circuit.getComponentsList();
		//set a Label
		Label boom = new Label("BOOM!! Your Board");
		boom.setLayoutX(230);
		boom.setLayoutY(15);
		boom.setTextFill(Color.INDIANRED);
		diagramPane.getChildren().add(boom);
		
		ImageView imgSrc;
		if (source.getF() == 0) {
			imgSrc = new ImageView(new Image(getClass().getResourceAsStream("image/DC.png")));;		
		}else {
			imgSrc = new ImageView(new Image(getClass().getResourceAsStream("image/AC.png")));;
		}
		imgSrc.setFitHeight(35);
		imgSrc.setFitWidth(35);
		//a rounding method
		DecimalFormat round = new DecimalFormat("#.####");
		round.setRoundingMode(RoundingMode.CEILING);
		
		if(circuit instanceof ParallelCircuit) {	
			imgSrc.setLayoutX(LayoutX+30);
			imgSrc.setLayoutY(LayoutY+80);
			Label sourceInfo = new Label(source.getV() + " V");
			sourceInfo.setLayoutX(LayoutX+70);
			sourceInfo.setLayoutY(LayoutY+90);
			diagramPane.getChildren().addAll(imgSrc,sourceInfo);
			
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
					
					if(component instanceof Resistor) {
						ImageView imgR = new ImageView(new Image(getClass().getResourceAsStream("image/resistor.png")));
						imgR.setFitHeight(15);
						imgR.setFitWidth(35);
						imgR.setRotate(90);
						
						Label compZ = new Label(((Resistor)component).getR() + " " + "Ω");
						componentInfo.getChildren().addAll(compID,compZ);
						
						componentBox.getChildren().addAll(imgR,componentInfo);
						componentBox.setLayoutX(LayoutX+130+100*i);
						componentBox.setLayoutY(LayoutY+85);
					}else if(component instanceof Capacitor) {
						ImageView imgC = new ImageView(new Image(getClass().getResourceAsStream("image/Capacitor.png")));
						imgC.setFitHeight(35);
						imgC.setFitWidth(15);
						imgC.setRotate(90);
						
						Label compZ = new Label(((Capacitor)component).getC() + " " + "F");
						componentInfo.getChildren().addAll(compID,compZ);
						
						componentBox.setLayoutX(LayoutX+140+100*i);
						componentBox.setLayoutY(LayoutY+80);
						componentBox.setSpacing(15);
						componentBox.getChildren().addAll(imgC,componentInfo);
					}else if(component instanceof Inductor) {
						ImageView imgL = new ImageView(new Image(getClass().getResourceAsStream("image/Inductor.png")));
						imgL.setFitHeight(15);
						imgL.setFitWidth(35);
						imgL.setRotate(90);
						
						Label compZ = new Label(((Inductor)component).getL() + " " + "H");
						componentInfo.getChildren().addAll(compID,compZ);
						componentBox.setLayoutX(LayoutX+130+100*i);
						componentBox.setLayoutY(LayoutY+85);
						componentBox.getChildren().addAll(imgL,componentInfo);
					}
					diagramPane.getChildren().addAll(lineH_up,lineH_down,componentBox);
				}
				
				
			}
		}else if (circuit instanceof SerialCircuit) {
			//add source
			imgSrc.setLayoutX(LayoutX+110*list.size()/2);
			imgSrc.setLayoutY(LayoutY+128);
			Label sourceInfo = new Label(source.getV() + " V");
			sourceInfo.setLayoutX(LayoutX+110*list.size()/2);
			sourceInfo.setLayoutY(LayoutY+110);
			diagramPane.getChildren().addAll(imgSrc,sourceInfo);
			
			//add two VLine
			for (int i = 0; i<2; i++) {
				Line lineV = new Line (0,0,100,0);
				lineV.setLayoutX(LayoutX+i*100*list.size());
				lineV.setLayoutY(LayoutY+100);
				lineV.setRotate(90);
				diagramPane.getChildren().add(lineV);	
			}
			
			for (int i = 0; i <= list.size(); i++) {
				Line lineH_up = new Line (0,0,100,0);
				Line lineH_down = new Line (0,0,100,0);
				lineH_up.setLayoutX(LayoutX+50+100*i);
				lineH_up.setLayoutY(LayoutY+50);
				lineH_down.setLayoutX(LayoutX+50+100*i);
				lineH_down.setLayoutY(LayoutY+150);
				if (i<list.size()) {
					Component component = (Component) list.toArray()[i];
					VBox componentBox = new VBox();
					VBox componentInfo = new VBox();
					Label compID = new Label(component.getId());
										
					if(component instanceof Resistor) {
						ImageView imgR = new ImageView(new Image(getClass().getResourceAsStream("image/resistor.png")));
						imgR.setFitHeight(15);
						imgR.setFitWidth(35);
						Label compZ = new Label(((Resistor)component).getR() + " " + "Ω");
						componentInfo.getChildren().addAll(compID,compZ);
						componentBox.getChildren().addAll(componentInfo,imgR);
						componentBox.setLayoutX(LayoutX+80+100*i);
						componentBox.setLayoutY(LayoutY+6);
					}else if(component instanceof Capacitor) {
						ImageView imgC = new ImageView(new Image(getClass().getResourceAsStream("image/Capacitor.png")));
						imgC.setFitHeight(35);
						imgC.setFitWidth(15);
						Label compZ = new Label(((Capacitor)component).getC() + " " + "F");
						componentInfo.getChildren().addAll(compID,compZ);
						componentBox.setLayoutX(LayoutX+80+100*i);
						componentBox.setLayoutY(LayoutY);
						componentBox.getChildren().addAll(componentInfo,imgC);
					}else if(component instanceof Inductor) {
						ImageView imgL = new ImageView(new Image(getClass().getResourceAsStream("image/Inductor.png")));
						imgL.setFitHeight(15);
						imgL.setFitWidth(35);
						Label compZ = new Label(((Inductor)component).getL() + " " + "H");
						componentInfo.getChildren().addAll(compID,compZ);
						componentBox.setLayoutX(LayoutX+80+100*i);
						componentBox.setLayoutY(LayoutY+8);
						componentBox.getChildren().addAll(componentInfo,imgL);
					}
					diagramPane.getChildren().addAll(lineH_down,lineH_up,componentBox);
				}
				
			}
		}
		}catch(NullPointerException exception) {
			circuit.removeAllComponent();
			diagramPane.getChildren().clear();
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
class DoubleFormatCell extends TableCell<Component,Double> {

    public DoubleFormatCell() {    }
      
	@Override protected void updateItem(Double item, boolean empty) {
        
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
        	if(item.isInfinite()) {
        		setText("∞");
        		setTextFill(Color.RED);
        	}else {
        		String format;
        		if (item < 1 && (item * 100 - Math.round(item * 100) != 0)) {
        			format = String.format("%.2e", item);
        		}
        		else {
        			format = String.format("%.2f", item);
        		}
        		setText(format);
        	}
            
        }         
    }
}



