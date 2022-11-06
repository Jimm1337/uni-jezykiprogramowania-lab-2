package tb.soft;

import javax.swing.*;

public class UserDialogJOption extends UserDialogConsole implements IUserDialog {
    @Override
    public void printMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void printInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String enterString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt, "Wprowadź", JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public char enterChar(String prompt) {
        return super.enterChar(prompt);
    }

    @Override
    public int enterInt(String prompt) {
        return super.enterInt(prompt);
    }

    @Override
    public float enterFloat(String prompt) {
        return super.enterFloat(prompt);
    }

    @Override
    public double enterDouble(String prompt) {
        return super.enterDouble(prompt);
    }

    @Override
    public void printErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
