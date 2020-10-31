package battleship;

import java.io.IOException;

public class GameField {
    static String[] letters = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
    public String[][] field = new String[10][10];
    public String[][] fieldInFog = new String[10][10];

    Coordinates coordinates = new Coordinates();

    public String[][] forbidenCoordinates = new String[12][12];
    public String[][] sunkenShips = new String[12][12];

    int shipCount = 17;

    public void play() throws IOException {
        System.out.println();
        System.out.println("Take a shot!");
        System.out.println();
        while (shipCount > 0) {
            runGame();
        }
        System.out.println();
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public boolean checkSunk(int x, int y) {
        boolean isSunk = true;

        if (sunkenShips[x - 1][y].equals("O ") || sunkenShips[x][y - 1].equals("O ") ||
                sunkenShips[x][y + 1].equals("O ") || sunkenShips[x + 1][y].equals("O ")) {
            isSunk = false;
        }
        return isSunk;
    }

    public void runGame() throws IOException {
        boolean isRight = false;
        String[] coordinates = new String[2];
        String letter = "";
        int number = 0;

        while (!isRight) {
            coordinates = Main.reader.readLine().split("");
            letter = coordinates[0];
            if (coordinates.length == 2) {
                number = Integer.parseInt(coordinates[1]);
            } else if (coordinates.length == 3) {
                number = Integer.parseInt(coordinates[1] + coordinates[2]);
            }

            if (letter.matches("[A-J]") && number > 0 && number < 11) {
                isRight = true;
            } else {
                System.out.println();
                System.out.println("Error! You entered the wrong coordinates! Try again: ");
                System.out.println();
            }
        }
        if (field[Coordinates.letterColum.get(letter)][number - 1].equals("O ")) {
            boolean isSunk = false;
            fieldInFog[Coordinates.letterColum.get(letter)][number - 1] = "X ";
            field[Coordinates.letterColum.get(letter)][number - 1] = "X ";
            sunkenShips[Coordinates.letterColum.get(letter) + 1][number - 1 + 1] = "X ";
            int x = Coordinates.letterColum.get(letter) + 1;
            int y = number - 1 + 1;
            System.out.println();
            displayFieldOfWar();
            isSunk = checkSunk(x, y);
            shipCount -= 1;
            if (!isSunk) {
                System.out.println();
                System.out.println("You hit a ship! Try again:");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("You sank a ship! Specify a new target: ");
                System.out.println();
            }
        } else if (field[Coordinates.letterColum.get(letter)][number - 1].equals("~ ")) {
            fieldInFog[Coordinates.letterColum.get(letter)][number - 1] = "M ";
            field[Coordinates.letterColum.get(letter)][number - 1] = "M ";
            System.out.println();
            displayFieldOfWar();
            System.out.println();
            System.out.println("You missed! Try again: ");
            System.out.println();
        } else if (field[Coordinates.letterColum.get(letter)][number - 1].equals("X ")) {
            System.out.println();
            displayFieldOfWar();
            System.out.println();
            System.out.println("Error! You hit a ship! Try again: ");
            System.out.println();
        }
    }

    public void fillForbiden() {
        for (int i = 0; i < forbidenCoordinates.length; i++) {
            for (int j = 0; j < forbidenCoordinates.length; j++) {
                forbidenCoordinates[i][j] = "~";
                sunkenShips[i][j] = "~";
            }
        }
    }

    public void createOriginalField() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = "~ ";
                fieldInFog[i][j] = "~ ";
            }
        }
    }

    public void displayFieldOfWar() {
        System.out.println();
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(letters[i]);
            for (int j = 0; j < 10; j++) {
                System.out.print(fieldInFog[i][j]);
            }
            System.out.println();
        }
    }

    public void displayField() {
        System.out.println();
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(letters[i]);
            for (int j = 0; j < 10; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }


    public void inputSetcoordinates(Coordinates coordinates) throws IOException {
        String[] airacraftCarierTwoCoordinates = Main.reader.readLine().split(" ");
        String[] firstCoordinate = airacraftCarierTwoCoordinates[0].split("");
        if (firstCoordinate.length == 3 && firstCoordinate[1].equals("1")) {
            firstCoordinate[1] = "10";
        }
        String[] secondCoordinate = airacraftCarierTwoCoordinates[1].split("");
        if (secondCoordinate.length == 3 && secondCoordinate[1].equals("1")) {
            secondCoordinate[1] = "10";
        }
        coordinates.setFirstCoordinateLetter(firstCoordinate[0]);
        coordinates.setFirstCoordinateNumber(firstCoordinate[1]);
        coordinates.setSecondCoordinateLetter(secondCoordinate[0]);
        coordinates.setSecondCoordinateNumber(secondCoordinate[1]);
    }

    public void setCarrier() throws IOException {
        boolean isCorrect = false;

        System.out.println();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");

        while (!isCorrect) {
            inputSetcoordinates(coordinates);
            isCorrect = coordinates.checkLength("carrier");
        }
    }

    public void setBattleship() throws IOException {
        boolean isCorrect = false;

        System.out.println();
        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");

        while (!isCorrect) {
            inputSetcoordinates(coordinates);
            isCorrect = coordinates.checkLength("battleship") && checkProximity();

        }
    }

    public void setSubmarine() throws IOException {
        boolean isCorrect = false;

        System.out.println();
        System.out.println("Enter the coordinates of the Submarine  (3 cells):\n");

        while (!isCorrect) {
            inputSetcoordinates(coordinates);
            isCorrect = coordinates.checkLength("submarine") && checkProximity();
        }
    }

    public void setCruiser() throws IOException {
        boolean isCorrect = false;

        System.out.println();
        System.out.println("Enter the coordinates of the Cruiser  (3 cells):\n");

        while (!isCorrect) {
            inputSetcoordinates(coordinates);
            isCorrect = coordinates.checkLength("cruiser") && checkProximity();
        }
    }

    public void setDestroyer() throws IOException {
        boolean isCorrect = false;

        System.out.println();
        System.out.println("Enter the coordinates of the Destroyer (2 cells):\n");

        while (!isCorrect) {
            inputSetcoordinates(coordinates);
            isCorrect = coordinates.checkLength("destroyer") && checkProximity();
        }
    }

    public void setTheField() {

        String firstCoordinateLetter = coordinates.getFirstCoordinateLetter();
        String firstCoordinateNumber = coordinates.getFirstCoordinateNumber();

        String secondCoordinateLetter = coordinates.getSecondCoordinateLetter();
        String secondCoordinateNumber = coordinates.getSecondCoordinateNumber();

        int firstCoordinateFirst = Coordinates.letterColum.get(firstCoordinateLetter);
        int firstCoordinateSecond = Integer.parseInt(firstCoordinateNumber) - 1;
        int secondCoordinateFirst = Coordinates.letterColum.get(secondCoordinateLetter);
        int secondCoordinateSecond = Integer.parseInt(secondCoordinateNumber) - 1;
        int holder = 0;

        if (secondCoordinateFirst < firstCoordinateFirst) {
            holder = secondCoordinateFirst;
            secondCoordinateFirst = firstCoordinateFirst;
            firstCoordinateFirst = holder;
        }

        if (secondCoordinateSecond < firstCoordinateSecond) {
            holder = secondCoordinateSecond;
            secondCoordinateSecond = firstCoordinateSecond;
            firstCoordinateSecond = holder;
        }

        if (firstCoordinateLetter.equals(secondCoordinateLetter)) {
            for (int i = firstCoordinateSecond; i <= secondCoordinateSecond; i++) {
                int y = i + 1;
                int x = firstCoordinateFirst + 1;
                field[firstCoordinateFirst][i] = "O ";
                sunkenShips[x][y] = "O ";
                forbidenCoordinates[x][y] = "F";
                forbidenCoordinates[x - 1][y - 1] = "F";
                forbidenCoordinates[x - 1][y] = "F";
                forbidenCoordinates[x - 1][y + 1] = "F";
                forbidenCoordinates[x][y - 1] = "F";
                forbidenCoordinates[x][y + 1] = "F";
                forbidenCoordinates[x + 1][y - 1] = "F";
                forbidenCoordinates[x + 1][y] = "F";
                forbidenCoordinates[x + 1][y + 1] = "F";
            }
        } else {
            for (int i = firstCoordinateFirst; i <= secondCoordinateFirst; i++) {
                int x = i + 1;
                int y = firstCoordinateSecond + 1;
                field[i][firstCoordinateSecond] = "O ";
                sunkenShips[x][y] = "O ";
                forbidenCoordinates[i][firstCoordinateSecond] = "F";
                forbidenCoordinates[x - 1][y - 1] = "F";
                forbidenCoordinates[x - 1][y] = "F";
                forbidenCoordinates[x - 1][y + 1] = "F";
                forbidenCoordinates[x][y - 1] = "F";
                forbidenCoordinates[x][y + 1] = "F";
                forbidenCoordinates[x + 1][y - 1] = "F";
                forbidenCoordinates[x + 1][y] = "F";
                forbidenCoordinates[x + 1][y + 1] = "F";
            }
        }
    }

    public boolean checkProximity() {

        boolean isFar = true;

        String firstCoordinateLetter = coordinates.getFirstCoordinateLetter();
        String firstCoordinateNumber = coordinates.getFirstCoordinateNumber();

        String secondCoordinateLetter = coordinates.getSecondCoordinateLetter();
        String secondCoordinateNumber = coordinates.getSecondCoordinateNumber();

        int firstCoordinateFirst = Coordinates.letterColum.get(firstCoordinateLetter);
        int firstCoordinateSecond = Integer.parseInt(firstCoordinateNumber) - 1;
        int secondCoordinateFirst = Coordinates.letterColum.get(secondCoordinateLetter);
        int secondCoordinateSecond = Integer.parseInt(secondCoordinateNumber) - 1;
        int holder = 0;

        if (secondCoordinateFirst < firstCoordinateFirst) {
            holder = secondCoordinateFirst;
            secondCoordinateFirst = firstCoordinateFirst;
            firstCoordinateFirst = holder;
        }

        if (secondCoordinateSecond < firstCoordinateSecond) {
            holder = secondCoordinateSecond;
            secondCoordinateSecond = firstCoordinateSecond;
            firstCoordinateSecond = holder;
        }
        if (forbidenCoordinates[firstCoordinateFirst + 1][firstCoordinateSecond + 1].equals("F") ||
                forbidenCoordinates[secondCoordinateFirst + 1][secondCoordinateSecond + 1].equals("F")) {
            isFar = false;
            System.out.println();
            System.out.println("Error! You placed it too close to another one. Try again: ");
            System.out.println();
        }
        return isFar;
    }
}

