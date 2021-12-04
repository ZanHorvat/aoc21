package io.github.zanhorvat.aoc21.day04;

import java.io.*;
import java.util.*;

public class Puzzle {

    public static final int N = 5;

    public static long predictBingo(String filePath, boolean isFirst) throws FileNotFoundException {

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        List<Integer> drawnNumbers = readDrawnNumbers(myReader);
        List<Integer> bingoNumbers = readBingoTickets(myReader);
        myReader.close();

        Set<Integer> checkedNumbers = new HashSet<>();
        int[] wonTickets = new int[bingoNumbers.size()/(N * N)];


        for (int num : drawnNumbers){ // Mark
            checkedNumbers.add(num);
            for(int j = 0; j < wonTickets.length && checkedNumbers.size() >= N; j++){ // Check
                int startOfWindow = N * N * j;

                if(     hasCheckedRow(bingoNumbers, checkedNumbers, startOfWindow) ||
                        hasCheckedCol(bingoNumbers, checkedNumbers, startOfWindow)
                ){
                    wonTickets[j] = 1;
                    int sumUnchecked = bingoNumbers
                            .subList(startOfWindow, startOfWindow + N*N).stream()
                            .reduce(0, (t, elt) -> t + (!checkedNumbers.contains(elt) ? elt : 0));

                    if(isFirst) return num * sumUnchecked;
                    if(Arrays.stream(wonTickets).sum() == wonTickets.length) return num * sumUnchecked;
                }
            }
        }
        return -1;
    }

    private static boolean hasCheckedCol(List<Integer> bingoNumbers, Set<Integer> checkedNumbers, int startOfWindow) {
        boolean hasCheckedColumn = false;

        for(int pos = 0; pos < N && !hasCheckedColumn; pos++){
            boolean areAllCheckedColumn = true;
            for(int startOfRow = 0; startOfRow < N * N; startOfRow += N)
                areAllCheckedColumn = areAllCheckedColumn && checkedNumbers.contains(bingoNumbers.get(startOfWindow + startOfRow + pos));
            hasCheckedColumn = hasCheckedColumn || areAllCheckedColumn;
        }
        return hasCheckedColumn;
    }

    private static boolean hasCheckedRow(List<Integer> bingoNumbers, Set<Integer> checkedNumbers, int startOfWindow) {
        boolean hasCheckedRow = false;
        for(int k = 0; k < N && !hasCheckedRow; k++){
            int startOfRow = startOfWindow + k * N;
            boolean areAllCheckedRow = checkedNumbers.containsAll(bingoNumbers.subList(startOfRow, startOfRow + 5));
            hasCheckedRow = hasCheckedRow || areAllCheckedRow;
        }
        return hasCheckedRow;
    }

    private static List<Integer> readBingoTickets(Scanner myReader) {
        List<Integer>  bingoTickets = new ArrayList<>();
        while(myReader.hasNextInt()){
            bingoTickets.add(myReader.nextInt());
        }
        return bingoTickets;
    }

    private static ArrayList<Integer> readDrawnNumbers(Scanner myReader) {
        ArrayList<Integer> drawnNumbers = new ArrayList<>();
        if (myReader.hasNextLine()) {
            for(String number : myReader.nextLine().split(",")){
                drawnNumbers.add(Integer.parseInt(number));
            }
        }
        return drawnNumbers;
    }

    public static void main(String[] args) {
        try {
            System.out.println("To guarantee victory against the giant squid, figure out which board will win first.\n"
                    + "What will your final score be if you choose that board?");

            System.out.println(predictBingo("day04/src/main/resources/input01.txt", false));
            System.out.println(
                    "\nFigure out which board will win last. Once it wins, what would its final score be?"
            );
            System.out.println(predictBingo("day04/src/main/resources/input01.txt", true));


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
