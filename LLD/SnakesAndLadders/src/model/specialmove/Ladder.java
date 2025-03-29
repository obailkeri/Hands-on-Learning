package model.specialmove;

public class Ladder extends SpecialMove {
    private final int target;

    public Ladder(int source, int target) {
        super(source);
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}
