import java.util.Scanner;
import java.awt.Toolkit;

public class Game {
    private Player player1;
    private Player player2;
    private GameBoard board;
    private int pairs;
    private int size;
    private Scanner scanner = new Scanner(System.in);

    public Game(Player player1, Player player2,int size) {
        this.player1 = player1;
        this.player2 = player2;
        this.pairs = size/2;
        this.board = new GameBoard(size);
        this.size = size;
    }

    public void start() {
        System.out.println("\nLet's start the game! All the cards will be shown for 3 seconds");
        System.out.println("       " + player1.getName() + " VS " + player2.getName());
        System.out.println("Ready...");
        try {
            Thread.sleep(1000); // Pause for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Steady...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GO!...");
        Toolkit.getDefaultToolkit().beep();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Reveal all cards for 3 seconds
        for (int i = 0; i < size; i++) {
            board.getElement(i).setVisible(true);
        }
        board.displayBoard();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hide all cards after the pause
        for (int i = 0; i < size; i++) {
            board.getElement(i).setVisible(false);
        }

        boolean isPlayer1Turn = true;

        // Main game loop
        while (!board.allVisible()) {
            Player currentPlayer = isPlayer1Turn ? player1 : player2;
            System.out.println("\n" + currentPlayer.getName() + "'s turn:");
            board.displayBoard();

            // Offer hint at the start of the turn if not already used
            if (!currentPlayer.isHelpUsed() && !(currentPlayer instanceof RandomPlayer)) {
                System.out.print(currentPlayer.getName() + ", do you want a hint before picking? (Y/N): ");
                String hint = scanner.next();
                if (hint.equalsIgnoreCase("Y")) {
                    currentPlayer.setHelpUsed(true);
                    showCard();
                }
            }

            // Get first card index
            int pos1 = -1;
            if (currentPlayer instanceof RandomPlayer) {
                pos1 = ((RandomPlayer) currentPlayer).getMove(size);
                System.out.println(currentPlayer.getName() + " selects card index: " + pos1);
                while (board.getElement(pos1).isVisible()) {
                    pos1 = ((RandomPlayer) currentPlayer).getMove(size);
                }
            } else {
                System.out.print("Enter first card index to show: ");
                while (true) {
                    if (scanner.hasNextInt()) {
                        pos1 = scanner.nextInt();
                        if (pos1 >= 0 && pos1 < size) {
                            if (board.getElement(pos1).isVisible()) {
                                System.err.println("The card is already visible! Please choose another card.");
                            } else {
                                break;
                            }
                        } else {
                            System.err.println("Invalid input! Please try again");
                        }
                    } else {
                        System.err.println("Invalid input! Please try again");
                        scanner.next();
                    }
                }
            }
            Element e1 = board.getElement(pos1);
            e1.setVisible(true);
            board.displayBoard();

            // Get second card index
            int pos2 = -1;
            if (currentPlayer instanceof RandomPlayer) {
                pos2 = ((RandomPlayer) currentPlayer).getMove(size);
                System.out.println(currentPlayer.getName() + " selects card index: " + pos2);
                while (pos2 == pos1 || board.getElement(pos2).isVisible()) {
                    pos2 = ((RandomPlayer) currentPlayer).getMove(size);
                }
            } else {
                System.out.print("Enter second card index to show: ");
                while (true) {
                    if (scanner.hasNextInt()) {
                        pos2 = scanner.nextInt();
                        if (pos2 >= 0 && pos2 < size) {
                            if (pos2 == pos1) {
                                System.err.println("You cannot choose the same card twice! Please choose another card.");
                            } else if (board.getElement(pos2).isVisible()) {
                                System.err.println("The card is already visible! Please choose another card.");
                            } else {
                                break;
                            }
                        } else {
                            System.err.println("Invalid input! Please try again");
                        }
                    } else {
                        System.err.println("Invalid input! Please try again");
                        scanner.next();
                    }
                }
            }
            Element e2 = board.getElement(pos2);
            e2.setVisible(true);
            board.displayBoard();

            // Check for a match
            if (e1.getValue().equals(e2.getValue())) {
                System.out.println("It's a match!");
                currentPlayer.increaseScore(1);
                // Optional: Leave the cards visible or mark them as matched
            } else {
                // Pause briefly so players can see the second card before hiding both
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                e1.setVisible(false);
                e2.setVisible(false);
                isPlayer1Turn = !isPlayer1Turn; // Switch turn if no match
            }
        }

        // Game over: all pairs have been revealed.
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
        System.out.println();
    }

    private void showCard() {
        System.out.print("Enter the card index for a hint: ");
        int pos = -1;
        while (true) {
            if (scanner.hasNextInt()) {
                pos = scanner.nextInt();
                if (pos >= 0 && pos < size) {
                    if (board.getElement(pos).isVisible()) {
                        System.err.println("The card is already visible! Please choose another card.");
                    } else {
                        break;
                    }
                } else {
                    System.err.println("Invalid input! Please enter a number between 0 and 19.");
                }
            } else {
                System.err.println("Invalid input!");
                scanner.next();
            }
        }
        Element e = board.getElement(pos);
        e.setVisible(true);
        board.displayBoard();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        e.setVisible(false);
        board.displayBoard();
    }
}
