package io.github.zanhorvat.aoc21.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleTest {

    @Test
    void calculateSubmarinePosition00() {
        assertDoesNotThrow(() -> assertEquals(150, Puzzle.calculateSubmarinePosition("src/test/resources/input00.txt", false)) );
    }

    @Test
    void calculateSubmarinePositionWithAim00() {
        assertDoesNotThrow(() -> assertEquals(900, Puzzle.calculateSubmarinePosition("src/test/resources/input00.txt", true)) );
    }
}