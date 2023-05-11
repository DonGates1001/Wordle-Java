import java.util.Random;
import java.util.Scanner;

//https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println#5762502
public class Wordle {
	
	//Color codes used in game for print output
	public static final String GREEN = "\033[0;32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String RESET = "\u001B[0m";
	
	/** number of turns in a game to guess the answer*/
	private static final int MAX_TURNS = 6;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ListReader lr = new ListReader();
		
		//Print out welcome message
		System.out.print("Welcome to ");
		System.out.print(GREEN + "W" + RESET + "o");
		System.out.print(YELLOW + "r" + GREEN + "d" + RESET);
		System.out.println("l" + YELLOW + "e\n" + RESET);
		
		//get user file to run the game
		System.out.print("Please enter a file path for your word list: ");
		String filePath = input.nextLine();
		
		//array of playable words for the game
		String[] playableWords = lr.makeList(filePath);
		
		//check if word list is game-compliant, all 5 letter words and no duplicate words
		while (!lr.checkList(playableWords) || playableWords == null) {
			System.out.println("The provided file is an invalid word list containing either duplicates or non five-letter words, or the file was not found.");
			System.out.print("Please enter a file path for your word list: ");
			filePath = input.nextLine();
			
			//array of playable words for the game
			playableWords = lr.makeList(filePath);
			
			
		}
		
		System.out.print("We're ready to play ");
		System.out.print(GREEN + "W" + RESET + "o");
		System.out.print(YELLOW + "r" + GREEN + "d" + RESET);
		System.out.println("l" + YELLOW + "e" + RESET + "!\n");
		
		System.out.println("Please enter a 5-letter guess");
		
		//if you don't guess in 6 turns, you lose
		int turns = 0;
		
		//determine the target word
		Random rand = new Random();
		int targetIndex = rand.nextInt(playableWords.length);
		String targetWord = playableWords[targetIndex];
		
		//set up the GuessChecker
		GuessChecker checker = new GuessChecker(targetWord);
		
		while (turns < MAX_TURNS) {
			String guess = input.nextLine();
			while (!checker.validGuess(guess, playableWords)) {
				System.out.print("Your guess is not in the word list.  Please enter a different word: ");
				guess = input.nextLine();
			}
			turns += checker.printGuess(guess);
		}
		
		//Either all turns have been used or player has won the game
		if (turns == MAX_TURNS) {
			System.out.println("Sorry, you didn't guess the word in six tries or less.");
			System.out.println("The Wordle was: " + GREEN + targetWord + RESET);
		}
		
		input.close();
	}
	
	

}
