package io.github.zanhorvat.aoc21.day04;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void predictBingoFirst00() {
        assertDoesNotThrow(() -> assertEquals(4512, Puzzle.predictBingo("src/test/resources/input00.txt", true)));
    }

    @Test
    void predictBingoLast00() {
        assertDoesNotThrow(() -> assertEquals(1924, Puzzle.predictBingo("src/test/resources/input00.txt", false)));
    }


}