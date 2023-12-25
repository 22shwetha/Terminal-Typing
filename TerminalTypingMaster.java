package package1;
import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.*;
public class TerminalTypingMaster {
	    private static final String WORDS_FILE_PATH = "words.json";
	    private static final String LEADERBOARD_FILE_PATH = "leaderboard.json";

	    private static List<String> words;
	    private static Map<String, Integer> leaderboard;

	    public static void main(String[] args) {
	        initialize();
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter your username: ");
	        String username = scanner.nextLine();

	        while (true) {
	            System.out.println("Options:");
	            System.out.println("1. Start Typing Test");
	            System.out.println("2. Show Leaderboard");
	            System.out.println("3. Exit");

	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    startTypingTest(username);
	                    break;
	                case 2:
	                    showLeaderboard();
	                    break;
	                case 3:
	                    exit(username);
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }
	    }

	    private static void initialize() {
	        loadWordsFromJson();
	        loadLeaderboardFromJson();
	    }

	    private static void loadWordsFromJson() {
	        // Implement loading words from a JSON file into the 'words' list
	        // Placeholder: For simplicity, initialize with some default words
	        words = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
	    }

	    private static void loadLeaderboardFromJson() {
	        // Implement loading leaderboard from a JSON file into the 'leaderboard' map
	        // Placeholder: Initialize an empty leaderboard if the file doesn't exist
	        leaderboard = new HashMap<>();
	    }

	    private static void startTypingTest(String username) {
	        Scanner scanner = new Scanner(System.in);
	        int wordsTyped = 0;
	        long startTime = System.currentTimeMillis();

	        System.out.println("Type the following words. Press 'Ctrl + Q' to quit.");
	        Collections.shuffle(words);

	        for (String word : words) {
	            System.out.print(word + " ");
	            String userTyped = scanner.next();

	            if (userTyped.equals("Ctrl+Q")) {
	                System.out.println("Typing test aborted.");
	                return;
	            }

	            if (word.equals(userTyped)) {
	                wordsTyped++;
	            }
	        }

	        long endTime = System.currentTimeMillis();
	        long totalTime = endTime - startTime;
	        int wordsPerMinute = (int) ((wordsTyped * 60000) / totalTime);

	        System.out.println("\nTyping test complete!");
	        System.out.println("Words Typed: " + wordsTyped);
	        System.out.println("Time Taken: " + totalTime / 1000 + " seconds");
	        System.out.println("Words Per Minute: " + wordsPerMinute);

	        updateLeaderboard(username, wordsPerMinute);
	    }

	    private static void updateLeaderboard(String username, int wordsPerMinute) {
	        leaderboard.put(username, wordsPerMinute);
	        sortAndSaveLeaderboard();
	    }

	    private static void sortAndSaveLeaderboard() {
	        // Sort the leaderboard by WPM and save to the JSON file
	        leaderboard = sortByValue(leaderboard);

	        try (FileWriter fileWriter = new FileWriter(LEADERBOARD_FILE_PATH)) {
	            fileWriter.write(new ObjectMapper().writeValueAsString(leaderboard));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {
	        // Implement sorting the leaderboard by value (WPM)
	        // Placeholder: For simplicity, returning an unsorted map
	        return unsortedMap;
	    }

	    private static void showLeaderboard() {
	        System.out.println("\nLeaderboard:");
	        int rank = 1;

	        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
	            System.out.println(rank + ". " + entry.getKey() + ": " + entry.getValue() + " WPM");
	            rank++;
	        }
	    }

	    private static void exit(String username) {
	        System.out.println("Exiting Terminal Typing Master. Goodbye, " + username + "!");
	        saveWordsToJson();
	        saveLeaderboardToJson();
	        System.exit(0);
	    }

	    private static void saveWordsToJson() {
	        // Implement saving words to the JSON file
	        // Placeholder: For simplicity, do nothing
	    }

	    private static void saveLeaderboardToJson() {
	        // Implement saving leaderboard to the JSON file
	        // Placeholder: Do nothing for simplicity
	    }

}
