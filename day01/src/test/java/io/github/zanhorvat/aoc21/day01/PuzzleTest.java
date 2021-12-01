package io.github.zanhorvat.aoc21.day01;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test()
    void countSingleIncreasesInput00() {
        assertDoesNotThrow(() -> assertEquals(7, Puzzle.countSingleIncreases("src/test/resources/input00.txt")) );
    }

    @Test
    void countGroupIncreasesInput00() {
        assertDoesNotThrow(() -> assertEquals(5, Puzzle.countGroupIncreases("src/test/resources/input00.txt")) );
    }
}