import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class checkDetails {
    public static boolean verifyDetails(JTextField accountNumber, JTextField accountHolder, JRadioButton savingAccount,
            JRadioButton currentAccount, JTextField balance) {
        if (!(checkAccNum(accountNumber) && checkAccHol(accountHolder) && checkBal(balance))) {
            return false;
        }
        if (!savingAccount.isSelected() && !currentAccount.isSelected()) {
            JOptionPane.showMessageDialog(null, "Select Account Type", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean checkAccNum(JTextField accountNumber) {
        if (accountNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Account Number should not be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(accountNumber.getText());
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Account number should be in number format", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean checkAccHol(JTextField accountHolder) {
        if (accountHolder.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name should not be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String str = accountHolder.getText();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ' && !Character.isLetter(str.charAt(i))) {
                JOptionPane.showMessageDialog(null, "Name should not contain any number or special keywords", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public static boolean checkBal(JTextField balance) {
        if (balance.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Balance should not be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(balance.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Balance should be number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
