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

public class getByAccNo extends JFrame implements ActionListener {
    String action;
    JTextField accountNumber;
    JButton button;

    getByAccNo(String action) {
        this.action = action;
        JLabel heading = new JLabel(action);
        heading.setFont(new Font(null, Font.BOLD, 20));
        JPanel header = new JPanel();
        header.add(heading);

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.accountNumber = new JTextField(20);
        JLabel label = new JLabel("Account Number:");
        formPanel.add(label);
        formPanel.add(accountNumber);

        JPanel footer = new JPanel();
        button = new JButton("Submit");
        button.addActionListener(this);
        footer.add(button);

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
        if (e.getSource() == button) {
            if (!checkDetails.checkAccNum(accountNumber)) {
                return;
            }
            BankDetails bankAcc = BankDetails.searchAcc(accountNumber.getText());

            if (bankAcc == null) {
                JOptionPane.showMessageDialog(null, "Account number doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (action.equals("Balance Enquiry")) {
                BankDetails.balanceEnquiry(bankAcc);
            } else if (action.equals("Consumer Details")) {
                BankDetails.consumerDetails(bankAcc);
            } else if (action.equals("Modify Account Details")) {
                new modifyAccountDetails(bankAcc, accountNumber.getText());
            } else {
                BankDetails.closeAccount(bankAcc);
            }
            this.dispose();

        }
    }
}
