package com.masonbeale;

public class LettersForWord {
    private boolean isLetterKnown = false;
    private char letter;

    public LettersForWord(char letter) {
        this.letter = letter;
    }

    public boolean isLetterKnown() {
        return isLetterKnown;
    }

    public char getLetter() {
        return letter;
    }
    public void setLetterKnown(boolean letterKnown) {
        isLetterKnown = letterKnown;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
