package src.main;

import java.util.ArrayList;

/**
 * Represents a person with a name and amount paid.
 */
public class Person {
    private final String name;
    private double amtPaid;

    private static ArrayList<Person> peopleList = new ArrayList<>();

    public Person(String name, double amtPaid) {
        this.name = name;
        this.amtPaid = amtPaid;
    }

    @Override
    public String toString() {
        return name;
    }
}

