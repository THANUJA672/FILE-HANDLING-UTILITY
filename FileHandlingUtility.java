import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileHandlingUtility {

    // Write data to a file
    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Data written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read data from a file
    public static void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Append data to a file
    public static void appendToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
            System.out.println("Data appended to " + fileName);
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    // Modify a specific line in the file
    public static void modifyLineInFile(String fileName, int lineNumber, String newContent) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            if (lineNumber > 0 && lineNumber <= lines.size()) {
                lines.set(lineNumber - 1, newContent);  // Modify the line (1-based index)
                Files.write(Paths.get(fileName), lines);
                System.out.println("Line " + lineNumber + " modified.");
            } else {
                System.out.println("Invalid line number.");
            }
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    // Main method to demonstrate the file operations
    public static void main(String[] args) {
        String fileName = "example.txt";  // Name of the file to operate on

        // 1. Write to the file
        writeToFile(fileName, "This is the first line of the text file.\n");

        // 2. Read from the file
        System.out.println("Reading file content:");
        readFromFile(fileName);

        // 3. Append to the file
        appendToFile(fileName, "This is an appended line.\n");

        // 4. Read after appending
        System.out.println("\nReading file content after appending:");
        readFromFile(fileName);

        // 5. Modify a line (e.g., modify the 1st line)
        modifyLineInFile(fileName, 1, "This is the modified first line.");

        // 6. Read after modifying
        System.out.println("\nReading file content after modification:");
        readFromFile(fileName);
    }
}
