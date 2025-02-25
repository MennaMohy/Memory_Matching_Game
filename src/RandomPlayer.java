import java.util.Random;

// Random player is a player
public class RandomPlayer extends Player {
    private Random random;

    public RandomPlayer(String name) {
        super(name);
        this.random = new Random();
    }

    public int getMove(int size){
       //Generate move randomly
        return random.nextInt(size);
    }

}
