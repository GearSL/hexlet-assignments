package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private final int[] numbers;
    private int result = 0;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(this.numbers).min().getAsInt();
    }

    public int getResult() {
        return result;
    }
}
// END
