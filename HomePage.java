import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage extends JFrame implements ActionListener {
    JButton newAcc;
    JButton moneyDep;
    JButton moneyWith;
    JButton accBalance;
    JButton consumerDetails;
    JButton modifyDetails;
    JButton closeAcc;
    JButton exit;

    HomePage() {
        this.setTitle("Banking App");

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("***Welcome to Banking Management System***");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        topPanel.add(label);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        buttonPanel.setLayout(new GridLayout(4, 2, 50, 20));
        newAcc = new JButton("New Account");
        newAcc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        moneyDep = new JButton("Deposit Money");
        moneyDep.setFont(new Font("Times New Roman", Font.BOLD, 20));
        moneyWith = new JButton("Withdraw Money");
        moneyWith.setFont(new Font("Times New Roman", Font.BOLD, 20));
        accBalance = new JButton("Check Account Balance");
        accBalance.setFont(new Font("Times New Roman", Font.BOLD, 20));
        consumerDetails = new JButton("View Consumer Details");
        consumerDetails.setFont(new Font("Times New Roman", Font.BOLD, 20));
        modifyDetails = new JButton("Modify Account Details");
        modifyDetails.setFont(new Font("Times New Roman", Font.BOLD, 20));
        closeAcc = new JButton("Close Account");
        closeAcc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman", Font.BOLD, 20));

        newAcc.addActionListener(this);
        moneyDep.addActionListener(this);
        moneyWith.addActionListener(this);
        accBalance.addActionListener(this);
        consumerDetails.addActionListener(this);
        modifyDetails.addActionListener(this);
        closeAcc.addActionListener(this);
        exit.addActionListener(this);

        buttonPanel.add(newAcc);
        buttonPanel.add(moneyDep);
        buttonPanel.add(moneyWith);
        buttonPanel.add(accBalance);
        buttonPanel.add(consumerDetails);
        buttonPanel.add(modifyDetails);
        buttonPanel.add(closeAcc);
        buttonPanel.add(exit);

        this.setLayout(new BorderLayout(10, 20));
        this.add(topPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newAcc) {
            new NewAccount();
        } else if (e.getSource() == moneyDep) {
            new manageAmount("Deposit Amount");
        } else if (e.getSource() == moneyWith) {
            new manageAmount("Withdraw Amount");
        } else if (e.getSource() == accBalance) {
            new getByAccNo("Balance Enquiry");
        } else if (e.getSource() == consumerDetails) {
            new getByAccNo("Consumer Details");
        } else if (e.getSource() == modifyDetails) {
            new getByAccNo("Modify Account Details");
        } else if (e.getSource() == closeAcc) {
            new getByAccNo("Close Account");
        } else if (e.getSource() == exit) {
            BankDetails.saveDetails(BankingApp.myFile);
            System.exit(0);
        }
        this.dispose();
    }

}
