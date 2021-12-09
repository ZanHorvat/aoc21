package io.github.zanhorvat.aoc21.day09

import java.io.File
import java.lang.Exception

class Puzzle {

    fun solve(path: String, isPart2: Boolean): Int{
        val field : ArrayList<List<Int>> = arrayListOf()
        val seen : ArrayList<BooleanArray> = arrayListOf()
        val m : ArrayList<Int> = arrayListOf() // Basin sizes
        var n = 0

        File(path).forEachLine {
            field.add(it.toCharArray().map { ix -> ix.toString().toInt() })
            seen.add(BooleanArray(it.length))
        }

        field.forEachIndexed{ ix, x ->
            x.forEachIndexed{ iy, y ->
                if(
                        0 <= iy && iy < x.size && 0 <= ix && ix < field.size &&
                        y < getValue(field, ix, iy-1) && y < getValue(field, ix, iy+1) &&
                        y < getValue(field, ix-1, iy) && y < getValue(field, ix+1, iy)
                ){
                    n += 1 + y
                    if (isPart2) m.add( evolve(field, seen, ix, iy) )
                }
            }
        }

        return if(isPart2){
            m.sort()
            m[m.lastIndex] *  m[m.lastIndex-1] * m[m.lastIndex-2]
        } else {
            n
        }
    }

    private fun evolve(field: ArrayList<List<Int>>, seen: ArrayList<BooleanArray>, ix: Int, iy: Int): Int {
        val value = getValue(field, ix, iy)
        return if(value == Int.MAX_VALUE || seen[ix][iy]){
            0
        } else if(value == 9) {
            seen[ix][iy] = true
            0
        }
        else {
            seen[ix][iy] = true
            1 + evolve(field, seen, ix+1, iy) + evolve(field, seen, ix-1, iy) + evolve(field, seen, ix, iy+1) + evolve(field, seen, ix, iy-1)
        }
    }

    private fun getValue(field: ArrayList<List<Int>>, ix: Int, iy: Int): Int {
        return try {
            field[ix][iy]
        } catch (e : Exception){
            Int.MAX_VALUE
        }
    }
}

fun main() {
    println(Puzzle().solve("day09/src/main/resources/input01.txt", false))
    println(Puzzle().solve("day09/src/main/resources/input01.txt", true))
}