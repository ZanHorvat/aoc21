package io.github.zanhorvat.aoc21.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Puzzle {

    public static void main(String[] args) throws IOException {
        System.out.println("What is the power consumption of the submarine?");

        System.out.println(findAndMultiplyGammaAndEpsilonRate("day03/src/main/resources/input01.txt"));
        System.out.println(
                "\nWhat is the life support rating of the submarine?"
        );
        System.out.println(calcOxyCO2Rating("day03/src/main/resources/input01.txt"));
    }

    private static class O2Filter implements Predicate<String> {
        private int pos, ones, zeros;

        public O2Filter(int n, List<String> nums) {
            pos = n;
            ones = zeros = 0;
            for (String num : nums) {
                if (num.charAt(pos) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
        }

        public boolean test(String num) {
            if (ones >= zeros) {
                return num.charAt(pos) == '0';
            } else {
                return num.charAt(pos) == '1';
            }
        }
    }

    public static long findAndMultiplyGammaAndEpsilonRate(String filePath) throws FileNotFoundException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        int[] countArray = null;
        int n = 0;

        while (myReader.hasNextLine()) {
            n++;
            char[] command = myReader.nextLine().toCharArray();

            if(countArray == null){
                countArray = new int[command.length];
            }

            for (int i = 0; i < command.length; i++) {
                if(command[i] == '1'){
                    countArray[i]++;
                }
            }
        }

        myReader.close();

        return calcGammaRate(countArray, n) * calcEpsilonRate(countArray, n);
    }

    static long calcOxyCO2Rating(String filePath) throws IOException {

        List<String> input = Files.lines(Path.of(filePath)).collect(Collectors.toList());
        int nbytes = input.get(0).length();
        int[] ones = new int[nbytes];

        List<String> oxygen = input;
        List<String> co2 = input;
        for (int n = 0; n < ones.length; n++) {
            if (oxygen.size() > 1) {
                oxygen = oxygen.stream().filter(new O2Filter(n, oxygen)).collect(Collectors.toList());
            }
            if (co2.size() > 1) {
                co2 = co2.stream()
                        .filter(Predicate.not(new O2Filter(n, co2)))
                        .collect(Collectors.toList());
            }
            if (oxygen.size() == 1 && co2.size() == 1) {
                break;
            }
        }

        int o2r = Integer.valueOf(oxygen.get(0), 2);
        int co2r = Integer.valueOf(co2.get(0), 2);
        return o2r * co2r;
    }

    static long calcGammaRate(int[] countArray, int n) {

        long x = 0;

        for (int i = 0; i < countArray.length; i++) {
            // n of Zeros < n of Ones
            if(n - countArray[i] < countArray[i]){
                x += Math.pow(2, countArray.length-i-1);
            }
        }

        return x;
    }

    static long calcEpsilonRate(int[] countArray, int n) {

        long x = 0;

        for (int i = 0; i < countArray.length; i++) {
            // n of Zeros < n of Ones
            if(n - countArray[i] > countArray[i]){
                x += Math.pow(2, countArray.length-i-1);
            }
        }

        return x;
    }
}
