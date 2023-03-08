///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

import java.lang.Math;
import java.util.ArrayList;

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    int[] ShiftRegister;
    int tap;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        if (tap >= 0 && tap <= size - 1) {
            this.ShiftRegister = new int[size];
            this.tap = tap;
        }
        else {
            System.out.println("Error: invalid tap"); //if tap is not between 0 and size - 1
        }

    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        int n = 0;
        if (ShiftRegister.length != seed.length) { //if shiftregister size not equal to seed size
            System.out.println("Error: ShiftRegister size not equal to seed size");
        }
        else {
            for (int i = seed.length - 1; i > -1; i--) { //convert seed into ShiftRegister form
                if (seed[i] != 0 && seed[i] != 1) { //if seed contains numbers that are not 0 or 1
                    System.out.println("Error: seed contains numbers that are not 0 or 1");
                    break;
                } else {
                    ShiftRegister[n] = seed[i];
                    n++;
                }
            }
        }
    }

    public static int[] generateSeed(String password) { //generates binary seed from a password string
        int[] seed;
        ArrayList<Integer> seedList = new ArrayList<Integer>();
        int charnum; //records the acsii number of the char in qn

        for (int i = 0; i < password.length(); i++) {
            charnum = (int) password.charAt(i); //convert character into acsii number
            for (int j = 0; j < toBinary(charnum).length; j++) {
                seedList.add(toBinary(charnum)[j]);
            }
        }
        seed = seedList.stream().mapToInt(i -> i).toArray();
        return seed;
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
        int feedbackbit = ShiftRegister[0] ^ ShiftRegister[ShiftRegister.length - tap - 1];
        for (int i = 0; i < (ShiftRegister.length - 1); i++) {
            ShiftRegister[i] = ShiftRegister[i + 1];
        }
        ShiftRegister[ShiftRegister.length - 1] = feedbackbit;
        return feedbackbit;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:
        int[] save = new int[k]; //save records the binary output of generate in array form
        for (int i = 0; i < k; i++) {
            save[i] = shift();
        }
        return toDecimal(save);
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        int output = 0;
        int n = 0;
        for (int i = array.length - 1; i > -1; i--) {
            output += array[i] * Math.pow(2, n);
            n++;
        }
        return output;
    }

    private static int[] toBinary(int number) { //convert number to binary form
        int store = number;
        int remainder = 0;
        int arrsize = 1;

        while (number > 0) {
            remainder = number % 2;
            number = number / 2;
            arrsize++;
        }

        int[] output = new int[arrsize];
        arrsize--;

        while (store > 0) {
            remainder = store % 2;
            store = store / 2;
            output[arrsize] = remainder;
            arrsize-- ;
        }
        output[0] = 0; //for convention purposes

        return output;

    }
}

