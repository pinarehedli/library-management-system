import java.util.Scanner;

public class Main {
    static final String EXTENSION = ".txt";
    static Scanner sc = new Scanner(System.in);
    static LibraryManager manager = new LibraryManager();

    public static void main(String[] args) {
        while (true) {
            menu();
            int selection = getInt();
            getUserSelection(selection);
        }
    }

    public static void menu() {
        System.out.println("\n--- Library Management System ---\n");
        System.out.println("1. Create a new library");
        System.out.println("2. Load the existing library");
        System.out.println("3. Remove a library");
        System.out.println("4. Show all libraries");
        System.out.println("5. Exit");
        System.out.print("Enter your selection: ");
    }

    public static void getUserSelection(int selection) {
        try {
            switch (selection) {
                case 1:
                    // Create a new library
                    System.out.print("Enter library name: ");
                    String name1 = sc.nextLine() + EXTENSION;
                    manager.createLibrary(name1);
                    break;
                case 2:
                    // Load the library
                    manager.loadLibrary();
                    break;
                case 3:
                    // Remove a library
                    System.out.print("Enter library name: ");
                    String name2 = sc.nextLine() + EXTENSION;
                    manager.remove(name2);
                    break;
                case 4:
                    // Show all libraries
                    manager.showLibraries();
                    break;
                case 5:
                    // Exit the program
                    System.out.println("Program is finished...");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection!");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}