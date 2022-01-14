package com.masonbeale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        Scanner scanner = new Scanner(System.in);
        String currentGuess;
        boolean stillPlaying = true;
        int correctGuesses = 0;
        int previousCorrectGuesses = 0;
        int lives = 5;
        List<String> guessedLetters = new ArrayList<String>();

	    // hidden word will be pre-determined to start, then more dynamic after
        String hiddenWord = "moose";
        hiddenWord.toLowerCase();
        LettersForWord lettersOfWord[] = new LettersForWord[hiddenWord.length()];

        hangman.SetupGame(hiddenWord, lettersOfWord);
        System.out.println();

        while(stillPlaying){
            System.out.println("\n\nLives: " + lives);
            System.out.print("Enter a letter: ");
            currentGuess = scanner.next();

            currentGuess = hangman.CheckUserInput(scanner, currentGuess, guessedLetters);


            currentGuess.toLowerCase();
            correctGuesses = hangman.checkCorrectGuesses(currentGuess, correctGuesses, lettersOfWord);

            if(correctGuesses == previousCorrectGuesses){
                lives--;
            }
            stillPlaying = hangman.CheckWinLoss(correctGuesses, lives, hiddenWord);

            stillPlaying = hangman.GuessWord(hiddenWord);


            previousCorrectGuesses = hangman.SetupNextRound(currentGuess, correctGuesses, guessedLetters);
        }
    }



    protected int checkCorrectGuesses(String currentGuess, int correctGuesses, LettersForWord[] lettersOfWord) {
        for(int i = 0; i < lettersOfWord.length; i++){
            if(currentGuess.charAt(0) == lettersOfWord[i].getLetter()){
                lettersOfWord[i].setLetterKnown(true);
                correctGuesses++;
            }
            if (lettersOfWord[i].isLetterKnown() == true) {
                System.out.print(lettersOfWord[i].getLetter());
            }
            else {
                System.out.print("_");
            }
        }
        return correctGuesses;
    }

    protected void SetupGame(String hiddenWord, LettersForWord[] lettersOfWord) {
        for(int i = 0; i < hiddenWord.length(); i++){
            lettersOfWord[i] = new LettersForWord(hiddenWord.charAt(i));
            // no checking needs to be done on first output as all letters are blank
            System.out.print("_");
        }
    }

    protected String CheckUserInput(Scanner scanner, String currentGuess, List<String> guessedLetters) {
        boolean gettingUserInput = true;
        boolean isInputChar = false;
        boolean isInputNewGuess = false;

        while (gettingUserInput){
            if(currentGuess.length() == 1){
                isInputChar = true;
            }
            else {
                System.out.print("please only enter one letter: ");
                currentGuess = scanner.next();
            }
            if(guessedLetters.contains(currentGuess)){
                System.out.println("You have already guessed that letter, guess again: ");
                currentGuess = scanner.next();
            }
            else {
                isInputNewGuess = true;
            }
            if(isInputChar && isInputNewGuess){
                gettingUserInput = false;
            }
        }
        return currentGuess;
    }

    protected boolean CheckWinLoss(int correctGuesses, int lives, String hiddenWord) {
        if(correctGuesses == hiddenWord.length()) {
            System.out.println("\n\nyou won!");
            return false;
        }
        if(lives == 0){
            System.out.println("\n\nyou lost!");
            return false;
        }
        return true;
    }
    protected boolean GuessWord(String hiddenWord) {
        System.out.println("\nWould you like to guess the word? y/n");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toLowerCase(Locale.ROOT);
        if(answer.equals("y")){
            System.out.println("Enter Guess: ");
            String Guess = scanner.next().toLowerCase();
            if (Guess.equals(hiddenWord.toLowerCase())){
                System.out.println("\nYou won!");
                return false;
            }
        }
        return true;
    }
    protected int SetupNextRound(String currentGuess, int correctGuesses, List<String> guessedLetters) {
        int previousCorrectGuesses;
        guessedLetters.add(currentGuess);
        previousCorrectGuesses = correctGuesses;
        return previousCorrectGuesses;
    }
}
