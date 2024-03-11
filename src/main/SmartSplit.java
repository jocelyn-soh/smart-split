package src.main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A smart bill splitter that splits the bill equally among a group of people with the least number of transactions.
 */
public class SmartSplit {

    public static ArrayList<Person> people = new ArrayList<>();

    /**
     * Runs the program.
     */
    public void run() {
        System.out.println(Ui.showWelcomeMsg());
        System.out.println(Ui.breakLines());
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine().strip();
        while (!cmd.equals("/split")) {
            getResponse(cmd);
            System.out.println(Ui.breakLines());
            cmd = sc.nextLine();
        }
        getResponse(cmd);
    }

    /**
     * Returns the response by SmartSplit
     * @param input from the user
     */
    public void getResponse(String input) {
        String response = Parser.parse(input);
        System.out.println(response);
    }

    public static void main(String[] args) {
        new SmartSplit().run();
    }
}


