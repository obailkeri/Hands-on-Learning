import model.Board;
import model.Player;
import model.dice.Dice;
import model.dice.NormalDice;
import model.specialmove.Ladder;
import model.specialmove.Snake;

import java.util.*;

public class Game {
    public static void main(String[] args) throws Exception {
        System.out.println("========================");
        System.out.println("Snakes and Ladders!");
        System.out.println("========================");

        Board board = createBoard();
        Queue<Player> playerQueue = getPlayers();
        Dice dice = new NormalDice();

        ArrayList<Player> winnerList = play(playerQueue, board, dice);
        printRankList(winnerList);

        System.out.println("Game ends. Thank you for playing!");
    }

    private static Board createBoard() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Let's setup our board");
        System.out.print("Board size : ");
        int n = sc.nextInt();
        Board board = new Board(n);

        System.out.println("\nHow many snakes to add?");
        n = sc.nextInt();
        if (n < 0) throw new Exception();
        if (n != 0) System.out.println("Enter mouth and tail of each snake");
        for (int i = 0; i < n; i++) {
            System.out.print("Snake " + (i+1) + ": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            if(!board.addSpecialMove(new Snake(src, dest))) {
                System.out.println("Cannot add Snake! Cell already contains special move. Enter different one.");
                i--;
            }
        }

        System.out.println("\nHow many ladders to add?");
        n = sc.nextInt();
        if (n < 0) throw new Exception();
        if (n != 0) System.out.println("Enter base and top of each ladder");
        for (int i = 0; i < n; i++) {
            System.out.print("Ladder " + (i+1) + ": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            if(!board.addSpecialMove(new Ladder(src, dest))) {
                System.out.println("Cannot add Ladder! Cell already contains special move. Enter different one.");
                i--;
            }
        }
        return board;
    }

    private static Queue<Player> getPlayers() {
        Scanner sc = new Scanner(System.in);
        Queue<Player> playerQueue = new LinkedList<>();

        System.out.println("\nGet number of players : ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Player player = new Player("Player " + (i + 1));
            playerQueue.add(player);
        }
        return playerQueue;
    }

    private static ArrayList<Player> play(Queue<Player> playerQueue, Board board, Dice dice) {
        System.out.println("Let's start playing!");
        int winnerRank = 1;
        ArrayList<Player> winnerList = new ArrayList<>();
        while (playerQueue.size() != 1) {
            Player player = playerQueue.poll();
            System.out.println(Optional.ofNullable(player).map(Player::getName).orElseThrow() + "'s turn : Press Enter to roll");
            int rolledNum = dice.rollDice();
            System.out.println("Rolled : " + rolledNum);

            int pos = Optional.of(player).map(Player::getPosition).orElseThrow();
            if (pos + rolledNum == board.getFinishStep()) {
                System.out.println("Player " + player.getName() + " wins!");
                System.out.println("Winner No " + winnerRank + " => " + player.getName());
                winnerList.add(player);
                winnerRank++;
            } else if (pos + rolledNum > board.getFinishStep()) {
                System.out.println("Cannot move!");
                System.out.println("Player position " + pos);
                playerQueue.add(player);
            } else {
                pos = board.checkAndGetSpecialMove(pos + rolledNum);
                System.out.println("Moved to position -> " + pos);
                player.setPosition(pos);
                playerQueue.add(player);
            }
            System.out.println();
        }
        return winnerList;
    }

    private static void printRankList(ArrayList<Player> winnerList) {
        System.out.println("========================");
        System.out.println("Final Rank List");
        System.out.println("========================");
        for(int i=0; i<winnerList.size(); i++) {
            System.out.println("Rank " + (i+1) + " : " + winnerList.get(i).getName());
        }
        System.out.println("========================");
    }
}