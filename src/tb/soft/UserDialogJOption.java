package tb.soft;

import javax.swing.*;

public class UserDialogJOption extends UserDialogConsole implements IUserDialog {
    @Override
    public void printMessage(final String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void printInfoMessage(final String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String enterString(final String prompt) {
        return JOptionPane.showInputDialog(null, prompt, "Wprowadź", JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public char enterChar(final String prompt) {
        return super.enterChar(prompt);
    }

    @Override
    public int enterInt(final String prompt) {
        return super.enterInt(prompt);
    }

    @Override
    public float enterFloat(final String prompt) {
        return super.enterFloat(prompt);
    }

    @Override
    public double enterDouble(final String prompt) {
        return super.enterDouble(prompt);
    }

    @Override
    public void printErrorMessage(final String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
