package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private final int[] numbers;
    private int result = 0;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for(int i = 0; i < this.numbers.length; i++) {
            result = Arrays.stream(numbers).max().getAsInt();
        }
    }

    public int getResult() {
        return result;
    }
}
// END
