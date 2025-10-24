package com.example;

import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILENAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Write a note");
            System.out.println("2. View all notes");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeNote(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        sc.close();
    }

    // Write a new note to file (appends)
    private static void writeNote(Scanner sc) {
        System.out.print("Write your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILENAME, true)) {
            fw.write(note + System.lineSeparator());
            System.out.println("Note saved.");
        } catch (IOException e) {
            System.out.println("Error saving note: " + e.getMessage());
        }
    }

    // Read and display all notes
    private static void readNotes() {
        System.out.println("\n--- All Notes ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count++ + ". " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Add a note first!");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
