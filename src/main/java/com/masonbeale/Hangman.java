package com.masonbeale;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        Scanner scanner = new Scanner(System.in);
        String currentUserGuess;
        boolean stillPlaying = true;
        int correctGuesses = 0;
        int previousCorrectGuesses = 0;
        int lives = 10;
        String hiddenWord;
        List<String> possibleWords = new ArrayList<>();
        List<String> guessedLetters = new ArrayList<>();

        ReadInWords(possibleWords);
        hiddenWord = PickRandomWord(possibleWords);
        LettersForWord[] lettersOfWord = SetupGame(hiddenWord);


        while(stillPlaying){
            PrintInfo(lives, hiddenWord, lettersOfWord);

            currentUserGuess = hangman.HandleUserInput(scanner, guessedLetters);

            correctGuesses = hangman.checkCorrectGuesses(currentUserGuess, correctGuesses, lettersOfWord);

            if(correctGuesses == previousCorrectGuesses){
                lives--;
            }
            stillPlaying = hangman.CheckWinLoss(correctGuesses, lives, hiddenWord);

            if((stillPlaying) && (correctGuesses > previousCorrectGuesses)){
                stillPlaying = hangman.GuessWord(hiddenWord);
            }

            previousCorrectGuesses = hangman.SetupNextRound(currentUserGuess, correctGuesses, guessedLetters);
        }
    }



    private static void PrintInfo(int lives, String hiddenWord, LettersForWord[] lettersOfWord) {
        System.out.println("\n\nLives: " + lives);
        for(int i = 0; i < hiddenWord.length(); i++){
            if(lettersOfWord[i].isLetterKnown()){
                System.out.print(lettersOfWord[i].getLetter());
            }
            else{
                System.out.print("_");
            }
        }
    }


    private static String PickRandomWord(List<String> possibleWords) {
        String hiddenWord;
        Random rand = new Random();
        int upperbound = possibleWords.size()-1;
        int randomNumber = rand.nextInt(upperbound);
        hiddenWord = possibleWords.get(randomNumber);
        return hiddenWord;
    }

    private static LettersForWord[] SetupGame(String hiddenWord) {
        System.out.println("Welcome to Hangman!");
        LettersForWord lettersOfWord[] = new LettersForWord[hiddenWord.length()];
        for(int i = 0; i < hiddenWord.length(); i++){
            lettersOfWord[i] = new LettersForWord(hiddenWord.charAt(i));
        }
        return lettersOfWord;
    }

    private static void ReadInWords(List<String> possibleWords) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader("words.txt"));
            fileScanner.useDelimiter(",");
            while (fileScanner.hasNext()){
                possibleWords.add(fileScanner.nextLine().toLowerCase());

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileScanner != null){
                fileScanner.close();
            }
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


    protected String HandleUserInput(Scanner scanner, List<String> guessedLetters) {
        boolean gettingUserInput = true;
        boolean isInputChar = false;
        boolean isInputNewGuess = false;

        System.out.print("\nEnter a letter: ");
        String currentGuess = scanner.next();

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
        currentGuess.toLowerCase();
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
