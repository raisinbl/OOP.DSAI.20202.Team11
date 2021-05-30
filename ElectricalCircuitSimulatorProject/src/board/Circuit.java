package board;

import board.component.Component;
import board.source.Source;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Circuit {
    private Source source;
    private static final int MAX_COMPONENTS = 5;
    private int nComponents = 0;
    private ObservableList<Component> componentsList = FXCollections.observableArrayList();

    public void addComponent(Component component) {
        if (nComponents >= MAX_COMPONENTS) {
            System.out.println("Can't add more components! Max number is reached!");
        }
        else {
            componentsList.add(component);
            nComponents++;
            component.setId(component.getPrefix() + nComponents);
            System.out.println("Component " + component.getId() + " has been added to the circuit.");
            System.out.println(component);
        }
    }
    public void removeAllComponent() {
    	componentsList.clear();
    	}

    public void removeComponent() {
        System.out.println(componentsList.remove(componentsList.size() - 1).getId() + " has been removed from the circuit.");
        nComponents--;
    }
    
    public void resetNComponents() {
    	nComponents = 0;
    }
    
    public Source getSource() {
        return source;
    }
    public void addSource(Source source) {
    	this.source = source;
    	System.out.println("Source has been added");
    }
    public int getnComponents() {
        return nComponents;
    }

    public ObservableList<Component> getComponentsList() {
        return componentsList;
    }
    
    public void displayAnalysis() {
        for (Component component: componentsList) {
            System.out.println(
                    component.getId() + " | V = " + component.getV() + "(V) | I = " + component.getI() + "(A) | R = " + component.getR() + "(Ω)"
            );
        }
    }
}
