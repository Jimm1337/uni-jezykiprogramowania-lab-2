package tb.soft;

public interface IUserDialog {
    void printMessage(String message);

    void printInfoMessage(String message);

    void printErrorMessage(String message);

    void clearConsole();

    String enterString(String prompt);

    char enterChar(String prompt);

    int enterInt(String prompt);

    float enterFloat(String prompt);

    double enterDouble(String prompt);
}
