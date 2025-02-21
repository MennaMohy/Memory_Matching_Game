import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    private ArrayList<Element> elements;
    private int size;
    private int rows;
    private int cols;

    public GameBoard(int size) {
        this.size = size;
        this.elements = new ArrayList<>();

        // Determine board dimensions based on the provided size.
        if (size == 12) {
            rows = 3;
            cols = 4;
        } else if (size == 16) {
            rows = 4;
            cols = 4;
        } else if (size == 20) {
            rows = 4;
            cols = 5;
        } else {
            // Fallback: try to create a roughly square board.
            rows = (int) Math.sqrt(size);
            cols = size / rows;
        }

        // Calculate the number of pairs.
        int pairs = size / 2;

        // An array of emojis. We have more than enough for our possible pair counts.
        String[] emojis = {
                "\uD83D\uDE00", "\uD83D\uDE02", "\uD83D\uDC99",
                "\uD83D\uDE0A", "\uD83D\uDE0D", "\uD83C\uDF4E",
                "\uD83D\uDC9A", "\uD83C\uDF49", "\uD83D\uDE2D", "\uD83D\uDE31"
        };

        // Create pairs of elements with matching emojis.
        for (int i = 0; i < pairs; i++) {
            String emoji = emojis[i % emojis.length];
            elements.add(new Element(emoji));
            elements.add(new Element(emoji));
        }
        // Shuffle the elements so the pairs are randomly distributed.
        Collections.shuffle(elements);
    }

    public void displayBoard() {
        System.out.println(" -------------------------");
        int index = 0;
        // Print each row of the board.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Element element = elements.get(index);
                // If the card is visible, display the emoji; otherwise, show its index.
                String displayValue = element.isVisible() ? element.getValue() : String.valueOf(index);
                System.out.printf(" %-3s|", displayValue);
                index++;
            }
            System.out.println();
            System.out.println(" -------------------------");
        }
    }

    public boolean allVisible() {
        for (Element e : elements) {
            if (!e.isVisible()) {
                return false;
            }
        }
        return true;
    }

    public Element getElement(int index) {
        return elements.get(index);
    }
}
