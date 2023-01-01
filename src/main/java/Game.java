import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    //Properties
    private ArrayList<Tile> tiles;
    private ArrayList<Player> players;
    private boolean gameComplete;

    //Constructor
    public Game (){
        this.tiles = new ArrayList<>();
        this.players = new ArrayList<>();
        this.gameComplete = false;
    }

    //Methods
    public void addTile(Tile tile){this.tiles.add(tile);}

    public void addPlayer(Player player){this.players.add(player);}

    public Player getPlayerAtIndex(int index){
        return this.players.get(index);
    }

    public int calculateScore(Turn turn) {

        int score = 0;
        int wordMultiplier = 1;

        for (int i = 0; i < turn.getWords().size(); i++) {
            String word = turn.getWords().get(i);

            if (word.charAt(0) == '2') {
                wordMultiplier = 2;
            }
            if (word.charAt(0) == '3') {
                wordMultiplier = 3;
            }

            for (int j = 0; j < word.length(); j++) {
                int tileMultiplier = 1;
                char tile = word.toLowerCase().charAt(j);
                if (!((tile == 2 || tile == 3) && j == 0)) {
                    if(j + 1 < word.length()) {
                        if (word.charAt(j + 1) == '2') {
                            tileMultiplier = 2;
                        }
                        if (word.charAt(j + 1) == '3') {
                            tileMultiplier = 3;
                        }
                    }


                    for (int k = 0; k < tiles.size(); k++) {
                        char tileCheck = tiles.get(k).getLetter();
                        if (tile == tileCheck){
                            int tileScore = tiles.get(k).getValue();
                            score += (tileScore * tileMultiplier * wordMultiplier);

                        }
                    }
                }
            }
            wordMultiplier = 1;

        }
        return score;
    }
    public int calculateRemainingTilesValue(String string){
        int sum = 0;
        for (int i = 0; i < string.length(); i++){
            char letter = string.charAt(i);
            for(int j = 0; j < tiles.size(); j++){
                if (letter == tiles.get(j).getLetter()){
                    sum += tiles.get(j).getValue();
                }
            }
        }
        return sum;
    }

    //Word Check
    public boolean wordCheck(String word) throws FileNotFoundException {
        Scanner txtscan = new Scanner(new File("/Users/harry/Documents/not_BNTA/scrabble_v1/src/main/Collins Scrabble Words (2019).txt"));

        while(txtscan.hasNextLine()){
            String str = txtscan.nextLine();
            if (str.equals(word)){return true;}
        }
        return false;
    }

    //Convert input into dictionary checkable word
    public String convertWord(String word){
        String adaptedWord = "";
        for (int i = 0; i < word.length(); i++){
            Character character = word.toUpperCase().charAt(i);
            if (character == '?'){
                System.out.println("Which letter do you want the blank to be?");
                Scanner scanBlankLetter = new Scanner(System.in);
                char letter = scanBlankLetter.nextLine().charAt(0);
                adaptedWord += letter;
            }
            if (character.isAlphabetic(character)){
                adaptedWord += character;
            }
        }
        return adaptedWord;
    }

    //Getters and Setters

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        this.gameComplete = gameComplete;
    }
}
