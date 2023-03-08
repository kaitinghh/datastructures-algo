import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        String password = "cow";
        ILFShiftRegister r = getRegister(ShiftRegister.generateSeed(password).length, 0);
        int[] seed = ShiftRegister.generateSeed(password);
        r.setSeed(seed);
    }
//
//    /**
//     * Tests generate with simple example.
//     */
//    @Test
//    public void testGenerate1() {
//        ILFShiftRegister r = getRegister(9, 7);
//        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
//        r.setSeed(seed);
//        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
//        for (int i = 0; i < 10; i++) {
//            assertEquals("GenerateTest", expected[i], r.generate(3));
//        }
//    }
//
//    /**
//     * Tests register of length 1.
//     */
//    @Test
//    public void testOneLength() {
//        ILFShiftRegister r = getRegister(1, 0);
//        int[] seed = { 1 };
//        r.setSeed(seed);
//        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
//        for (int i = 0; i < 10; i++) {
//            assertEquals(expected[i], r.generate(3));
//        }
//    }


    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError1() { //test when shiftregister size not equal to seed size
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    //This should throw an error noting that the size of the ShiftRegister is not equal to the size of the seed. The
    //right way to test this case is to put in an input where the initialized ShiftRegister size is different
    //from the length of the seed (in this case ShiftRegister size is 4, while seed length/size is 7).

    @Test
    public void testError2() { //test when seed contains numbers that are not 0 or 1
        ILFShiftRegister r = getRegister(7, 1);
        int[] seed = { 1, 0, 0, 0, 1, 2, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    @Test
    public void testError3() { //test when tap is not between 0 and (size - 1)
        ILFShiftRegister r = getRegister(4, 4);
    }

}
