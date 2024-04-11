import java.io.File;

public class BankingApp {
    static File myFile;

    public static void main(String arg[]) {
        myFile = new File(
                "C:\\Users\\91810\\OneDrive\\Documents\\Programing\\Projects\\BankingApp\\AccountData.txt");
        BankDetails.addFile(myFile); // Adding the Saved Account data file
        new HomePage(); // Opening the interface
        BankDetails.displayFileContents(myFile);
    }
}