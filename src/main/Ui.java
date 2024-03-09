package src.main;

public class Ui {
    /**
     * Returns a welcome message.
     */
    public String showWelcomeMsg() {
        return "Welcome to SmartSplit!";
    }

    /**
     * Returns break lines to show end of a command.
     */
    public String breakLines() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------------------------------------------------------------");
        sb.append(System.lineSeparator());
        sb.append("-------------------------------------------------------------------------------------------------");
        return sb.toString();
    }
}
