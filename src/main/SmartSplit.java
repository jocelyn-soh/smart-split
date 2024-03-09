package src.main;

/**
 * A smart bill splitter that splits the bill equally among a group of people with the least number of transactions.
 */
public class SmartSplit {
    private  Ui ui;

    public SmartSplit() {
        this.ui = new Ui();
    }

    /**
     * Runs the program.
     */
    public void run() {
        System.out.println(ui.showWelcomeMsg());
        System.out.println(ui.breakLines());
    }
    public static void main(String[] args) {
        new SmartSplit().run();
    }
}


