package io.zanhorvat.aoc21.day13

import java.io.*
import java.util.*

class Puzzle {

    fun solve(path: String, nFolds: Int, drawImage: Boolean) {

        val scanner = Scanner(File(path))
        var points = arrayListOf<Pair<Int, Int>>()

        var atCommands = false

        while(scanner.hasNextLine() && !atCommands){ // Collect points.
            val line = scanner.nextLine()
            if(line.isEmpty()){
                atCommands = true
            } else {
                points.add(Pair(line.split(",")[0].toInt(), line.split(",")[1].toInt()))
            }
        }

        var count = nFolds

        while (scanner.hasNextLine() && count > 0){ // Do the folding
            count--
            var line = scanner.nextLine()

            line = line.replace("fold along ", "")
            val axis = line.split("=")[0]
            val value = line.split("=")[1].toInt()

            points = fold(points, axis, value)
        }

        if (drawImage) drawImage(points)

        println(points.size)
    }

    private fun fold(points: ArrayList<Pair<Int, Int>>, axis: String, mid: Int) : ArrayList<Pair<Int, Int>>{
        val foldedPoints = arrayListOf<Pair<Int, Int>>()

        when (axis) {
            "y" -> {
                points.forEach {
                    if(it.second > mid && !foldedPoints.contains(Pair(it.first, 2* mid - it.second))) {
                        foldedPoints.add(Pair(it.first, 2* mid - it.second))
                    } else if (it.second < mid){
                        if(!foldedPoints.contains(Pair(it.first, it.second))){
                            foldedPoints.add(Pair(it.first, it.second))
                        }
                    }
                }
            }
            "x" -> {
                points.forEach {
                    if(it.first > mid && !foldedPoints.contains(Pair(2 * mid - it.first, it.second))) {
                        foldedPoints.add(Pair(2 * mid - it.first, it.second))
                    } else if (it.first < mid) {
                        if(!foldedPoints.contains(Pair(it.first, it.second))){
                            foldedPoints.add(Pair(it.first, it.second))
                        }
                    }
                }
            }
        }

        return foldedPoints
    }

    private fun drawImage(points: ArrayList<Pair<Int, Int>>) {
        var maxY = 0
        var maxX = 0

        points.forEach {
            if(it.first > maxX) maxX = it.first
            if(it.second > maxY) maxY = it.second
        }

        for (i in 0..maxY){
            for (j in 0..maxX){
                if(points.contains(Pair(j,i))){
                    print("X")
                } else {
                    print(" ")
                }
            }
            println()
        }
    }

}

fun main() {
    Puzzle().solve("day13/src/main/resources/input01.txt", 1, false)
    Puzzle().solve("day13/src/main/resources/input01.txt", Int.MAX_VALUE, true)
}