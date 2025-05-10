import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LibraryOperation {

    public void menu() {
        System.out.println("\n--- Library Operation System ---\n");
        System.out.println("1. Add a new word");
        System.out.println("2. Remove a word");
        System.out.println("3. Translate a word");
        System.out.println("4. Show all words");
        System.out.println("5. Finish the operations");
        System.out.print("Enter your selection: ");
    }

    public void addNewWord(File file, String word, String translation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath(), true))) {
            String line = word.concat(":").concat(translation).toUpperCase();
            writer.write(line);
            writer.newLine();
            System.out.println("Word added!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removeWord(File file, String word) {
        File temp = new File("temp.txt");
        boolean isFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(temp.getPath(), true))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] words = currentLine.split(":");
                if (words.length == 2 && !words[0].equalsIgnoreCase(word)) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    isFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (isFound) {
            if (file.delete()) {
                if (temp.renameTo(file)) {
                    System.out.println("File is updated!");
                } else {
                    System.out.println("File couldn't be updated...");
                }
            } else {
                System.out.println("File couldn't be deleted...");
            }
        } else {
            System.out.println("Word not found!");
        }

    }

    public void translate(File file, String word) {
        Map<String, String> library = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
            String line, translation;
            while (Objects.nonNull(line = reader.readLine())) {
                String[] words = line.split(":");
                library.put(words[0].toUpperCase(), words[1].toUpperCase());
            }
            translation = library.get(word);
            if (Objects.nonNull(translation)) {
                System.out.println("Translation: " + translation);
            } else {
                throw new RuntimeException("Word not found!");
            }
        } catch (IOException e) {
            System.out.println("There's no word in library..");
        }
    }

    public void showAllWords(File file) {
        Map<String, String> library = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(":");
                library.put(words[0].toUpperCase(), words[1].toUpperCase());
            }

            if (library.isEmpty()) {
                System.out.println("There's no word in library...");
            } else {
                System.out.printf("%-20s %-20s%n", "Word", "Translation");
                library.forEach((key, value) -> System.out.printf("%-20s %-20s%n", key, value));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}