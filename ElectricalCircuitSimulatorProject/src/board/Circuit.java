package board;

import board.component.Component;
import board.source.Source;

public abstract class Circuit {
    private Source source;
    private static final int MAX_COMPONENTS = 5;
    private int nComponents = 0;
    private Component[] componentsList = new Component[MAX_COMPONENTS];

    public Circuit(Source source) {
        this.source = source;
    }

    public void addComponent(Component component) {
        if (nComponents >= MAX_COMPONENTS) {
            System.out.println("Can't add more components! Max number is reached!");
        }
        else {
            componentsList[nComponents] = component;
            nComponents++;
        }
    }

    public Source getSource() {
        return source;
    }

    public int getnComponents() {
        return nComponents;
    }

    public Component[] getComponentsList() {
        return componentsList;
    }
}
