import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class manageAmount extends JFrame implements ActionListener {
    JTextField accountNumber;
    JTextField amount;
    JButton submit;
    String action;

    manageAmount(String action) {
        this.action = action;

        JPanel header = new JPanel();
        JLabel heading = new JLabel(action);
        heading.setFont(new Font(null, Font.BOLD, 20));
        header.add(heading);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 20, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        formPanel.add(new JLabel("Account Number:"));
        accountNumber = new JTextField(20);

        formPanel.add(accountNumber);
        if (action.equals("Withdraw Amount") || action.equals("Deposit Amount")) {
            if (action.equals("Withdraw Amount")) {
                formPanel.add(new JLabel("Withdraw Amount"));
            } else {
                formPanel.add(new JLabel("Deposit Amount"));
            }
            amount = new JTextField();
            formPanel.add(amount);
        }

        JPanel footer = new JPanel();
        submit = new JButton();
        submit.setText("Submit");
        submit.addActionListener(this);
        footer.add(submit);

        this.setLayout(new BorderLayout());
        this.add(header, BorderLayout.NORTH);
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
        if (e.getSource() == submit) {
            if (!checkDetails.checkAccNum(accountNumber)) {
                return;
            }
            BankDetails bankAcc = BankDetails.searchAcc(accountNumber.getText());

            if (bankAcc == null) {
                JOptionPane.showMessageDialog(null, "Account number doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (action.equals("Withdraw Amount") || action.equals("Deposit Amount")) {
                if (checkDetails.checkBal(amount)) {
                    if (action.equals("Withdraw Amount")) {
                        BankDetails.withdrawal(bankAcc, Integer.parseInt(amount.getText()));
                    } else {
                        BankDetails.deposit(bankAcc, Integer.parseInt(amount.getText()));
                    }
                    this.dispose();
                    new HomePage();
                } else {
                    return;
                }

            }
        }
    }
}
