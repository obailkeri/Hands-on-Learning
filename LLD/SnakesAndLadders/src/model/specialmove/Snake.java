package model.specialmove;

public class Snake extends SpecialMove {
    private final int target;

    public Snake(int source, int target) {
        super(source);
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}
