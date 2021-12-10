package io.github.zanhorvat.aoc21.day10

import java.io.File
import java.util.ArrayDeque

class Puzzle {

    private val chars = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    private val pointsPart1 = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val pointsPart2 = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
    private var scores = arrayListOf<Long>()
    var score : Long = 0

    fun solve(path: String) {
        File(path).forEachLine {
            score += errorScore(it)
        }
        scores.sort()
    }

    private fun errorScore(it: String): Long {
        val stack = ArrayDeque<Char>()
        var sum: Long = 0

        for(char in it.toCharArray()){
            if(char in chars.values){
                stack.push(char)
            } else if (stack.size == 0 || stack.pop() != chars[char]){
                sum += pointsPart1[char]!!
                stack.clear()
                break
            }
        }

        if (!stack.isEmpty()){
            var subSum : Long = 0
            for(char in stack) subSum = 5 * subSum + pointsPart2[char]!!
            scores.add(subSum)
        }

        return sum
    }

    fun getPart1(): Long {
        return score
    }

    fun getPart2(): Long {
        return scores[scores.size / 2]
    }


}

fun main() {
    val puzzle = Puzzle()
    puzzle.solve("day10/src/main/resources/input01.txt")
    println("Part 1:\n" + puzzle.getPart1())
    println("Part 2:\n" + puzzle.getPart2())
}