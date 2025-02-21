import java.util.Scanner;
import java.awt.Toolkit;

public class Game {
    private Player player1;
    private Player player2;
    private GameBoard board;
    private int pairs;
    private Scanner scanner = new Scanner(System.in);

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.pairs = 10;
        this.board = new GameBoard();
    }

    public void start() {
        System.out.println("\nLet's start the game! All the cards will be shown for 3 seconds");
        System.out.println("       "+player1.getName() + " VS " + player2.getName());
        System.out.println("Ready...");
        try {
            Thread.sleep(1000); // Pause for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Steady...");
        try {
            Thread.sleep(1000); // Pause for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GO!...");
        Toolkit.getDefaultToolkit().beep();
        try {
            Thread.sleep(1000); // Pause for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Reveal all cards for 3 seconds
        for (int i = 0; i < 20; i++) {
            board.getElement(i).setVisible(true);
        }
        board.displayBoard();
        try {
            Thread.sleep(3000); // Pause for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hide all cards after the pause
        for (int i = 0; i < 20; i++) {
            board.getElement(i).setVisible(false);
        }
        // true if it's player1's turn, false if it's player2's turn
        boolean isPlayer1Turn = true;

        // Main game loop
        while (!board.allVisible()) {
            Player currentPlayer = isPlayer1Turn ? player1 : player2;
            System.out.println("\n" + currentPlayer.getName() + "'s turn:");
            board.displayBoard();

            // Get first card
            System.out.print("Enter first card index to show: ");
            int pos1 = scanner.nextInt();
            if (pos1 < 0 || pos1 >= 20) {
                System.err.println("Invalid position! Please try again.");
                continue;
            }
            Element e1 = board.getElement(pos1);
            e1.setVisible(true);
            board.displayBoard();

            // Get second card
            System.out.print("Enter second card index to show: ");
            int pos2 = scanner.nextInt();
            if (pos2 < 0 || pos2 >= 20 || pos1 == pos2) {
                System.err.println("Invalid position! Please try again.");
                // Hide the first card if second is invalid
                e1.setVisible(false);
                continue;
            }
            Element e2 = board.getElement(pos2);
            e2.setVisible(true);
            board.displayBoard();

            // Check if they match
            if (e1.getValue().equals(e2.getValue())) {
                System.out.println("It's a match!");
                currentPlayer.increaseScore(1);
                // currentPlayer gets another turn
            } else {
                e1.setVisible(false);
                e2.setVisible(false);
                // Change turn to the other player only if no match was found.
                isPlayer1Turn = !isPlayer1Turn;
            }

            // Optional: Help option (hint) for the current turn
            System.out.print("Do you want a hint? (Y/N): ");
            String hint = scanner.next();
            if (hint.equalsIgnoreCase("Y") && !currentPlayer.isHelpUsed()) {
                currentPlayer.setHelpUsed(true);
                showCard();
            }
        }

        System.out.println("\nCongratulations! All pairs have been revealed.");
        System.out.println(player1.getName() + "'s score: " + player1.getScore());
        System.out.println(player2.getName() + "'s score: " + player2.getScore());
        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " Wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println(player2.getName() + " Wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void showCard() {
        System.out.print("Enter the card index for a hint: ");
        int pos = scanner.nextInt();
        if (pos < 0 || pos >= 20) {
            System.err.println("Invalid position!");
            return;
        }
        Element e = board.getElement(pos);
        e.setVisible(true);
        board.displayBoard();
        // Optionally, you can hide the card again after the hint.
    }
}
