package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean isOne = true;
        boolean isFinished = false;
        String enter = "";

        System.out.println();
        System.out.println("Player 1, place your ships on the game field");
        GameField playerOne = new GameField();
        playerOne.setShips();

        System.out.println();
        System.out.println("Press Enter and pass the move to another player");
        enter = reader.readLine();
        if (enter.equals("")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        System.out.println("Player 2, place your ships on the game field");
        GameField playerTwo = new GameField();
        playerTwo.setShips();

        System.out.println();
        System.out.println("The game starts!");
        System.out.println("Press Enter and pass the move to another player");

        enter = reader.readLine();

        if (enter.equals("")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        while (!isFinished) {
            if (isOne) {
                playerTwo.displayFieldOfWar();
                System.out.println("---------------------");
                playerOne.displayField();
                System.out.println();
                System.out.println("Player 1, it's your turn:");
                System.out.println();
                isOne = false;
                isFinished = playerTwo.play();
                enter = reader.readLine();

                if (enter.equals("")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } else {
                playerOne.displayFieldOfWar();
                System.out.println("---------------------");
                playerTwo.displayField();
                System.out.println();
                System.out.println("Player 2, it's your turn:");
                System.out.println();
                isOne = true;
                isFinished = playerOne.play();
                enter = reader.readLine();

                if (enter.equals("")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            }
        }


    }
}

