/**
 * handles all of the checking of a user's guess, as well as formatting the user's guess
 * appropriately back out to the console
 * @author dongates
 *
 */
public class GuessChecker {
	/** length of a legal word in the game */
	private static final int VALID_WORD_LENGTH = 5;
	
	/** the word player is trying to guess, rendered as a character array */
	private char[] targetWord;
	
	/**
	 * constrctor for the class
	 * @param target the word player is trying to gues
	 */
	public GuessChecker(String target) {
		targetWord = target.toCharArray();
	}
	
	/**
	 * Determines if the player's guess is valid.  Guess must be 5 letters and must
	 * be contained in the word list
	 * @param guess player's guess
	 * @param wordList list of valid words for the game
	 * @return true if guess is valid, otherwise false
	 */
	public boolean validGuess(String guess, String[] wordList) {
		if (guess.length() != VALID_WORD_LENGTH) {
			return false;
		}
		boolean found = false;
		for (int i = 0; i < wordList.length; i++) {
			if (guess.compareTo(wordList[i]) == 0) {
				found = true;
			}
		}
		return found;
	}
	
	/**
	 * Checks the player's guess against the target
	 * @param guess
	 */
	public int printGuess(String guess) {
		int matches = 0;
		char[] guessArray = guess.toCharArray();
		//check if the player has guessed the word
		for (int i = 0; i < guessArray.length; i++) {
			if (guessArray[i] == targetWord[i]) {
				++matches;
			}
		}
		//if true, player has won
		if (matches == VALID_WORD_LENGTH) {
			System.out.println(Wordle.GREEN + guess + Wordle.RESET);
			System.out.println("You win!");
			//this will ensure the gameplay while loop terminates
			return 10;
		}
		
		//arrays will be used to eliminate letters from consideration
		//tracks indices of matched letters
		int[] match = {1, 1, 1, 1, 1};
		//tracks indices of guess letters contained in target, but not in right spot
		int[] contains = {1, 1, 1, 1, 1};
		//tracks indices of letters in target word that are in the guess as well
		int[] eliminated = {1, 1, 1, 1, 1};
		
		//look for matches
		for (int j = 0; j < guess.length(); j++) {
			if (guessArray[j] == targetWord[j]) {
				match[j] = 0;
				eliminated[j] = 0;
			}
		}
		
		//look for leftovers
		for (int k = 0; k < guess.length(); k++) {
			for (int m = 0; m < targetWord.length; m++) {
				if (eliminated[m] != 0 && guessArray[k] == targetWord[m]) {
					eliminated[m] = 0;
					contains[k] = 0;
					//break loop
					m = targetWord.length;
				}
			}
		}
		
		//print your guess
		for (int letter = 0; letter < guessArray.length; letter++) {
			if (match[letter] == 0) {
				System.out.print(Wordle.GREEN + guessArray[letter] + Wordle.RESET);
			} else if (contains[letter] == 0) {
				System.out.print(Wordle.YELLOW + guessArray[letter] + Wordle.RESET);
			} else {
				System.out.print(guessArray[letter]);
			}
		}
		
		System.out.println();
		
		//this return value can be used to increment the turn counter
		return 1;
	}
}
