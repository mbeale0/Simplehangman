package com.masonbeale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LettersClassTest {

    @Test
    @DisplayName("Check the ability to set letter known")
    void testSetLetterKnown() {
        LettersForWord lettersForWord = new LettersForWord();
        lettersForWord.setLetterKnown(true);
        assertTrue(lettersForWord.isLetterKnown());

    }

    @Test
    @DisplayName("Check ability to set letter")
    void testSetLetter() {
        LettersForWord lettersForWord = new LettersForWord();
        lettersForWord.setLetter('b');
        assertEquals('b', lettersForWord.getLetter());
    }
}