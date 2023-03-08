import java.util.ArrayList;
import java.util.Arrays;

public class generateSeed {
    public static String generateSeed(String password) {
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
        return Arrays.toString(seed);
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
//    private static int x = 40;
    public static void main(String[] args) {
        System.out.println("k" + generateSeed("ab"));
    }
}
