// Timmy Vo
// 10/13/21
// CSE143X
// TA: Andrew Frazier
// Assignment #3
//
// This program will play the game called "Bagels" with the user.

import java.util.*;

public class Bagels {

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    intro();
    prompter(console);
  }

  public static void intro() {
    System.out.println("Welcome to CSE 143x Bagels!");
    System.out.println("I'll think up a number for you to guess.");
    System.out.println("Each digit will be between 1 and 9.");
    System.out.println("Try to guess my number, and I'll say \"fermi\"");
    System.out.println("for each digit you get right and in the right");
    System.out.println("place, \"pico\" for each digit you get right");
    System.out.println("that is in the wrong place, and \"bagels\"");
    System.out.println("if you don't get any digits right at all.");
  }

  // Will output how many guesses it took the user to win
  // and prompt the user if the user would like to
  // play again. If they say an answer that contain n/N, it then
  // outputs the overall results of the game.
  public static void prompter(Scanner console) {
    Boolean gaming = true;
    int games = 0;
    int guesses = 0;
    int sumGuess = 0; 
    int bestGame = 0;
    while (gaming) {
      games++;
      guesses = game(console);
      sumGuess += guesses;
      if (guesses == 1)
        System.out.println("You got it right in " + guesses + " guess!");
      else
        System.out.println("You got it right in " + guesses + " guesses!");
      if (bestGame > guesses || games == 1)
        bestGame = guesses;
      System.out.print("Do you want to play again? ");
      String contin = console.next();
      if (contin.contains("n") || contin.contains("N"))
        gaming = false;
    }
    double avg = Math.round((guesses / games) * 10) / 10;
    overallResults(games, guesses, avg, bestGame);
  }

  public static void overallResults(int games, int guesses, double avg, int bestGame) {
    System.out.println("\nOverall results:\n"
        + "    total games   = " + games
        + " \n    total guesses = " + guesses
        + " \n    guesses/game  = " + avg
        + " \n    best game     = " + bestGame);
  }

  // Prompts the user for how many digits they want to guess
  // for and their guess. It then checks if the guess is correct and if
  // not then the method prints a hint.
  public static int game(Scanner console) {
    String rand = "";
    int guesses = 0;
    boolean correct = false;
    System.out.print("\nHow many digits this time? ");
    int howMany = console.nextInt();
    rand = randNum(howMany);
    while (!correct) {
      guesses++;
      System.out.print("Your guess? ");
      String userIn = console.next();
      if (userIn.equals(rand))
        correct = true;
      else
        System.out.println(hint(userIn, rand));
    }
    return guesses;
  }

  // Returns a hint and takes the user's guess,
  // Takes String userIn, and the randomly generated answer, String rand, as
  // parameters.
  public static String hint(String userIn, String rand) {
    String hint = fermiCheck(userIn, rand);
    if (hint.isBlank())
      return "bagels";
    else
      return hint;
  }

  // Checks how many fermi matches there are and takes the user's
  // guess, String userIn, and the answer, String rand, as parameters.
  public static String fermiCheck(String userIn, String rand) {
    String hint = "";
    for (int i = 0; i < rand.length(); i++) {
      for (int j = 0; j < userIn.length(); j++) {
        if (rand.charAt(i) == userIn.charAt(j)) {
          if (i == j) {
            hint += "fermi ";
            userIn = replace(userIn, j, "x");
            rand = replace(rand, i, "X");
          }
        }
      }
    }
    return hint + picoCheck(userIn, rand);
  }

  // Checks how many pico matches there are and takes the user's
  // guess, String userIn, and the answer, String rand, as parameters.
  public static String picoCheck(String userIn, String rand) {
    String picos = "";
    for (int i = 0; i < rand.length(); i++) {
      for (int j = 0; j < userIn.length(); j++) {
        if (rand.charAt(i) == userIn.charAt(j)) {
          picos += "pico ";
          userIn = replace(userIn, j, "x");
          rand = replace(rand, i, "X");
        }
      }
    }
    return picos;
  }

  // returns the string obtained by replacing the character at the given
  // index with the replacement text
  public static String replace(String s, int index, String replacement) {
    return s.substring(0, index) + replacement + s.substring(index + 1);
  }

  // Takes int Max, the answer's length, and generates
  // that many numbers between 1 and 9.
  public static String randNum(int max) {
    Random random = new Random();
    String rand = "";
    for (int i = 0; i < max; i++) {
      rand += String.valueOf(random.nextInt(9) + 1);
    }
    return rand;
  }
}