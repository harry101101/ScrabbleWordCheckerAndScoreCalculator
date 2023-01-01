import java.util.ArrayList;

public class Player {

    //Properties
    private String name;
    private ArrayList<Turn> turns;
    private int score;

    //Constructor
    public Player(String name){
        this.name = name;
        this.turns = new ArrayList<>();
        this.score = 0;
    }

    //Methods
    public void addTurn(Turn turn){this.turns.add(turn);}



    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public void setTurns(ArrayList<Turn> turns) {
        this.turns = turns;
    }
}
