import java.io.*;
import java.util.Scanner;

public class FileHandlingDemo {
    
    // 1. Create and Write to File
    public static void writeToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            System.out.println("✓ Successfully wrote to file: " + filename);
        } catch (IOException e) {
            System.out.println("✗ Error writing to file: " + e.getMessage());
        }
    }
    
    // 2. Read from File
    public static void readFromFile(String filename) {
        System.out.println("\n--- Reading from " + filename + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // 3. Append to File
    public static void appendToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(content);
            System.out.println("✓ Successfully appended to file: " + filename);
        } catch (IOException e) {
            System.out.println("✗ Error appending to file: " + e.getMessage());
        }
    }
    
    // 4. Copy File
    public static void copyFile(String source, String destination) {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            System.out.println("✓ File copied from " + source + " to " + destination);
        } catch (IOException e) {
            System.out.println("✗ Error copying file: " + e.getMessage());
        }
    }
    
    // 5. Delete File
    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.delete()) {
            System.out.println("✓ Deleted file: " + filename);
        } else {
            System.out.println("✗ Failed to delete file: " + filename);
        }
    }
    
    // 6. Get File Information
    public static void getFileInfo(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("\n--- File Information ---");
            System.out.println("File Name: " + file.getName());
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
        } else {
            System.out.println("File does not exist: " + filename);
        }
    }
    
    // 7. List Directory Contents
    public static void listDirectory(String directory) {
        File dir = new File(directory);
        if (dir.exists() && dir.isDirectory()) {
            System.out.println("\n--- Contents of " + directory + " ---");
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("[DIR]  " + file.getName());
                    } else {
                        System.out.println("[FILE] " + file.getName() + " (" + file.length() + " bytes)");
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist: " + directory);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== File Handling Demo ===\n");
        
        String filename = "sample.txt";
        
        // 1. Write to file
        writeToFile(filename, "Hello, this is line 1\nThis is line 2\nThis is line 3\n");
        
        // 2. Read from file
        readFromFile(filename);
        
        // 3. Append to file
        appendToFile(filename, "This is appended line 4\nThis is appended line 5\n");
        
        // 4. Read again to see changes
        readFromFile(filename);
        
        // 5. Copy file
        String copyFilename = "sample_copy.txt";
        copyFile(filename, copyFilename);
        
        // 6. Get file information
        getFileInfo(filename);
        
        // 7. List current directory
        listDirectory(".");
        
        // 8. Delete copied file
        deleteFile(copyFilename);
        
        // 9. Create directory
        File dir = new File("test_directory");
        if (dir.mkdir()) {
            System.out.println("✓ Directory created: test_directory");
        }
        
        // 10. Write to file in directory
        writeToFile("test_directory/test.txt", "File inside directory");
        readFromFile("test_directory/test.txt");
        
        System.out.println("\n=== File Handling Demo Completed ===");
    }
}