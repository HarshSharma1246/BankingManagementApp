import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class modifyAccountDetails extends JFrame implements ActionListener {
    JTextField accountNumber;
    JTextField accountName;
    JRadioButton savingAccount;
    JRadioButton currentAccount;
    JButton button;
    BankDetails bankAcc;

    modifyAccountDetails(BankDetails bankAcc, String acc_no) {
        this.bankAcc = bankAcc;

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        formPanel.add(new JLabel("Account Number:"));
        JTextField accNo = new JTextField(20);
        accNo.setText(acc_no);
        accNo.setEditable(false);
        formPanel.add(accNo);

        formPanel.add(new JLabel("Account Holder Name:"));
        accountName = new JTextField(20);
        formPanel.add(accountName);

        formPanel.add(new JLabel("Account Type:"));
        ButtonGroup group = new ButtonGroup();
        savingAccount = new JRadioButton("Saving");
        currentAccount = new JRadioButton("Current");
        JPanel radioPanel = new JPanel();
        group.add(savingAccount);
        group.add(currentAccount);
        radioPanel.add(savingAccount);
        radioPanel.add(currentAccount);
        formPanel.add(radioPanel);

        button = new JButton("Submit");
        button.addActionListener(this);
        JPanel footer = new JPanel();
        footer.add(button);

        this.setLayout(new BorderLayout());
        this.add(new JLabel("Modify Consumer Details"), BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

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
        if (e.getSource() == button) {
            if (!checkDetails.checkAccHol(accountName)) {
                return;
            } else {
                if (savingAccount.isSelected()) {
                    BankDetails.modifyAccount(bankAcc, accountName.getText(), "Saving");
                } else if (currentAccount.isSelected()) {
                    BankDetails.modifyAccount(bankAcc, accountName.getText(), "Current");
                } else {
                    JOptionPane.showMessageDialog(null, "Select Account Type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            this.dispose();
            new HomePage();
        }
    }
}
