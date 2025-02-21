import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    private ArrayList<Element> elements;
    private int size = 20;
    private int columns = 5;
    private int rows = 4;

    public GameBoard() {
        int pairs = 10;
        this.elements = new ArrayList<>();
        String[] emojis = {
                "\uD83D\uDE00", "\uD83D\uDE02", "\uD83D\uDC99",
                "\uD83D\uDE0A", "\uD83D\uDE0D", "\uD83C\uDF4E",
                "\uD83D\uDC9A", "\uD83C\uDF49", "\uD83D\uDE2D", "\uD83D\uDE31"
        };
        for (int i = 0; i < pairs; i++) {
            String emoji = emojis[i];
            elements.add(new Element(emoji));
            elements.add(new Element(emoji));
        }
        Collections.shuffle(elements);
    }
    public void displayBoard() {
        // Print column indices header
        System.out.print("    "); // initial spacing for row labels
        for (int j = 0; j < columns; j++) {
            System.out.printf("%-5d", j);
        }
        System.out.println();

        // Print a separator line
        System.out.println("   -------------------------");

        // Print each row with its content
        for (int i = 0; i < 4; i++) {
            // Print row index at the beginning
            System.out.printf("%d |", i);
            for (int j = 0; j < 5; j++) {
                // Calculate index for the one-dimensional list (row-major order)
                int index = i * 5 + j;
                // Decide what to display: the emoji if revealed, or the index if not
                Element element = elements.get(index);
                String displayIndex = element.isVisible() ? element.getValue() : String.valueOf(index);
                // Print the cell with a width of 4 characters
                System.out.printf(" %-3s|", displayIndex);
            }
            System.out.println();
            // Print a separator line after each row
            System.out.println("    -------------------------");
        }
    }

    public boolean allVisible(){
        for (Element element : elements) {
            if (!element.isVisible())
                return false;
        }
        return true;
    }
    public Element getElement(int index){
        return elements.get(index);
    }

}
