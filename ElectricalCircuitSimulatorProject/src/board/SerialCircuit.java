package board;

import board.source.Source;

public class SerialCircuit extends Circuit implements Calculator{
    public SerialCircuit() {
        super();
    }

    @Override
    public void calculateI() {
    }

    @Override
    public void calculateV() {
    }

    @Override
    public boolean checkShortCircuit() {
        return false;
    }
}
