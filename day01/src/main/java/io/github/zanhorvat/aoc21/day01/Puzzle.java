package io.github.zanhorvat.aoc21.day01;

import java.io.*;
import java.util.*;

public class Puzzle {

    public static int countSingleIncreases(String filePath) throws FileNotFoundException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        int increased = 0;
        int previous = -1;

        while (myReader.hasNextLine()) {
            int current = Integer.parseInt(myReader.nextLine());
            if(previous != -1 && current > previous){
                increased++;
            }
            previous = current;
        }

        myReader.close();

        return increased;
    }

    public static int countGroupIncreases(String filePath) throws FileNotFoundException {

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        int increased = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (myReader.hasNextLine()) {
            int value = Integer.parseInt(myReader.nextLine());
            list.add(value);
            if(list.size() == 4){
                if( list.subList(0, 3).stream().reduce(0, Integer::sum) <
                    list.subList(1, 4).stream().reduce(0, Integer::sum)){
                    increased++;
                }
                list.remove(0);
            }
        }

        myReader.close();

        return increased;
    }
}
