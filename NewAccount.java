import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewAccount extends JFrame implements ActionListener {
    JTextField accountNumber;
    JTextField accountHolder;
    JTextField balance;
    JRadioButton savingAccount;
    JRadioButton currentAccount;
    JButton openButton;

    public NewAccount() {
        JLabel heading = new JLabel("Open Account");
        JPanel topPanel = new JPanel();
        topPanel.add(heading);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        accountNumber = new JTextField(20);
        JLabel label = new JLabel("Account Number:");
        formPanel.add(label);
        formPanel.add(accountNumber);

        accountHolder = new JTextField(20);
        label = new JLabel("Account Holder Name:");
        formPanel.add(label);
        formPanel.add(accountHolder);

        JPanel radioPanel = new JPanel();
        JLabel accountType = new JLabel("Account Type:");
        formPanel.add(accountType);
        ButtonGroup group = new ButtonGroup();
        savingAccount = new JRadioButton("Saving");
        currentAccount = new JRadioButton("Current");
        group.add(savingAccount);
        group.add(currentAccount);
        radioPanel.add(savingAccount);
        radioPanel.add(currentAccount);
        formPanel.add(radioPanel);

        balance = new JTextField(20);
        label = new JLabel("Balance:");
        formPanel.add(label);
        formPanel.add(balance);

        openButton = new JButton("Submit");
        openButton.addActionListener(this);
        JPanel footer = new JPanel();
        footer.add(openButton);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);

        this.setTitle("Banking App");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new HomePage();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            if (checkDetails.verifyDetails(accountNumber, accountHolder, savingAccount, currentAccount,
                    balance)) {
                if (BankDetails.searchAcc(accountNumber.getText()) != null) {
                    JOptionPane.showMessageDialog(null, "Account number Already exist", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (savingAccount.isSelected()) {
                    new BankDetails(accountNumber.getText(), accountHolder.getText(), "Saving",
                            Integer.parseInt(balance.getText()));
                    JOptionPane.showMessageDialog(null, "Account create successfully", "Account created",
                            JOptionPane.DEFAULT_OPTION);
                } else {
                    new BankDetails(accountNumber.getText(), accountHolder.getText(), "Current",
                            Integer.parseInt(balance.getText()));
                    JOptionPane.showMessageDialog(null, "Account create successfully", "Account created",
                            JOptionPane.DEFAULT_OPTION);
                }
                this.dispose();
                new HomePage();
            }
        }
    }
}
