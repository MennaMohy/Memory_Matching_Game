import java.util.Random;

public class RandomPlayer extends Player {
    private Random random;

    public RandomPlayer(String name) {
        super(name);
        this.random = new Random();
    }
    public int getMove(int size){
        return random.nextInt(size);
    }

}
