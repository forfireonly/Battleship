package battleship;

import java.util.HashMap;

public class Coordinates {
    private String firstCoordinateLetter;
    private String firstCoordinateNumber;
    private String secondCoordinateLetter;
    private String secondCoordinateNumber;

    public static HashMap<String, Integer> letterColum = createMap();
    private static HashMap<String, Integer> createMap() {

        HashMap<String,Integer> letterColum = new HashMap<>();

        letterColum.put("A",0);
        letterColum.put("B",1);
        letterColum.put("C",2);
        letterColum.put("D",3);
        letterColum.put("E",4);
        letterColum.put("F",5);
        letterColum.put("G",6);
        letterColum.put("H",7);
        letterColum.put("I",8);
        letterColum.put("J",9);

        return letterColum;
    }

    public void setFirstCoordinateLetter(String firstCoordinateLetter) {
        this.firstCoordinateLetter = firstCoordinateLetter;
    }

    public void setFirstCoordinateNumber(String firstCoordinateNumber) {
        this.firstCoordinateNumber = firstCoordinateNumber;
    }

    public  void setSecondCoordinateLetter(String secondCoordinateLetter) {
        this.secondCoordinateLetter = secondCoordinateLetter;
    }

    public void setSecondCoordinateNumber(String secondCoordinateNumber) {
        this.secondCoordinateNumber = secondCoordinateNumber;
    }

    public String getFirstCoordinateLetter() {
        return firstCoordinateLetter;
    }

    public String getFirstCoordinateNumber() {
        return firstCoordinateNumber;
    }

    public String getSecondCoordinateLetter() {
        return secondCoordinateLetter;
    }

    public String getSecondCoordinateNumber() {
        return secondCoordinateNumber;
    }

    public boolean checkLength(String shipType) {

        boolean isCorrectLength = false;
        int firstCoordinate = Integer.parseInt(firstCoordinateNumber);
        int secondCoordinate = Integer.parseInt(secondCoordinateNumber);
        //System.out.println(firstCoordinate);
        //System.out.println(secondCoordinate);
        int lengthOfShip = 0;
        if (firstCoordinate !=secondCoordinate && !firstCoordinateLetter.equals(secondCoordinateLetter)) {
            System.out.println();
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
        } else {
            if (firstCoordinateLetter.equals(secondCoordinateLetter)) {
                lengthOfShip = Math.abs(secondCoordinate - firstCoordinate) + 1;
            } else {
               lengthOfShip = Math.abs(letterColum.get(firstCoordinateLetter) - letterColum.get(secondCoordinateLetter)) + 1;

            }
            //System.out.println(lengthOfShip);
            switch (shipType) {
                case "carrier":
                    if (lengthOfShip == 5) {
                        isCorrectLength = true;
                    } else {
                        System.out.println();
                        System.out.println("Error! Wrong length of the Carrier! Try again:\n");
                    }
                    break;
                case "battleship":
                    if (lengthOfShip == 4) {
                        isCorrectLength = true;
                    } else {
                        System.out.println();
                        System.out.println("Error! Wrong length of the Battleship! Try again:\n");
                    }
                    break;
                case "submarine":
                    if (lengthOfShip == 3) {
                        isCorrectLength = true;
                    } else {
                        System.out.println();
                        System.out.println("Error! Wrong length of the Submarine! Try again:\n");
                    }
                    break;
                case "cruiser":
                    if (lengthOfShip == 3) {
                        isCorrectLength = true;
                    } else {
                        System.out.println();
                        System.out.println("Error! Wrong length of the Cruiser! Try again:\n");
                    }
                    break;
                case "destroyer":
                    if (lengthOfShip == 2) {
                        isCorrectLength = true;
                    } else {
                        System.out.println();
                        System.out.println("Error! Wrong length of the Destroyer! Try again:\n");
                    }
                    break;
                default:

            }
        }
    return isCorrectLength;
    }
}
