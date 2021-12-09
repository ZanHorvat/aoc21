package io.github.zanhorvat.aoc21.day08

import java.io.File

class Puzzle {

    fun countTheObvious(path: String): Int{
        var count = 0
        File(path).forEachLine {
            it.split("|")[1].split(" ").forEach {
                e -> if(e.length in arrayOf(2,3,4,7)) count++
            }
        }
        return count
    }

    fun decode(path: String): Int {

        var count = 0

        File(path).forEachLine { it ->
            val input = it.split(" | ")[0].split(" ")
            val output = it.split(" | ")[1].split(" ")

            val patterns : Array<String?> = arrayOfNulls(10)

            patterns[1] = input.find { it.length == 2 }
            patterns[4] = input.find { it.length == 4 }
            patterns[7] = input.find { it.length == 3 }
            patterns[8] = input.find { it.length == 7 }

            patterns[9] = input.find { it.length == 6 && nIntersections(patterns[4], it) == 4 }
            patterns[0] = input.find { it.length == 6 && nIntersections(patterns[7], it)  == 3 && it != patterns[9] }
            patterns[6] = input.find { it.length == 6 && it != patterns[9] && it != patterns[0] }

            patterns[5] = input.find { it.length == 5 && nIntersections(patterns[6], it) == 5}
            patterns[3] = input.find { it.length == 5 && nIntersections(patterns[4], it) == 3 && it != patterns[5]}
            patterns[2] = input.find { it.length == 5 && it != patterns[5] && it != patterns[3] }

            val digits = output.map { p -> patterns.indexOf(patterns.find {
                nIntersections(p, it) == p.length && p.length == (it?.length ?: false)
            }).toString()
            }

            count += digits.reduce{acc, string -> acc + string}.toInt()
        }
        return count
    }

    private fun nIntersections(first: String?, second: String?) : Int{
        if (first != null && second != null) {
            return first.let { second.toCharArray().intersect(it.asIterable()).count() }
        }
        return -1
    }
}


fun main(){
    println("Day 8: Seven Segment Search\n")
    println("Part 1: " + Puzzle().countTheObvious("day08/src/main/resources/input01.txt"))
    println("Part 2: " + Puzzle().decode("day08/src/main/resources/input01.txt"))
}
