package io.github.zanhorvat.day05;

import java.io.*;
import java.util.*;

public class Puzzle {

    public static int solve(String filePath, boolean includeDiagonals) throws FileNotFoundException {

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        HashMap<String, Integer> points = new HashMap<>();

        while(myReader.hasNextLine()){

            String[] line = myReader.nextLine().split(" -> ");

            int x0 = Integer.parseInt(line[0].split(",")[0]);
            int y0 = Integer.parseInt(line[0].split(",")[1]);
            int x1 = Integer.parseInt(line[1].split(",")[0]);
            int y1 = Integer.parseInt(line[1].split(",")[1]);

            if(x0 == x1 || y0 == y1){
                for(int x = Math.min(x0, x1); x <= Math.max(x0, x1); x++){
                    for(int y = Math.min(y0, y1); y <= Math.max(y0, y1); y++){
                        addPoint(points, x, y);
                    }
                }
            } else if(includeDiagonals){
                if(x0 < x1){
                    for(int x = x0; x <= x1; x++){
                        if(y0 < y1){
                            addPoint(points, x, x-x0+y0);
                        } else {
                            addPoint(points, x,y0-(x-x0));
                        }
                    }
                } else {
                    for(int x = x1; x <= x0; x++){
                        if(y0 < y1){
                            addPoint(points, x,y1-(x-x1));
                        } else {
                            addPoint(points, x,x-x1+y1);
                        }
                    }
                }
            }
        }
        myReader.close();
        return points.values().stream().filter(x -> x > 1).toArray().length;
    }

    private static void addPoint(HashMap<String, Integer> points, int x, int y) {
        if(points.get(String.format("%d,%d", x, y)) == null){
            points.put(String.format("%d,%d", x, y), 1);
        } else {
            points.put(String.format("%d,%d", x, y), points.get(String.format("%d,%d", x, y)) + 1);
        }
    }

    public static int countFunctionalLines(String filePath) throws FileNotFoundException {

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        int n = 0;

        while(myReader.hasNextLine()){
            n += myReader.nextLine().length() > 1 ? 1 : 0;
        }
        myReader.close();
        return n;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Part 1:\nAt how many points do at least two lines overlap?");
            System.out.println(solve("day05/src/main/resources/input01.txt", false));
            System.out.println("Part 2:\nAt how many points do at least two lines overlap?");
            System.out.println(solve("day05/src/main/resources/input01.txt", true));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
