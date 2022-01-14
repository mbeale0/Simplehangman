package com.masonbeale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanClassTest {
    @Test
    @DisplayName("Check win/lose method")
    void WinLoss(){
        Hangman hangman = new Hangman();
        boolean loss = hangman.CheckWinLoss(-1, 0, "Test");
        boolean win = hangman.CheckWinLoss(4, 5, "Test");

        assertFalse(loss);
        assertFalse(win);
    }

}