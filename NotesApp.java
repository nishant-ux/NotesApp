import java.io.*;
import java.util.*;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void addNote(String note) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(note);
            System.out.println("✅ Note added successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing note: " + e.getMessage());
        }
    }
    public static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n📒 Your Notes:");
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("⚠ No notes found. Start adding some!");
        } catch (IOException e) {
            System.out.println("❌ Error reading notes: " + e.getMessage());
        }
    }
    public static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) { // overwrite mode
            fw.write("");
            System.out.println("🧹 All notes cleared!");
        } catch (IOException e) {
            System.out.println("❌ Error clearing notes: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== 📝 Notes App =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear All Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your note: ");
                    String note = sc.nextLine();
                    addNote(note);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    clearNotes();
                    break;
                case 4:
                    System.out.println("👋 Exiting Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
