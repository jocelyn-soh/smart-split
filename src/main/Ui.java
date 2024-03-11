package src.main;

public class Ui {
    /**
     * Returns a welcome message.
     */
    public static String showWelcomeMsg() {
        return "Welcome to SmartSplit!";
    }

    /**
     * Returns break lines to show end of a command.
     */
    public static String breakLines() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------------------------------------------------------------");
        sb.append(System.lineSeparator());
        sb.append("-------------------------------------------------------------------------------------------------");
        return sb.toString();
    }

    public static String addToString(String cmd) {
        String[] nameAndAmt = Parser.parseAdd(cmd);
        String name = nameAndAmt[0].strip();
        double amtPaid = Double.parseDouble(nameAndAmt[1].strip());
        Person newPerson = new Person(name, amtPaid);
        SmartSplit.people.add(newPerson);
        return "Successfully added " + name + "'s expenses of $" + amtPaid + "!";
    }
}
