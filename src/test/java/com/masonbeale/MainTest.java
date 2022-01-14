package com.masonbeale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HangmanClassTest {
    @Test
    @DisplayName("Check win/lose method")
    void WinLoss(){
        Hangman hangman = new Hangman();
        boolean loss = hangman.CheckWinLoss(-1, 0, "Test");
        boolean win = hangman.CheckWinLoss(4, 5, "Test");

        assertTrue(loss);
        assertFalse(win);
    }
    // need to figure out how to test function that needs user input
    /*@Test
    @DisplayName("Check guess name method")
    void checkGuessName(){
        Hangman hangman = new Hangman();
        // currently, word argument is like main function and not dynamic
        boolean correctWord = hangman.GuessWord("moose");
        Scanner scanner = new Scanner(System.in);
        String answer = "y";
        System.in(answer);
        assertFalse(correctWord);
    }*/

}