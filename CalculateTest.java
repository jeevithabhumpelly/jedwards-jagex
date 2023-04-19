import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculateTest {
     
    @Test
    public void testCalculate1() {
        // Test valid input expressions
        assertEquals("7", Calculate1.calculate("2 + 5"));
        assertEquals("5", Calculate1.calculate("8 - 3"));
        assertEquals("20", Calculate1.calculate("5 * 4"));
        assertEquals("4", Calculate1.calculate("8 / 2"));
        assertEquals("16", Calculate1.calculate("4 ^ 2"));
    } 


    @Test
    public void testCalculate2() {
        // Test valid input expressions
        assertEquals("7", Calculate2.calculate("1 + 2 * 3"));
        assertEquals("9", Calculate2.calculate("(1 + 2) * 3"));
        assertEquals("19", Calculate2.calculate("6 + 3 - 2 + 12"));
        assertEquals("53", Calculate2.calculate("2 * 15 + 23"));
        assertEquals("1", Calculate2.calculate("10 - 3 ^ 2"));
    }

    
    @Test
    public void testCalculate3() {
        assertEquals("-18", Calculate3.calculate("(-20 * 1.8) / 2"));
        assertEquals("3.5", Calculate3.calculate("3.5 * 1"));
        assertEquals("-77", Calculate3.calculate("- 53 + - 24"));
        assertEquals("3.333", Calculate3.calculate("10 / 3"));
        assertEquals("-54.315", Calculate3.calculate("-12.315 - 42"));
    }
}
