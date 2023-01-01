import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {


        Game game = new Game();

        //Add all tiles
        game.addTile(new Tile('a',1));
        game.addTile(new Tile('b',3));
        game.addTile(new Tile('c',3));
        game.addTile(new Tile('d',2));
        game.addTile(new Tile('e',1));
        game.addTile(new Tile('f',4));
        game.addTile(new Tile('g',2));
        game.addTile(new Tile('h',4));
        game.addTile(new Tile('i',1));
        game.addTile(new Tile('j',8));
        game.addTile(new Tile('k',5));
        game.addTile(new Tile('l',1));
        game.addTile(new Tile('m',3));
        game.addTile(new Tile('n',1));
        game.addTile(new Tile('o',1));
        game.addTile(new Tile('p',3));
        game.addTile(new Tile('q',10));
        game.addTile(new Tile('r',1));
        game.addTile(new Tile('s',1));
        game.addTile(new Tile('t',1));
        game.addTile(new Tile('u',1));
        game.addTile(new Tile('v',4));
        game.addTile(new Tile('w',4));
        game.addTile(new Tile('x',8));
        game.addTile(new Tile('y',4));
        game.addTile(new Tile('z',10));
        game.addTile(new Tile('?',0));

        //Intro
        System.out.println("------------------------------");
        System.out.println("SCRABBLE WORD CHECKER & SCORER");
        System.out.println("------------------------------");

        //Get the number of players
        Scanner scanNoOfPlayers = new Scanner(System.in);
        System.out.println("Enter the number of players: ");
        int numberOfPlayers = scanNoOfPlayers.nextInt();

        //Get player names
        for (int i = 0; i < numberOfPlayers; i++){
            Scanner scanPlayerName = new Scanner(System.in);
            System.out.println("Enter player " + (i+1) + "'s name: ");
            String name = scanPlayerName.nextLine();
            Player player = new Player(name);
            game.addPlayer(player);
        }

        //Explain how to use this app
        System.out.println("--------------------");
        System.out.println("How to use this app:");
        System.out.println("--------------------");
        System.out.println("1. Each time it is a players go they will be prompted for how many new words they have created on that turn.");
        System.out.println("2. You will then be asked to input these words 1 by 1");
        System.out.println("3. When entering the words if you have a blank put a '?' in place of the letter.");
        System.out.println("You will then be prompted for which letter you wish the blank to be.");
        System.out.println("4. If the word is on a double or triple word square, enter a 2 or 3 respectively, before the word.");
        System.out.println("5. If a letter is on a double or triple letter square, enter a 2 or 3 respectively, after that letter.");
        System.out.println("ie. 2scrabbl3e would be inputted if the word was on a double word tile and the 'l' was on a triple letter tile.");
        System.out.println("6. The words will then be checked and if valid a score calculated.");
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        //Play game
        int turnCounter = 0;
        while(!game.isGameComplete()){

            //Prompt for user number of words created on that go
            Player player = game.getPlayerAtIndex(turnCounter % numberOfPlayers);
            System.out.println(player.getName() + "'s turn:");
            Scanner scanNumberOfWords = new Scanner(System.in);
            System.out.println("How many new words created?");
            int numberOfWords = scanNumberOfWords.nextInt();

            //Add words to turn array for current player
            Turn turn = new Turn();
            int longestWordLength = 0;
            for (int i = 0; i < numberOfWords; i++){
                System.out.println("Enter word " +(i+1) + ":");
                Scanner scanWord = new Scanner(System.in);
                String word = scanWord.nextLine();

                String wordForDictionary = game.convertWord(word);
                if(longestWordLength < wordForDictionary.length()){
                    longestWordLength = wordForDictionary.length();
                }
                turn.addWord(word);
                //If invalid word is played
                if (!game.wordCheck(wordForDictionary.toUpperCase())){
                    turn.setValidTurn(false);
                    System.out.println(wordForDictionary + " is not a valid word.");
                    i = numberOfWords;
                }

            }
                player.addTurn(turn);

                //Calculate turns score change overall score
                int turnScore = game.calculateScore(turn);
                if(longestWordLength >= 7 && turn.isValidTurn()){
                    System.out.println("Did you get all your letters out? Enter 'yes' if so. Otherwise any other key to continue");
                    Scanner scanIfAllLettersOut = new Scanner(System.in);
                    String allLetterOutCheck = scanIfAllLettersOut.nextLine().toLowerCase();
                    if (allLetterOutCheck.equals("yes")){
                        turn.setAllLettersOut(true);
                    }
                }
                if(!turn.isValidTurn()) {
                    turnScore = 0;
                }
                if(turn.isAllLettersOut()){
                    turnScore += 50;
                }
                int totalScore = turnScore + player.getScore();

                player.setScore((totalScore));
                System.out.println(player.getName() + "'s score: " + turnScore);
                System.out.println("------------------");

            //Print overall scores
            System.out.println("Current scores:");
            System.out.println("------------------");
            for(int i = 0; i < game.getPlayers().size(); i++){
                System.out.println(game.getPlayerAtIndex(i).getName() + ": " + game.getPlayerAtIndex(i).getScore());
            }
            System.out.println("------------------");




            //Check if game complete
            Scanner scanGameComplete = new Scanner(System.in);
            System.out.println("Enter 'yes' if game is complete. Enter any other key to continue.");
            String gameCompleteInput = scanGameComplete.nextLine();
            if(gameCompleteInput.toLowerCase().equals("yes")){
                game.setGameComplete(true);
            } else {
                turnCounter ++;
            }

        }
        //If game complete adapt scores
        String playerOutFirst = game.getPlayerAtIndex(turnCounter % game.getPlayers().size()).getName();
        System.out.println(playerOutFirst + " got all their tiles out first. We will now adapt the scores.");
        int pointsAddedToPlayerOutFirst = 0;
        for (int i = 0; i < game.getPlayers().size(); i++){
            if (!game.getPlayerAtIndex(i).getName().equals(playerOutFirst)){
                System.out.println(game.getPlayerAtIndex(i).getName() +" enter your remaining tiles. ie 'rfehek': ");
                Scanner scanForRemainingTiles = new Scanner(System.in);
                String remainingTiles = scanForRemainingTiles.nextLine();
                int tileSum = game.calculateRemainingTilesValue(remainingTiles);
                int adaptedScore = game.getPlayerAtIndex(i).getScore() - tileSum;
                game.getPlayerAtIndex(i).setScore(adaptedScore);
                pointsAddedToPlayerOutFirst += tileSum;
            }
        }
        int playerOutAdaptedScore = game.getPlayerAtIndex(turnCounter % game.getPlayers().size()).getScore() + pointsAddedToPlayerOutFirst;
        game.getPlayerAtIndex(turnCounter % game.getPlayers().size()).setScore(playerOutAdaptedScore);


        System.out.println("Final Scores:");
        System.out.println("-------------");
        for (int i = 0; i < game.getPlayers().size(); i++){
            System.out.println(game.getPlayerAtIndex(i).getName() + ": " + game.getPlayerAtIndex(i).getScore());
        }

    }
}