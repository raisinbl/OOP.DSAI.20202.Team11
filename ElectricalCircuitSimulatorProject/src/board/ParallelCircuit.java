package board;

import board.component.Component;

public class ParallelCircuit extends Circuit implements Calculator{
	public ParallelCircuit() {
		super();
	}

	@Override
    public void calculateI() {
        if (!checkShortCircuit()) {
            for (Component component: getComponentsList()) {
                component.setI(getSource().getV() / component.getR());
            }
        }
        else {
            for (Component component: getComponentsList()) {
                if (component.getR() == 0) {
                    component.setI(Double.POSITIVE_INFINITY);
                }
                else {
                    component.setI(0);
                }
            }
        }
    }

    @Override
    public void calculateV() {
        for (Component component: getComponentsList()) {
            component.setV(getSource().getV());
        }
    }

    @Override
    public boolean checkShortCircuit() {
        for (Component component: getComponentsList()) {
            if (component.getR() == 0) {
                System.out.println("Short circuit detected!");
                return true;
            }
        }
        return false;
    }
}
