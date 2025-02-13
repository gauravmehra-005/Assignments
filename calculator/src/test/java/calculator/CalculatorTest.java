package calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    void testAddition() {
        assertEquals(30, calc.add(10, 20));
    }

    @Test
    void testSubtraction() {
        assertEquals(-10, calc.sub(10, 20));
    }

    @Test
    void testMultiplication() {
        assertEquals(200, calc.mul(10, 20));
    }
}
