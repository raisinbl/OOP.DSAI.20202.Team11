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
                component.setI(Math.round(getSource().getV() / component.getR() * 100.00) / 100.00);
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
            component.setV(Math.round(getSource().getV()*100)/100);
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
