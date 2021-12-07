package io.zanhorvat.aoc21.day07;

import java.io.*;
import java.util.*;

public class Puzzle {
    static int solve(String filePath, boolean isConstantUsage) throws FileNotFoundException {
        String[] list = new Scanner(new File(filePath)).nextLine().split(",");

        int[] numList = new int[list.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.length; i++){
            numList[i] = Integer.parseInt(list[i]);
            if(numList[i] > max) max = numList[i];
        }

        int minDiff = Integer.MAX_VALUE;

        for (int j = 0; j < max; j++) {
            int diff = 0;
            for (int value : numList){
                if(isConstantUsage){
                    diff += Math.abs(j - value);
                } else {
                    for (int k = 1; k <= Math.abs(j - value); k++) diff += k;
                }
            }
            if (minDiff > diff) minDiff = diff;
        }
        return minDiff;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part One:" + solve("day07/src/main/resources/input01.txt", true));
        System.out.println("Part Two:" + solve("day07/src/main/resources/input01.txt", false));
    }
}
