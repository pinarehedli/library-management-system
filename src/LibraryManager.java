import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    List<File> files = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    LibraryOperation operation = new LibraryOperation();

    public void createLibrary(String libraryName) {
        File file = new File(libraryName);
        if (!files.contains(file)) {
            files.add(file);
            System.out.println("Library is created!");
        } else {
            throw new RuntimeException("Library is already existed...");
        }
    }

    public void loadLibrary() {
        if (files.isEmpty()) {
            throw new RuntimeException("There's no existing library...");
        } else {
            System.out.println("\nLibraries");
            int count = 1;
            for (File file : files) {
                System.out.println(count++ + ". " + file.getName());
            }
            System.out.print("Enter file number: ");
            int fileChoice = Integer.parseInt(sc.nextLine());
            File currFile = files.get(fileChoice - 1);
            System.out.println(currFile.getName() + " loaded");
            while (true) {
                operation.menu();
                int selection = Integer.parseInt(sc.nextLine());
                getUserChoice(selection, currFile);
            }
        }
    }

    public void remove(String libraryName) {
        if (!files.isEmpty()) {
            if (files.contains(new File(libraryName))) {
                files.removeIf(file -> file.getPath().equals(libraryName));
                System.out.println("File deleted successfully");
            } else {
                throw new RuntimeException("File not found!");
            }
        } else {
            throw new RuntimeException("There's no library on system...");
        }
    }

    public void showLibraries() {
        if (files.isEmpty()) {
            throw new RuntimeException("There's no library on system...");
        } else {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }

    public void getUserChoice(int choice, File file) {
        switch (choice) {
            case 1:
                // Add a new word
                System.out.print("Word: ");
                String word1 = sc.nextLine();
                System.out.print("Translation of word: ");
                String translation = sc.nextLine();
                operation.addNewWord(file, word1, translation);
                break;
            case 2:
                // Remove a word
                System.out.print("Enter the word: ");
                String word2 = sc.nextLine().toUpperCase();
                operation.removeWord(file, word2);
                break;
            case 3:
                // Translate a word
                System.out.print("Enter the word: ");
                String word3 = sc.nextLine().toUpperCase();
                operation.translate(file, word3);
                break;
            case 4:
                // Show all words
                operation.showAllWords(file);
                break;
            case 5:
                // Finish the operations
                System.out.println("Library operation is finished...");
                while (true) {
                    Main.menu();
                    int selection = Main.getInt();
                    Main.getUserSelection(selection);
                }
            default:
                System.out.println("Invalid selection!");
        }
    }
}
