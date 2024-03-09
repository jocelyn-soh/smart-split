package src.main;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the commands entered by the user.
     * @param cmd String
     */
    public static String parse(String cmd) {
        String response = "";
        if (cmd.startsWith("/add")) {
            response = Ui.addToString(cmd);
        }
        return response;
    }

    public static String[] parseAdd(String cmd) {
        String[] splitCmd = cmd.split("/add ");
        return splitCmd[1].split(": ");
    }
}
