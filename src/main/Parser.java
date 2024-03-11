package src.main;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    public static double dblToTwoDp (double untruncatedDbl) {
        return Double.parseDouble(String.format("%.2f", untruncatedDbl));
    }

    /**
     * Parses the commands entered by the user.
     * @param cmd A String
     */
    public static String parse(String cmd) {
        String response = "";
        if (cmd.startsWith("/add")) {
            response = Ui.addToString(cmd);
        } else if (cmd.equals("/split")) {
            response = Ui.splitToString();
        }
        return response;
    }

    /**
     * Parses the add command to obtain each parameter.
     * @param cmd String of the add command
     * @return A String[] consisting of the name of the person and amount he/she paid.
     */
    public static String[] parseAdd(String cmd) {
        String[] splitCmd = cmd.split("/add ");
        return splitCmd[1].split(": ");
    }

    /**
     * Returns the average expense per person based on the total expense of everyone.
     * @return A double
     */
    public static double expensePerPerson() {
        double totalExpenses = 0;
        for (Person person : SmartSplit.people) {
            totalExpenses += person.getAmtPaid();
        }
        double aveExpense = totalExpenses / SmartSplit.people.size();
        return dblToTwoDp(aveExpense);
    }

    /**
     * Subtracts the expense per person from each person's amount paid.
     */
    public static void subtractExpensePerPerson() {
        double expensePerPerson = expensePerPerson();
        for (Person person : SmartSplit.people) {
            double amtPaidExtra =  dblToTwoDp(person.getAmtPaid() - expensePerPerson);
            person.setAmtPaid(amtPaidExtra);
        }
    }

    /**
     * Sorts the ArrayList based on the amount paid in ascending order.
     * @param arr An ArrayList<Person>
     */
    public static void sortByAmt(ArrayList<Person> arr) {
        arr.sort(Comparator.comparingDouble(Person::getAmtPaid));
    }

    /**
     * Creates an ArrayList<Person> of the people who still owe others money or others owe them money.
     * @return unpaidPpl, an ArrayList<Person>
     */
    public static ArrayList<Person> createUnpaidArr() {
        ArrayList<Person> unpaidPpl = new ArrayList<>();
        for (Person person : SmartSplit.people) {
            if (person.getAmtPaid() != 0) {
                unpaidPpl.add(person);
            }
        }
        return unpaidPpl;
    }

    /**
     * Updates the unpaidPpl ArrayList after a transaction is made. If the debtor or creditor has no more outstanding
     * transactions, he is removed from unpaidPpl. Otherwise, his amount paid will be updated based on the transaction.
     * @param unpaidPpl The ArrayList<Person> of unpaidPpl before the transaction
     * @param amtToTransfer A double
     * @param debtor A Person
     * @param creditor A Person
     * @return The ArrayList<Person> of unpaidPpl after the transaction
     */
    public static ArrayList<Person> updateUnpaidPpl(ArrayList<Person> unpaidPpl, double amtToTransfer, Person debtor,
                                                    Person creditor) {
        double debtorNewAmt = dblToTwoDp(debtor.getAmtPaid() + amtToTransfer);
        double creditorNewAmt = dblToTwoDp(creditor.getAmtPaid() - amtToTransfer);

        if (creditorNewAmt == 0) { // delete creditor as he has no outstanding transactions
            unpaidPpl.remove(unpaidPpl.size() - 1);
        } else { // update the amount paid for the creditor
            creditor.setAmtPaid(dblToTwoDp(creditor.getAmtPaid() - amtToTransfer));
        }
        if (debtorNewAmt == 0) { // delete debtor as he has no outstanding transactions
            unpaidPpl.remove(0);
        } else { // update the amount paid for the debtor
            debtor.setAmtPaid(dblToTwoDp(debtor.getAmtPaid() + amtToTransfer));
        }
        return unpaidPpl;
    }

    public static double amtToTransfer(int size, Person debtor, Person creditor) {
        double amt;
        if (size == 2) {
            amt = Math.max(Math.abs(debtor.getAmtPaid()), Math.abs(creditor.getAmtPaid()));
        } else {
            amt = Math.min(Math.abs(debtor.getAmtPaid()), Math.abs(creditor.getAmtPaid()));
        }
        return dblToTwoDp(amt);
    }

    /**
     * Parses the split command to obtain the list of transactions when the bill is split.
     * @return An ArrayList<String> of transactions
     */
    public static ArrayList<String> parseSplit() {
        subtractExpensePerPerson();
        ArrayList<Person> unpaidPpl = createUnpaidArr();
        sortByAmt(unpaidPpl);
        ArrayList<String> transactions = new ArrayList<>();
        while (unpaidPpl.size() > 1) {
            int firstIdx = 0;
            int lastIdx = unpaidPpl.size() - 1;
            Person debtor = unpaidPpl.get(firstIdx);
            Person creditor = unpaidPpl.get(lastIdx);
            double amtToTransfer = amtToTransfer(unpaidPpl.size(), debtor, creditor);
            transactions.add(debtor + " pays " + creditor + " $" + amtToTransfer);
            unpaidPpl = updateUnpaidPpl(unpaidPpl, amtToTransfer, debtor, creditor);
            if (unpaidPpl.size() > 1) {
                sortByAmt(unpaidPpl);
            } else {
                break;
            }
        }
        return transactions;
    }
}
