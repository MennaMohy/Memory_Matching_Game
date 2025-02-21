public class Player {
    private String name;
    private int score;
    private boolean helpUsed;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.helpUsed = false;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public void setHelpUsed(boolean helpUsed) {
        this.helpUsed = helpUsed;
    }

    public boolean isHelpUsed(){
        return helpUsed;
    }
}
