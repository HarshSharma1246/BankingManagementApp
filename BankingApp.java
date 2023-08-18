import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class BankDetails {
    private String acc_no;
    private String name;
    private String acc_type;
    private long balance;
    Scanner sc = new Scanner(System.in);

    // method to open new account
    public BankDetails(String acc_no, String name, String acc_type, long balance) {
        this.acc_no = acc_no;
        this.name = name;
        this.acc_type = acc_type;
        this.balance = balance;
    }

    public void openAccount() {
        System.out.print("Enter Account No: ");
        acc_no = sc.next();
        System.out.print("Enter Account type: ");
        acc_type = sc.next();
        System.out.print("Enter Name: ");
        name = sc.next();
        System.out.print("Enter Balance: ");
        balance = sc.nextLong();
    }

    public void modify(String n) {
        if (n.equals("1")) {
            System.out.println("Enter new Account Number: ");
            acc_no = sc.next();
        } else if (n.equals("2")) {
            System.out.println("Enter new Name: ");
            name = sc.next();
        } else if (n.equals("3")) {
            System.out.println("Enter new Account type: ");
            acc_type = sc.next();
        } else {
            System.out.println("Invalid choice!!");
        }
    }

    public String details(int i) {
        return (i + ".         " + acc_no + "         " + name + "         " + acc_type + "         " + balance);
    }

    // method to display account details
    public void showAccount(int i) {
        System.out.println(
                i + ".         " + acc_no + "         " + name + "         " + acc_type + "         " + balance);
    }

    // method to deposit money
    public void deposit() {
        long amt;
        System.out.println("Enter the amount you want to deposit: ");
        amt = sc.nextLong();
        balance = balance + amt;
    }

    // method to withdraw money
    public void withdrawal() {
        long amt;
        System.out.println("Enter the amount you want to withdraw: ");
        amt = sc.nextLong();
        if (balance >= amt) {
            balance = balance - amt;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!");
        }
    }

    public void balanceEnquiry() {
        System.out.println(acc_no + " : " + balance);
    }

    // method to search an account number
    public boolean search(String ac_no) {
        if (acc_no.equals(ac_no)) {
            return (true);
        }
        return (false);
    }
}

public class BankingApp {
    public static void main(String arg[]) {

        System.out.println("******* Welcome to Bank Management System *******");
        Scanner sc = new Scanner(System.in);
        // Home window

        ArrayList<BankDetails> accounts = new ArrayList<>();
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
                    newAccount.openAccount();
                    accounts.add(newAccount);
                    System.out.println("Account Created....:)");
                    System.out.println();
                    System.out.println("Enter any key...");
                    sc.next();
                    break;
                case 2:
                    System.out.println("Enter the account number for money deposition: ");
                    ac_no = sc.next();
                    boolean found = false;
                    for (BankDetails account : accounts) {
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
                    sc.next();
                    break;
                case 3:
                    System.out.print("Enter Account no. for Money Withdrawal : ");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : accounts) {
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
                    sc.next();
                    break;
                case 4:
                    System.out.print("Enter Account No for balance enquiry: ");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : accounts) {
                        if (account.search(ac_no)) {
                            account.balanceEnquiry();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                        System.out.println();
                    }
                    System.out.println("Enter any key....");
                    sc.next();
                    break;
                case 5:
                    System.out.println("Enter the Account number of the customer");
                    ac_no = sc.next();
                    found = false;
                    System.out.println("S/No    Account no.         name         Type         Balance");
                    System.out.println("==============================================================");
                    for (BankDetails account : accounts) {
                        if (account.search(ac_no)) {
                            found = true;
                            account.showAccount(1);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("This account number is not valid");
                    }
                    System.out.println("Enter any key....");
                    sc.next();
                    break;
                case 6:
                    System.out.println("Here is the list of all the accounts: \n");
                    System.out.println("S/No    Account no.         name         Type         Balance");
                    System.out.println("==============================================================");
                    int i = 1;
                    for (BankDetails account : accounts) {
                        account.showAccount(i++);
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Enter any key....");
                    sc.next();
                    break;
                case 7:
                    System.out.println("Enter the Account no. which you want to modify :");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : accounts) {
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
                    sc.next();
                    break;
                case 8:
                    System.out.println("Enter the Account no. which you want to close :");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : accounts) {
                        if (account.search(ac_no)) {
                            accounts.remove(account);
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
                    sc.next();
                    break;
                case 9:
                    System.out.println("See you soon...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid number");
                    System.out.println();
                    System.out.println("Enter any key....");
            }
        } while (running);
        File myFile = new File(
                "AccountData.txt");

        try {
            FileWriter fileWriter = new FileWriter(myFile);
            fileWriter.write("S/No    Account no.         name         Type         Balance\n" +
                    "==============================================================\n");
            int i = 1;
            for (BankDetails account : accounts) {
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
