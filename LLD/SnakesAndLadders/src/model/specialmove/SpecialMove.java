package model.specialmove;

public abstract class SpecialMove {
    int source;

    public SpecialMove(int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }
}
