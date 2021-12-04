package io.github.zanhorvat.aoc21.day03;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void findAndMultiplyGammaAndEpsilonRate00() {
        assertDoesNotThrow(() -> assertEquals(198, Puzzle.findAndMultiplyGammaAndEpsilonRate("src/test/resources/input00.txt")) );
    }

    @Test
    void findAndMultiplyGammaAndEpsilonRate01() {
        assertDoesNotThrow(() -> assertEquals(198, Puzzle.findAndMultiplyGammaAndEpsilonRate("src/test/resources/input01.txt")) );
    }

    @Test
    void calcGammaRate00() {
        Assert.assertEquals(7,Puzzle.calcGammaRate(new int[]{3,3,3}, 3));
    }

    @Test
    void calcGammaRate01() {
        Assert.assertEquals(5,Puzzle.calcGammaRate(new int[]{3,1,3}, 3));
    }

    @Test
    void calcEpsilonRate00() {
        Assert.assertEquals(0,Puzzle.calcEpsilonRate(new int[]{3,3,3}, 3));
    }

    @Test
    void name() {
        assertDoesNotThrow(() -> assertEquals(230, Puzzle.calcOxyCO2Rating("src/test/resources/input00.txt")) );
    }
}