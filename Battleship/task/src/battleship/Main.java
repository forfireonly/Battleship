package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        GameField newGame = new GameField();
        newGame.fillForbiden();
        newGame.createOriginalField();
        newGame.displayField();
        newGame.setCarrier();
        newGame.setTheField();
        newGame.displayField();
        newGame.setBattleship();
        newGame.setTheField();
        newGame.displayField();
        newGame.setSubmarine();
        newGame.setTheField();
        newGame.displayField();
        newGame.setCruiser();
        newGame.setTheField();
        newGame.displayField();
        newGame.setDestroyer();
        newGame.setTheField();
        newGame.displayField();

        System.out.println("The game starts!\n");

        newGame.displayFieldOfWar();
        newGame.runGame();



    }
}
