package src.test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import src.main.Parser;
import src.main.Person;
import src.main.SmartSplit;

import java.util.ArrayList;


public class ParserTest {

    private ArrayList<Person> people;

    @BeforeEach
    public void setUp() {
        // Initialize test data
        people = new ArrayList<>();
        people.add(new Person("Alice", 60.0));
        people.add(new Person("Bob", 120.0));
        people.add(new Person("Charlie", 30.0));
        SmartSplit.people = people;
    }

    @Test
    public void testDblToTwoDp() {
        assertEquals(10.12, Parser.dblToTwoDp(10.123456));
        assertEquals(10.00, Parser.dblToTwoDp(10));
        assertEquals(10.56, Parser.dblToTwoDp(10.555));
    }

    @Test
    public void testExpensePerPerson() {
        assertEquals(70.0, Parser.expensePerPerson());
    }

    @Test
    public void testCreateUnpaidArr() {
        ArrayList<Person> unpaidPpl = Parser.createUnpaidArr();
        assertEquals(3, unpaidPpl.size());
    }

}

