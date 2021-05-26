package board;

import board.component.Component;
import board.source.Source;

import java.util.ArrayList;

public abstract class Circuit {
    private Source source;
    private static final int MAX_COMPONENTS = 5;
    private int nComponents = 0;
    private ArrayList<Component> componentsList = new ArrayList<Component>();

    public Circuit(Source source) {
        this.source = source;
    }

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

    public Source getSource() {
        return source;
    }

    public int getnComponents() {
        return nComponents;
    }

    public ArrayList<Component> getComponentsList() {
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
