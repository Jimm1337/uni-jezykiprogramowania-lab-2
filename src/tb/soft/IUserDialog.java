package tb.soft;

public interface IUserDialog {
    void printMessage(final String message);

    void printInfoMessage(final String message);

    void printErrorMessage(final String message);

    void clearConsole();

    String enterString(final String prompt);

    char enterChar(final String prompt);

    int enterInt(final String prompt);

    float enterFloat(final String prompt);

    double enterDouble(final String prompt);
}
