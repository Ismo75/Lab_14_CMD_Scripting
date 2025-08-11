import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScan {

    public static void main(String[] args) {
        File fileToScan = null;

        // If a single cmd-line arg is provided, use it as the file name.
        if (args.length >= 1 && args[0] != null && !args[0].isBlank()) {
            // Try the arg as-is (absolute or relative)
            File candidate = new File(args[0]);
            if (!candidate.exists() || !candidate.isFile()) {
                // If not found, try inside src/
                candidate = new File("src", args[0]);
            }
            if (candidate.exists() && candidate.isFile()) {
                fileToScan = candidate;
            } else {
                System.err.println("Error: File not found (tried: \"" + args[0] + "\" and \"src/" + args[0] + "\").");
                System.exit(1);
            }
        } else {
            // No argument -> open JFileChooser starting in src/
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src"));
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                fileToScan = chooser.getSelectedFile();
            } else {
                System.out.println("No file was selected.");
                System.exit(0);
            }
        }

        // Scan and report
        scanAndReport(fileToScan);


    private static void scanAndReport(File selectedFile) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        System.out.println("Reading file: " + selectedFile.getName());
        System.out.println("------------------------------------------------");

        try (Scanner fileScanner = new Scanner(selectedFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line); // Echo the line to screen
                lineCount++;

                // Count words
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    String[] words = trimmed.split("\\s+");
                    wordCount += words.length;
                }

                // Count characters (including spaces)
                charCount += line.length();
            }

            // Summary report
            System.out.println("\n=== File Summary Report ===");
            System.out.println("File name: " + selectedFile.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            System.exit(1);
        }
    }
}