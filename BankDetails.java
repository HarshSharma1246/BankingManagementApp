import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        accounts.add(this);
    }

    public static BankDetails searchAcc(String accountNumber) {
        for (BankDetails account : accounts) {
            if (account.acc_no.equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static void modifyAccount(BankDetails bankAcc, String acc_name, String acc_type) {
        bankAcc.name = acc_name;
        bankAcc.acc_type = acc_type;
        JOptionPane.showMessageDialog(null, "Details Modified Successfully", "Success", JOptionPane.DEFAULT_OPTION);
    }

    public static void consumerDetails(BankDetails bankAcc) {
        JFrame accountDetails = new JFrame();
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        detailsPanel.add(new JLabel("Account Number:"));
        detailsPanel.add(new JLabel(bankAcc.acc_no));
        detailsPanel.add(new JLabel("Account Holder Name:"));
        detailsPanel.add(new JLabel(bankAcc.name));
        detailsPanel.add(new JLabel("Account Type:"));
        detailsPanel.add(new JLabel(bankAcc.acc_type));
        detailsPanel.add(new JLabel("Balance:"));
        detailsPanel.add(new JLabel(Long.toString(bankAcc.balance)));

        accountDetails.setLayout(new BorderLayout());
        accountDetails.add(new JLabel("Consumer Details"), BorderLayout.NORTH);
        accountDetails.add(detailsPanel, BorderLayout.CENTER);
        accountDetails.setTitle("Banking App");
        accountDetails.pack();
        accountDetails.setLocationRelativeTo(null);
        accountDetails.setVisible(true);
        accountDetails.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Create and display the new frame
                accountDetails.dispose();
                new HomePage();
            }
        });
    }

    // method to deposit money
    public static void deposit(BankDetails bankAcc, long bal) {
        bankAcc.balance += bal;
        JOptionPane.showMessageDialog(null, "!! Amount added !! \nUpdated Balance: " + bankAcc.balance, "Success",
                JOptionPane.DEFAULT_OPTION);
    }

    // method to withdraw money
    public static void withdrawal(BankDetails bankAcc, long bal) {
        if (bankAcc.balance < bal) {
            JOptionPane.showMessageDialog(null, "Account doesn't have enough balance", "Low Balance",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            bankAcc.balance -= bal;
            JOptionPane.showMessageDialog(null, "Amount successfully withdrawn\n Updated balance: " + bankAcc.balance,
                    "Success", JOptionPane.DEFAULT_OPTION);
        }
    }

    public static void balanceEnquiry(BankDetails bankAcc) {
        JOptionPane.showMessageDialog(null, "Balance: " + bankAcc.balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
        new HomePage();
    }

    public static void closeAccount(BankDetails bankAcc) {
        int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this account?", "Confirmation",
                JOptionPane.OK_CANCEL_OPTION);
        if (res == 0) {
            JOptionPane.showMessageDialog(null, "Successfully Removed", "Account Deleted", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "Cancelled", "", JOptionPane.DEFAULT_OPTION);
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(bankAcc)) {
                accounts.remove(i);
                break;
            }
        }
        bankAcc = null;
        new HomePage();
    }

    public static void addFile(File myFile) {
        try (FileReader fileReader = new FileReader(myFile)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while (bufferedReader.ready()) {
                    String acc_no = "";
                    String name = "";
                    String type = "";
                    long balance = 0;
                    String data[] = bufferedReader.readLine().split(",");
                    acc_no = data[0];
                    name = data[1];
                    type = data[2];
                    balance = Long.parseLong(data[3]);
                    new BankDetails(acc_no, name, type, balance);
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (NumberFormatException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void saveDetails(File myFile) {
        try (FileWriter fileWriter = new FileWriter(myFile)) {
            for (BankDetails account : accounts) {
                String detail = account.acc_no + "," + account.name + "," + account.acc_type + ","
                        + Long.toString(account.balance);
                fileWriter.write(detail + "\n");
            }
            fileWriter.close();
            System.out.println("Data is saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
