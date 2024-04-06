import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class BankDetails {
    private String acc_no;
    private String name;
    private String acc_type;
    private long balance;
    Scanner sc = new Scanner(System.in);
    static ArrayList<BankDetails> accounts = new ArrayList<>();

    // method to open new account
    public BankDetails(String acc_no, String name, String acc_type, long balance) {
        this.acc_no = acc_no;
        this.name = name;
        this.acc_type = acc_type;
        this.balance = balance;
    }

    public void openAccount(BankDetails newAccount) {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
        System.out.print("Enter Account No: ");
        boolean rightAcc = false;
        String ac_no = sc.next();

        while (true) {
            while (!rightAcc) {
                try {
                    Integer.parseInt(ac_no);
                    rightAcc = true;
                } catch (Exception e) {
                    System.out.println("Account number should be in number format. Type the valid number again");
                    ac_no = sc.next();
                }
            }
            boolean found = false;
            for (BankDetails b : accounts) {
                if (b.acc_no.equals(ac_no)) {
                    System.out
                            .println("This Account number is already present. Please provide some alternative number:");
                    found = true;
                }
            }
            if (found) {
                ac_no = sc.next();
                rightAcc = false;
            } else
                break;
        }
        this.acc_no = ac_no;
        System.out.print("Enter Account type (saving/current): ");
        acc_type = sc.next();
        while (!acc_type.toLowerCase().equals("saving") && !acc_type.toLowerCase().equals("current")) {
            System.out.println("Please choose correct Option\n\nEnter Account type (saving/current):");
            acc_type = sc.next();
        }
        sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Balance: ");
        boolean rightBalance = false;
        while (!rightBalance)
            try {
                this.balance = sc.nextLong();
                rightBalance = true;
            } catch (Exception e) {
                System.out.print("Balance should be in number format!! Try again...\n\nEnter Balance: ");
                sc.nextLine();
            }
        accounts.add(this);
    }

    public void modify(String n) {
        if (n.equals("1")) {
            System.out.print("Enter Account No: ");
            String ac_no = sc.next();
            while (true) {
                boolean found = false;
                for (BankDetails b : accounts) {
                    if (b.acc_no.equals(ac_no)) {
                        System.out.println(
                                "This Account number is already present. Please provide some alternative number:");
                        found = true;
                    }
                }
                if (found) {
                    ac_no = sc.next();
                } else
                    break;
            }
            this.acc_no = ac_no;
        } else if (n.equals("2")) {
            System.out.println("Enter new Name: ");
            this.name = sc.next();
        } else if (n.equals("3")) {
            System.out.println("Enter new Account type: ");
            acc_type = sc.next();
            while (!acc_type.toLowerCase().equals("saving") && !acc_type.toLowerCase().equals("current")) {
                System.out.println("Please choose correct Option\n\nEnter Account type (saving/current):");
                this.acc_type = sc.next();
            }
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
        long amt = 0;
        System.out.println("Enter the amount you want to deposit: ");
        boolean rightBalance = false;
        while (!rightBalance)
            try {
                amt = sc.nextLong();
                rightBalance = true;
            } catch (Exception e) {
                System.out.print(
                        "Amount should be in number format!! Try again...\n\nEnter the amount you want to deposit:");
                sc.nextLine();
            }
        System.out.println("Successfully Added!!");
        balance = balance + amt;
    }

    // method to withdraw money
    public void withdrawal() {
        System.out.println("Enter the amount you want to withdraw: ");
        long amt = 0;
        boolean rightBalance = false;
        while (!rightBalance)
            try {
                amt = sc.nextLong();
                rightBalance = true;
            } catch (Exception e) {
                System.out.print(
                        "Amount should be in number format!! Try again...\n\nEnter the amount you want to withdraw:");
                sc.nextLine();
            }
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

    public boolean search(String ac_no) {
        if (acc_no.equals(ac_no))
            return true;
        return (false);
    }

    public static void addFile(File myFile) {
        try (FileReader fileReader = new FileReader(myFile)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                bufferedReader.readLine();
                bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    String acc_no = "";
                    String name = "";
                    String type = "";
                    long balance = 0;
                    String data[] = bufferedReader.readLine().split("         ");
                    acc_no = data[1];
                    name = data[2];
                    type = data[3];
                    balance = Integer.parseInt(data[4]);
                    BankDetails b = new BankDetails(acc_no, name, type, balance);
                    accounts.add(b);
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (NumberFormatException | IOException e1) {
            e1.printStackTrace();
        }
    }
}
