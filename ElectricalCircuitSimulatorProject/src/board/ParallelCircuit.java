package board;

import board.source.Source;

public class ParallelCircuit extends Circuit implements Calculator{
    public ParallelCircuit(Source source) {
        super(source);
    }

    @Override
    public void calculateI() {
    }

    @Override
    public void calculateV() {
    }
}
