import java.util.*;
import java.util.regex.*;

public class GroceryChatbot {

    // Define patterns and responses
    static Map<String, String> responses = new HashMap<>();

    static {
        responses.put("\\b(hey|hi|hello)\\b", "Hey there! How can I help you?");
    }

    // Chatbot method
    public static String chatbot(String userInput) {
        String input = userInput.toLowerCase();

        for (Map.Entry<String, String> entry : responses.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return entry.getValue();
            }
        }
        return "I'm sorry, I don't understand you. Please try again.";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to GROCERY Chatbot");

        while (true) {
            System.out.print("You: ");
            String userMsg = sc.nextLine();

            if (userMsg.equalsIgnoreCase("bye")) {
                System.out.println("Chatbot: Goodbye");
                break;
            }

            String response = chatbot(userMsg);
            System.out.println("Chatbot: " + response);
        }

        sc.close();
    }
}
