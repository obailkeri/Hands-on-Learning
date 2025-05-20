package model;

import model.specialmove.Ladder;
import model.specialmove.Snake;
import model.specialmove.SpecialMove;

import java.util.ArrayList;

public class Board {
    private final int boardSize;
    private final int finishStep;

    private int[] boardCells;
    private ArrayList<SpecialMove> specialMoves;

    public Board() {
        boardSize = 10;
        finishStep = boardSize * boardSize;
        boardCells = new int[finishStep];
        specialMoves = new ArrayList<>();
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        finishStep = boardSize * boardSize;
        boardCells = new int[finishStep];
        specialMoves = new ArrayList<>();
    }

    public Board(int boardSize, ArrayList<SpecialMove> specialMoves) {
        this.boardSize = boardSize;
        this.finishStep = boardSize * boardSize;
        boardCells = new int[finishStep];
        this.specialMoves = specialMoves;
    }

    public boolean addSpecialMove(SpecialMove specialMove) {
        if (validateSpecialMove(specialMove)) {
            specialMoves.add(specialMove);
            return true;
        }
        return false;
    }

    private boolean validateSpecialMove(SpecialMove specialMove) {
        if (boardCells[specialMove.getSource() - 1] != 0) return false;
        else boardCells[specialMove.getSource() - 1] = 1;

        if (specialMove instanceof Snake) {
            if (boardCells[((Snake) specialMove).getTarget() - 1] != 0) {
                boardCells[specialMove.getSource() - 1] = 0;
                return false;
            }
            else boardCells[((Snake) specialMove).getTarget() - 1] = 1;
        }

        if (specialMove instanceof Ladder) {
            if (boardCells[((Ladder) specialMove).getTarget() - 1] != 0) {
                boardCells[specialMove.getSource() - 1] = 0;
                return false;
            }
            else boardCells[((Ladder) specialMove).getTarget() - 1] = 1;
        }

        return true;
    }

    public ArrayList<SpecialMove> getSpecialMoves() {
        return specialMoves;
    }

    public int getFinishStep() {
        return finishStep;
    }

    public int checkAndGetSpecialMove(int pos) {
        for (var it : specialMoves) {
            if (it.getSource() == pos) {
                if (it instanceof Ladder) {
                    System.out.println("Yes! Found a ladder at " + pos);
                    return ((Ladder) it).getTarget();
                }
                if (it instanceof Snake) {
                    System.out.println("Oops! Bitten by a snake at " + pos);
                    return ((Snake) it).getTarget();
                }
            }
        }
        return pos;
    }
}
