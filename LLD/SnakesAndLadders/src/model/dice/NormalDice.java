package model.dice;

import java.util.Random;

public class NormalDice implements Dice {
    private int n;

    public NormalDice() {
        this.n = 6;
    }

    public NormalDice(int n) {
        this.n = n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(n) + 1;
    }
}
