import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;  // For generating ID numbers like 000001, 000002...

        boolean addMore = true;
        while (addMore) {
            System.out.println("\nEnter data for new record:");

            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String id = String.format("%06d", idCounter); // Zero-padded 6-digit ID
            String email = SafeInput.getNonZeroLenString(in, "Enter Email");
            int yearOfBirth = SafeInput.getRangedInt(in, "Enter Year of Birth", 1900, 2025);

            String record = String.join(", ", firstName, lastName, id, email, String.valueOf(yearOfBirth));
            records.add(record);
            idCounter++;

            addMore = SafeInput.getYNConfirm(in, "Do you want to add another record?");
        }

        // Prompt for file name
        String fileName = SafeInput.getNonZeroLenString(in, "Enter name for CSV file (without extension)") + ".csv";

        // Save file in src directory
        String filePath = System.getProperty("user.dir") + "/src/" + fileName;
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String rec : records) {
                writer.write(rec + "\n");
            }
            System.out.println("\nFile saved successfully to: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
