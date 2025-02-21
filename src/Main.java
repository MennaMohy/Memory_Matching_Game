import java.util.Scanner;

public class Main {

    private static void displayInstructions() {
        System.out.println("\n========== Instructions ==========");
        System.out.println("1. The Memory Matching Game is played on a board with 20 cards (10 pairs).");
        System.out.println("2. Each card hides an emoji. Your goal is to find matching pairs.");
        System.out.println("3. At the start of the game, all cards will be briefly revealed for 3 seconds so you can memorize their positions.");
        System.out.println("4. During your turn, you will select two cards by entering their indices.");
        System.out.println("5. If the selected cards match, they remain visible and you score a point.");
        System.out.println("6. If they do not match, the cards will be hidden again.");
        System.out.println("7. Two players take turns; if you find a match, you get another turn.");
        System.out.println("8. Once per game, you can use a hint to reveal a card's value to help you remember its location.");
        System.out.println("9. The game continues until all pairs are revealed.");
        System.out.println("10. The player with the most points at the end wins.");
        System.out.println("\nPress Enter to return to the main menu...");
        new Scanner(System.in).nextLine();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("Welcome to MEMORY MATCHING GAME !");
        System.out.println("==================================\n");

        while (true) {
            System.out.println("Choose:\nA) Display instructions\nB) Start the Game\nC) Exit");
            String answer = scanner.nextLine().toUpperCase();

            switch (answer) {
                case "A":
                    displayInstructions();
                    break;

                case "B":
                    System.out.print("Enter player 1's name: ");
                    String playerName1 = scanner.nextLine();

                    System.out.print("Enter player 2's name: ");
                    String playerName2 = scanner.nextLine();

                    Player player1 = new Player(playerName1);
                    Player player2 = new Player(playerName2);

                    // Create a Game object and start the game
                    Game game = new Game(player1,player2);
                    game.start();
                    break;

                case "C":
                    System.out.println("GoodBye!");
                    return;  // Exit the program

                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }
}
