package io.zanhorvat.aoc21.day06;

import java.io.*;
import java.util.*;


public class Puzzle {
    static long solve(String filePath, int nSimulations) throws FileNotFoundException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        long[] list = new long[9];

        if(myReader.hasNextLine()){
            for(String elt : myReader.nextLine().split(",")){
                list[Integer.parseInt(elt)]++;
            }
        }

        for(int i = 0; i < nSimulations; i++){
            long nNewFish = list[0];
            System.arraycopy(list, 1, list, 0, 8);
            list[6] += nNewFish;
            list[8] = nNewFish;
        }
        return Arrays.stream(list).sum();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("How many lanternfish would there be after 80 days?");
        System.out.println(solve("day06/src/main/resources/input01.txt", 80));
        System.out.println("How many lanternfish would there be after 256 days?");
        System.out.println(solve("day06/src/main/resources/input01.txt", 256));
    }
}
