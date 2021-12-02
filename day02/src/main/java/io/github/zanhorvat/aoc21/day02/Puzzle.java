package io.github.zanhorvat.aoc21.day02;

import io.github.zanhorvat.aoc21.day02.model.SubmarinePosition;

import java.io.*;
import java.util.*;

public class Puzzle {
    public static long calculateSubmarinePosition(String filePath, boolean includesAim) throws FileNotFoundException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        SubmarinePosition submarinePosition = new SubmarinePosition();

        while (myReader.hasNextLine()) {

            String[] command = myReader.nextLine().split(" ");
            String direction = command[0];
            int value = Integer.parseInt(command[1]);

            if(includesAim){
                submarinePosition.changeWithAim(direction, value);
            } else {
                submarinePosition.changeSimple(direction, value);
            }
        }

        myReader.close();

        return submarinePosition.getDepth() * submarinePosition.getX();
    }
}
