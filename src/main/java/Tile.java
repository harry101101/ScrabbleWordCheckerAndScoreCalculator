public class Tile {

    //Properties
    private char letter;
    private int value;

    //Constructor
    public Tile(char letter, int value){
        this.letter = letter;
        this.value = value;
    }


    //Getters and Setters
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
