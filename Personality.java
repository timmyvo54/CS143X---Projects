// Timmy Vo
// 10/21/21
// CSE143X
// TA: Andrew Frazier
// Assignment #4
//
// This program will read a text file and compute the data scores into an output file

import java.util.*;
import java.io.*;

public class Personality {
  static final int TYPES = 4;

  public static void main(String args[]) throws FileNotFoundException {
    Scanner console = new Scanner(System.in);
    intro();
    prompter(console);
  }

  // Prints out the introduction to the program.
  public static void intro() {
    System.out.println("This program processes a file of answers to the");
    System.out.println("Keirsey Temperament Sorter.  It converts the ");
    System.out.println("various A and B answers for each person into");
    System.out.println("sequence of B-percentages and then into a");
    System.out.println("four-letter personality type.");
    System.out.println();
  }

  // Prompts the user for the input file name and what the user wants the output to be.
  public static void prompter(Scanner console) throws FileNotFoundException {
    String inputFileName = "";
    String outputFileName = "";
    System.out.print("input file name? ");
    inputFileName = console.next();
    Scanner data = new Scanner(new File(inputFileName));
    System.out.print("output file name? ");
    outputFileName = console.next();
    console.close();
    PrintStream output = new PrintStream(outputFileName);
    read(data, output);
    output.close();
  }

  // Reads the file input and then sorts and formats the data. It then outputs the
  // formatted data into a file.
  public static void read(Scanner data, PrintStream output) {
    while (data.hasNextLine()) {
      String name = data.nextLine();
      String choices = data.nextLine();
      String[] sortedChoices = getAnswers(choices);
      int[] percentages = interpret(sortedChoices);
      String personalityType = typeThePerson(percentages);
      output.println(name + ": " + Arrays.toString(percentages) + " = " + personalityType);
    }
  }

  // Takes the string, line, as a parameter and returns sorted answer array.
  // Seperates the a person's answers into the correct dimensions.
  // Each dimension is 7 questions and there are 10 groups.
  public static String[] getAnswers(String line) {
    String[] answers = new String[4];
    for (int i = 0; i < 70; i++) {
      int check = i % 7;
      if (check == 0)
        answers[0] += line.charAt(i);
      else if (check == 1 || check == 2)
        answers[1] += line.charAt(i);
      else if (check == 3 || check == 4)
        answers[2] += line.charAt(i);
      else if (check == 5 || check == 6)
        answers[3] += line.charAt(i);
    }
    return answers;
  }

  // Takes array data as a parameter and return an int array of percentages.
  // Interprets the a person's sorted answers into respective percentages.
  public static int[] interpret(String[] data) {
    int[] percents = new int[TYPES];
    double total = 0;
    int percent = 0;
    double percentage = 0;
    for (int i = 0; i < TYPES; i++) {
      double[] counts = compute(data[i]);
      total = counts[0] + counts[1];
      percentage = (counts[1] * 100) / total;
      percents[i] = (int) Math.round(percentage);
    }
    return percents;
  }

  // Takes an int array as a parameter and returns a string, type.
  // Uses the percentages to find out which kind of dimension a person is.
  public static String typeThePerson(int[] dimensions) {
    char[] aType = { 'E', 'S', 'T', 'J' };
    char[] bType = { 'I', 'N', 'F', 'P' };
    String type = "";
    for (int i = 0; i < TYPES; i++) {
      if (dimensions[i] > 50)
        type += bType[i];
      else if (dimensions[i] < 50)
        type += aType[i];
      else
        type += 'X';
    }
    return type;
  }

  // Takes a string named theirChoices as a parameter and returns an int array,
  // counts. Counts the number of times a person picked a, and picked b within a
  // dimension.
  public static double[] compute(String theirChoices) {
    double[] counts = new double[2];
    int aCount = 0;
    int bCount = 0;
    char choice;
    for (int i = 0; i < theirChoices.length(); i++) {
      choice = theirChoices.charAt(i);
      if (choice == 'A' || choice == 'a')
        aCount++;
      else if (choice == 'B' || choice == 'b')
        bCount++;
    }
    counts[0] = aCount;
    counts[1] = bCount;
    return counts;
  }
}