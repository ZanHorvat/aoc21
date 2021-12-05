package io.github.zanhorvat.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void solvePart1() {
        assertDoesNotThrow(() -> assertEquals(5, Puzzle.solve("src/test/resources/input00.txt", false)));
    }

    @Test
    void solvePart2() {
        assertDoesNotThrow(() -> assertEquals(12, Puzzle.solve("src/test/resources/input00.txt", true)));
    }
}