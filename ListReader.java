import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads a word list from an input file and creates an array from it.
 * @author dongates
 *
 */
public class ListReader {
	
	private static final int VALID_WORD_LENGTH = 5;
	
	/**
	 * Reads a file to make the list of valid Wordle guesses
	 * @param filepath path of file to read
	 * @return
	 */
	public String[] makeList(String filepath) {
		String[] wordList = new String[10];
		int idx = 0;
		//read the file
		try(Scanner scan = new Scanner(new FileInputStream(filepath), "UTF8")) {
			//add file contents to word list array.  Track index so the final array does not have 
			//empty values on the end
			while (scan.hasNextLine()) {
				//grow array capacity if more room is needed
				if (idx >= wordList.length) {
					wordList = Arrays.copyOf(wordList, idx * 2);
				}
				wordList[idx] = scan.nextLine();
				idx++;
			}
			//trim unused buckets from array before returning
			wordList = Arrays.copyOf(wordList, idx);
			Arrays.parallelSort(wordList);
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file name");
			return null;
		}
		return wordList;
	}
	
	/**
	 * Checks an array to ensure all contents are 5-letter words, and none are repeats
	 * Requires array to be sorted lexically
	 * @param wordList array of words sorted lexically to be verified
	 * @return true if all words are 5 letters, otherwise false
	 */
	public boolean checkList(String[] wordList) {
		for (String word : wordList) {
			if (word.length() != VALID_WORD_LENGTH) {
				return false;
			}
		}
		for (int i = 0; i < wordList.length - 1; i++) {
			if (wordList[i].compareTo(wordList[i + 1]) == 0) {
				return false;
			}
		}
		
		return true;
	}
	
}
