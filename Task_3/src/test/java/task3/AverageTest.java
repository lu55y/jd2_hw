package task3;

import org.junit.Test;

import static org.junit.Assert.*;

public class AverageTest {

    @Test
    public void average() {
        Average average = new Average();
        double expected = 7;
        double actual = average.average(14);
        assertEquals(expected,actual,1e-3);
    }
}