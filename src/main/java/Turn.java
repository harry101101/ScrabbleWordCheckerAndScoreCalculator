import java.util.ArrayList;

public class Turn {

    //Properties
    private ArrayList<String> words;
    private int score;
    private boolean validTurn;
    private boolean allLettersOut;

    //Constructor
    public Turn(){
        this.words = new ArrayList<>();
        this.score = 0;
        this.validTurn = true;
        this.allLettersOut = false;
    }

    //Methods
    public void addWord(String word){this.words.add(word);}

//    public int calculateScore(Turn turn){
//        int wordMultiplier = 1;
//
//        for (int i = 0; i < words.size(); i++){
//            String word = words.get(i);
//
//            if (word.charAt(0) == '2'){wordMultiplier = 2;}
//            if (word.charAt(0) == '3'){wordMultiplier = 3;}
//
//            for (int j = 0; j < word.length(); j++){
//                int tileMultiplier = 1;
//                char tile = word.charAt(j);
//                if( !((tile == 2 || tile ==3) && j == 0 )){
//                    if(word.charAt(j+1) == '2'){tileMultiplier = 2;}
//                    if(word.charAt(j+1) == '3'){tileMultiplier = 3;}
//
//                    for()
//                }
//            }
//
//        }
//        return score;
//    }

    //Getters and Setters
    public ArrayList<String> getWords() {
        return words;
    }

    public void setTurn(ArrayList<String> words) {
        this.words = words;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isValidTurn() {
        return validTurn;
    }

    public void setValidTurn(boolean validTurn) {
        this.validTurn = validTurn;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public boolean isAllLettersOut() {
        return allLettersOut;
    }

    public void setAllLettersOut(boolean allLettersOut) {
        this.allLettersOut = allLettersOut;
    }
}
