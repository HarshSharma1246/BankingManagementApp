import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class BankingApp {
    public static void main(String arg[]) {
        File myFile = new File(
                "C:\\Users\\91810\\OneDrive\\Documents\\Programing\\Projects\\BankingApp\\AccountData.txt");
        BankDetails.addFile(myFile); // Adding the Saved Account data file
        Scanner sc = new Scanner(System.in);
        // Home window
        System.out.println("******* Welcome to Bank Management System *******");
        boolean running = true;
        do {
            System.out.println("---------Main Menu------\n");
            System.out.println("1. Create a new Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Consumer Details");
            System.out.println("6. All Account Holders Details");
            System.out.println("7. Modify Account Details");
            System.out.println("8. Close an Account");
            System.out.println("9. Exit");
            System.out.println();
            System.out.println("Enter your choice: ");
            int n = sc.nextInt();
            String ac_no;
            switch (n) {
                case 1:
                    BankDetails newAccount = new BankDetails("", "", "", 0);
                    newAccount.openAccount(newAccount);
                    System.out.println("Account Created....:)");
                    System.out.println();
                    System.out.println("Enter any key...");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 2:
                    System.out.println("Enter the account number for money deposition: ");
                    ac_no = sc.next();
                    boolean found = false;
                    for (BankDetails account : BankDetails.accounts) {
                        if (account.search(ac_no)) {
                            found = true;
                            account.deposit();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                        System.out.println();
                    }
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 3:
                    System.out.print("Enter Account no. for Money Withdrawal : ");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : BankDetails.accounts) {
                        if (account.search(ac_no)) {
                            found = true;
                            account.withdrawal();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                        System.out.println();
                    }
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 4:
                    System.out.print("Enter Account No for balance enquiry: ");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : BankDetails.accounts) {
                        if (account.search(ac_no)) {
                            account.balanceEnquiry();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                        System.out.println();
                    }
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 5:
                    System.out.println("Enter the Account number of the customer");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : BankDetails.accounts) {
                        if (account.search(ac_no)) {
                            found = true;
                            System.out.println("S/No     Account no.         name         Type         Balance");
                            System.out.println("==================================================================");
                            account.showAccount(1);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("This account number is not valid");
                    }
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 6:
                    System.out.println("Here is the list of all the BankDetails.accounts: \n");
                    System.out.println("S/No     Account no.         name         Type         Balance");
                    System.out.println("==============================================================");
                    int i = 1;
                    for (BankDetails account : BankDetails.accounts) {
                        account.showAccount(i++);
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 7:
                    System.out.println("Enter the Account no. which you want to modify :");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : BankDetails.accounts) {
                        if (account.search(ac_no)) {
                            found = true;
                            System.out.println("Which detail to modify");
                            System.out.println("1. Account number");
                            System.out.println("2. Name");
                            System.out.println("3. Account Type");
                            String choice = sc.next();
                            account.modify(choice);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("This account number is invalid!!");
                    }
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 8:
                    System.out.println("Enter the Account no. which you want to close :");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : BankDetails.accounts) {
                        if (account.search(ac_no)) {
                            BankDetails.accounts.remove(account);
                            System.out.println("Account removed successfully...:(");
                            System.out.println();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                        System.out.println();
                    }
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                case 9:
                    System.out.println("See you soon...");
                    sc.nextLine();
                    sc.nextLine();
                    running = false;
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;
                default:
                    System.out.println("Invalid number");
                    System.out.println();
                    System.out.println("Enter any key....");
                    sc.nextLine();
                    sc.nextLine();
            }
        } while (running);
        try {
            FileWriter fileWriter = new FileWriter(myFile);
            fileWriter.write("S/No     Account no.         name         Type         Balance\n" +
                    "=====================================================================\n");
            int i = 1;
            for (BankDetails account : BankDetails.accounts) {
                fileWriter.write(account.details(i) + "\n");
                i++;
            }
            fileWriter.close();
            System.out.println("Account data saved to AccountData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}